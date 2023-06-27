package com.muhammadusman92.criminalrecordservice.services.impl;

import com.muhammadusman92.criminalrecordservice.config.ConversionDtos;
import com.muhammadusman92.criminalrecordservice.entity.Crime;
import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.repo.LocationRepo;
import com.muhammadusman92.criminalrecordservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepo locationRepo;

    @Override
    public List<CrimeDto> getAllCrimesWithInLocation(double latitude, double longitude, double radius) {
        double radiusInDegrees = radius / 111120.0;

        double minLatitude = latitude - radiusInDegrees;
        double maxLatitude = latitude + radiusInDegrees;

        double degreesPerLongitude = Math.cos(Math.toRadians(latitude)) * 111120.0;
        double radiusLongitude = radius / degreesPerLongitude;

        double minLongitude = longitude - radiusLongitude;
        double maxLongitude = longitude + radiusLongitude;
        List<Crime> crimesList = locationRepo.findCrimesWithinBounds(minLatitude, maxLatitude, minLongitude, maxLongitude);
        List<CrimeDto> crimeDtoList = new ArrayList<>();
        for(Crime crime:crimesList){
            CrimeDto crimeDto = ConversionDtos.crimeToCrimeDto(crime);
            crimeDto.setIncidentLocation(ConversionDtos.locationToLocationDto(crime.getIncidentLocation()));
            crimeDtoList.add(crimeDto);
        }
        return crimeDtoList;
    }
}
