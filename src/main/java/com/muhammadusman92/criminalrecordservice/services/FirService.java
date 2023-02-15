package com.muhammadusman92.criminalrecordservice.services;

import com.muhammadusman92.criminalrecordservice.payload.FirDto;
import com.muhammadusman92.criminalrecordservice.payload.PageResponse;

public interface FirService {
    FirDto createFir(FirDto firDto);
    FirDto updateFir(FirDto firDto,Integer firId);
    FirDto getById(Integer firId);
    PageResponse<FirDto> getAllFirs(String patientId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteCriminal(Integer firId);
}
