package com.muhammadusman92.criminalrecordservice.services;

import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;

import java.util.List;

public interface LocationService {
    List<CrimeDto> getAllCrimesWithInLocation(double latitude, double longitude, double radius);

}
