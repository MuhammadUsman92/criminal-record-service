package com.muhammadusman92.criminalrecordservice.services.impl;

import com.muhammadusman92.criminalrecordservice.config.ConversionDtos;
import com.muhammadusman92.criminalrecordservice.entity.Crime;
import com.muhammadusman92.criminalrecordservice.entity.Criminal;
import com.muhammadusman92.criminalrecordservice.entity.CriminalStatus;
import com.muhammadusman92.criminalrecordservice.entity.Location;
import com.muhammadusman92.criminalrecordservice.exception.ResourceNotFoundException;
import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.FirDto;
import com.muhammadusman92.criminalrecordservice.payload.LocationDto;
import com.muhammadusman92.criminalrecordservice.payload.PageResponse;
import com.muhammadusman92.criminalrecordservice.repo.CrimeRepo;
import com.muhammadusman92.criminalrecordservice.repo.CriminalRepo;
import com.muhammadusman92.criminalrecordservice.repo.CriminalStatusRepo;
import com.muhammadusman92.criminalrecordservice.repo.LocationRepo;
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
    @Override
    public CrimeDto createCrime(CrimeDto crimeDto) {
        Crime crime = ConversionDtos.crimeDtoToCrime(crimeDto);
        Location location = locationRepo.save(crime.getIncidentLocation());
        crime.getIncidentLocation().setId(location.getId());
        Crime saveCrime = crimeRepo.save(crime);
        saveCrime.setIncidentLocation(saveCrime.getIncidentLocation());
        return ConversionDtos.crimeToCrimeDto(saveCrime);
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
