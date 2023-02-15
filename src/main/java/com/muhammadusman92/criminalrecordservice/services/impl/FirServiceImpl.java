package com.muhammadusman92.criminalrecordservice.services.impl;

import com.muhammadusman92.criminalrecordservice.config.ConversionDtos;
import com.muhammadusman92.criminalrecordservice.entity.Fir;
import com.muhammadusman92.criminalrecordservice.exception.ResourceNotFoundException;
import com.muhammadusman92.criminalrecordservice.payload.FirDto;
import com.muhammadusman92.criminalrecordservice.payload.PageResponse;
import com.muhammadusman92.criminalrecordservice.repo.FirRepo;
import com.muhammadusman92.criminalrecordservice.services.FirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirServiceImpl implements FirService {
    @Autowired
    private FirRepo firRepo;
    @Override
    public FirDto createFir(FirDto firDto) {
        Fir fir = ConversionDtos.firDtoToFir(firDto);
        Fir saveFir = firRepo.save(fir);
        return ConversionDtos.firToFirDto(saveFir);
    }

    @Override
    public FirDto updateFir(FirDto firDto, Integer firId) {
        Fir fir = ConversionDtos.firDtoToFir(firDto);
        Fir findFir = firRepo.findById(firId)
                .orElseThrow(()->new ResourceNotFoundException("Fir","FirId",firId));
        return null;
    }

    @Override
    public FirDto getById(Integer firId) {
        return null;
    }

    @Override
    public PageResponse<FirDto> getAllFirs(String patientId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public void deleteCriminal(Integer firId) {

    }
}
