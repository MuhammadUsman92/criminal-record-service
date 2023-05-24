package com.muhammadusman92.criminalrecordservice.services;

import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.PageResponse;

public interface CriminalService {
    CriminalDto createCriminal(CriminalDto criminalDto);
    CriminalDto updateCriminal(CriminalDto criminalDto,String criminalId);
    CriminalDto getById(String criminalId);
    PageResponse<CriminalDto> getAllCriminals( Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteCriminal(String criminalId);
}
