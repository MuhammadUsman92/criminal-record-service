package com.muhammadusman92.criminalrecordservice.services.impl;

import com.muhammadusman92.criminalrecordservice.config.ConversionDtos;
import com.muhammadusman92.criminalrecordservice.entity.*;
import com.muhammadusman92.criminalrecordservice.exception.ResourceNotFoundException;
import com.muhammadusman92.criminalrecordservice.payload.*;
import com.muhammadusman92.criminalrecordservice.repo.*;
import com.muhammadusman92.criminalrecordservice.services.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CrimeServiceImpl implements CrimeService {
    @Autowired
    private CrimeRepo crimeRepo;
    @Autowired
    private CriminalRepo criminalRepo;
    @Autowired
    private CriminalStatusRepo criminalStatusRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private FirRepo firRepo;
    @Override
    public CrimeDto createCrime(CrimeDto crimeDto) {
        Crime crime = ConversionDtos.crimeDtoToCrime(crimeDto);
        Set<Fir> firSet = crimeDto.getFirSet().stream().map(ConversionDtos::firDtoToFir).collect(Collectors.toSet());
        Location location = locationRepo.save(crime.getIncidentLocation());
        crime.getIncidentLocation().setId(location.getId());
        crime.setFirSet(firSet);
        Crime saveCrime = crimeRepo.save(crime);
        for(Fir fir:firSet){
            fir.setCrime(crime);
        }
        firRepo.saveAll(firSet);
        CrimeDto crimeDto1 = ConversionDtos.crimeToCrimeDto(saveCrime);
        crimeDto1.setFirSet(crimeDto.getFirSet());
        crimeDto1.setIncidentLocation(ConversionDtos.locationToLocationDto(saveCrime.getIncidentLocation()));
        return crimeDto1;
    }

    @Override
    public CrimeDto updateCrime(CrimeDto crimeDto, Integer crimeId) {
        Crime crime = ConversionDtos.crimeDtoToCrime(crimeDto);
        Crime findCrime = crimeRepo.findById(crimeId)
                .orElseThrow(()->new ResourceNotFoundException("Crime","CrimeId",crimeId));
        crime.setId(findCrime.getId());
        crime.getFirSet().addAll(crime.getFirSet());
        return ConversionDtos.crimeToCrimeDto(crimeRepo.save(crime));
    }

    @Override
    public CrimeDto getById(Integer crimeId) {
        Crime findCrime = crimeRepo.findById(crimeId)
                .orElseThrow(()->new ResourceNotFoundException("Crime","CrimeId",crimeId));
        CrimeDto findCrimeDto = ConversionDtos.crimeToCrimeDto(findCrime);
        Set<FirDto> firDtoSet = findCrime.getFirSet().stream().map(ConversionDtos::firToFirDto).collect(Collectors.toSet());
        findCrimeDto.setFirSet(firDtoSet);
        List<CriminalStatus> byIdCrimeId = criminalStatusRepo.findByIdCrimeId(crimeId);
        Set<CriminalDto> criminalDtoSet = byIdCrimeId.stream().map(criminalStatus -> ConversionDtos.criminalDtoToCriminal(criminalStatus.getCriminal())).collect(Collectors.toSet());
        findCrimeDto.setCriminalDtos(criminalDtoSet);
        return findCrimeDto;
    }

    @Override
    public PageResponse<CrimeDto> getAllCrimesOfCriminal(String criminalId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort= (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Criminal findCriminal = criminalRepo.findById(criminalId)
                .orElseThrow(()->new ResourceNotFoundException("Criminal","CriminalId",criminalId));
        Page<CriminalStatus> postPage = criminalStatusRepo.findByIdCriminalCNIC(findCriminal.getCNIC(),pageable);
        List<Crime> crimeList = postPage.getContent().stream()
                .map(CriminalStatus::getCrime).toList();
        List<CrimeDto> crimeDtoList= crimeList.stream().map(
                (ConversionDtos::crimeToCrimeDto)).toList();
        PageResponse<CrimeDto> pageResponse=new PageResponse<>();
        pageResponse.setPageNumber(postPage.getNumber());
        pageResponse.setPageSize(postPage.getSize());
        pageResponse.setTotalPage(postPage.getTotalPages());
        pageResponse.setLast(postPage.isLast());
        pageResponse.setTotalElements(postPage.getTotalElements());
        pageResponse.setContent(crimeDtoList);
        return pageResponse;
    }

    @Override
    public void deleteCrime(Integer crimeId) {
        Crime findCrime = crimeRepo.findById(crimeId)
                .orElseThrow(()->new ResourceNotFoundException("Crime","CrimeId",crimeId));
        crimeRepo.delete(findCrime);
    }
}
