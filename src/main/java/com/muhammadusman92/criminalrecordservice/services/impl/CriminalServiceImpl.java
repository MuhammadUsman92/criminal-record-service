package com.muhammadusman92.criminalrecordservice.services.impl;

import com.muhammadusman92.criminalrecordservice.config.ConversionDtos;
import com.muhammadusman92.criminalrecordservice.entity.Crime;
import com.muhammadusman92.criminalrecordservice.entity.Criminal;
import com.muhammadusman92.criminalrecordservice.entity.CriminalStatus;
import com.muhammadusman92.criminalrecordservice.entity.Location;
import com.muhammadusman92.criminalrecordservice.exception.ResourceNotFoundException;
import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.PageResponse;
import com.muhammadusman92.criminalrecordservice.repo.CriminalRepo;
import com.muhammadusman92.criminalrecordservice.repo.LocationRepo;
import com.muhammadusman92.criminalrecordservice.services.CriminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriminalServiceImpl implements CriminalService {
    @Autowired
    private CriminalRepo criminalRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Override
    public CriminalDto createCriminal(CriminalDto criminalDto) {
        Criminal criminal = ConversionDtos.criminalDtoToCriminal(criminalDto);
        Location saveLocation = locationRepo.save(criminal.getLocation());
        criminal.getLocation().setId(saveLocation.getId());
        Criminal saveCriminal = criminalRepo.save(criminal);
        return ConversionDtos.criminalDtoToCriminal(saveCriminal);
    }

    @Override
    public CriminalDto updateCriminal(CriminalDto criminalDto, String criminalId) {
        Criminal criminal = ConversionDtos.criminalDtoToCriminal(criminalDto);
        Criminal findCriminal = criminalRepo.findById(criminalId)
                .orElseThrow(()->new ResourceNotFoundException("Criminal","CriminalId",criminalId));
        Location saveLocation = locationRepo.save(criminal.getLocation());
        criminal.getLocation().setId(saveLocation.getId());
        Criminal saveCriminal = criminalRepo.save(criminal);
        return ConversionDtos.criminalDtoToCriminal(saveCriminal);
    }

    @Override
    public CriminalDto getById(String criminalId) {
        Criminal findCriminal = criminalRepo.findById(criminalId)
                .orElseThrow(()->new ResourceNotFoundException("Criminal","CriminalId",criminalId));
        return ConversionDtos.criminalDtoToCriminal(findCriminal);
    }

    @Override
    public PageResponse<CriminalDto> getAllCriminals(
            Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort= (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Page<Criminal> postPage = criminalRepo.findAll(pageable);
        List<Criminal> criminalList = postPage.getContent();
        List<CriminalDto> criminalDtoList= criminalList.stream().map(
                (ConversionDtos::criminalDtoToCriminal)).toList();
        PageResponse<CriminalDto> pageResponse=new PageResponse<>();
        pageResponse.setPageNumber(postPage.getNumber());
        pageResponse.setPageSize(postPage.getSize());
        pageResponse.setTotalPage(postPage.getTotalPages());
        pageResponse.setLast(postPage.isLast());
        pageResponse.setTotalElements(postPage.getTotalElements());
        pageResponse.setContent(criminalDtoList);
        return pageResponse;
    }

    @Override
    public void deleteCriminal(String criminalId) {
        Criminal findCriminal = criminalRepo.findById(criminalId)
                .orElseThrow(()->new ResourceNotFoundException("Criminal","CriminalId",criminalId));
        criminalRepo.delete(findCriminal);
    }
}
