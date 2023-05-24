package com.muhammadusman92.criminalrecordservice.services;

import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.PageResponse;

public interface CrimeService {
    CrimeDto createCrime(CrimeDto crimeDto);
    CrimeDto updateCrime(CrimeDto crimeDto,Integer crimeId);
    CrimeDto getById(Integer crimeId);
    PageResponse<CrimeDto> getAllCrimesOfCriminal(String criminalId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteCrime(Integer crimeId);
}
