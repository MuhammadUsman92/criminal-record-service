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
import java.util.stream.Collectors;

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
            double crimeLatitude = crime.getIncidentLocation().getLatitude();
            double crimeLongitude = crime.getIncidentLocation().getLongitude();
            double distance = calculateDistance(latitude, longitude, crimeLatitude, crimeLongitude, radiusInDegrees);
            if (distance <= radiusInDegrees) {
                CrimeDto crimeDto = ConversionDtos.crimeToCrimeDto(crime);
                crimeDto.setIncidentLocation(ConversionDtos.locationToLocationDto(crime.getIncidentLocation()));
                crimeDto.setCriminal(crime.getCriminalStatuses().stream().map(criminalStatus
                        -> ConversionDtos.criminalDtoToCriminal(criminalStatus.getCriminal())).collect(Collectors.toSet()));
                crimeDtoList.add(crimeDto);
            }
        }
        return crimeDtoList;
    }
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2, double radius) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = radius * c;
        return distance;
    }
}
