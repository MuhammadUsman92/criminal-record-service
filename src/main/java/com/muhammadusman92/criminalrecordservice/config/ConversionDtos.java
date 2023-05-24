package com.muhammadusman92.criminalrecordservice.config;

import com.muhammadusman92.criminalrecordservice.entity.*;
import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.FirDto;
import com.muhammadusman92.criminalrecordservice.payload.LocationDto;

public class ConversionDtos {
    public static Crime crimeDtoToCrime(CrimeDto crimeDto){
        Crime crime = new Crime();
        crime.setIncidentDate(crimeDto.getIncidentDate());
        crime.setIncidentLocation(ConversionDtos.locationDtoToLocation(crimeDto.getIncidentLocation()));
        crime.setType(crimeDto.getType());
        return crime;
    }
    public static CrimeDto crimeToCrimeDto(Crime crime){
        CrimeDto crimeDto = new CrimeDto();
        crimeDto.setIncidentDate(crime.getIncidentDate());
        crimeDto.setId(crime.getId());
        crimeDto.setIncidentLocation(ConversionDtos.locationToLocationDto(crime.getIncidentLocation()));
        crimeDto.setType(crime.getType());
        return crimeDto;
    }

    public static Fir firDtoToFir(FirDto firDto){
        Fir fir = new Fir();
        fir.setComplainantCategory(firDto.getComplainantCategory());
        fir.setAssignedOfficerName(firDto.getAssignedOfficerName());
        fir.setComplainantDate(firDto.getComplainantDate());
        fir.setComplainantName(firDto.getComplainantName());
        fir.setContactNum(firDto.getContactNum());
        fir.setOfficerCell(firDto.getAssignedOfficerName());
        fir.setIncidentReport(firDto.getIncidentReport());
        fir.setPoliceStationName(firDto.getPoliceStationName());
        fir.setStatus(firDto.getStatus());
        fir.setDocument_1(firDto.getDocument_1());
        fir.setDocument_2(firDto.getDocument_2());
        return fir;
    }

    public static FirDto firToFirDto(Fir fir){
        FirDto firDto = new FirDto();
        firDto.setComplainantCategory(fir.getComplainantCategory());
        firDto.setAssignedOfficerName(fir.getAssignedOfficerName());
        firDto.setComplainantDate(fir.getComplainantDate());
        firDto.setComplainantName(fir.getComplainantName());
        firDto.setContactNum(fir.getContactNum());
        firDto.setOfficerCell(fir.getAssignedOfficerName());
        firDto.setIncidentReport(fir.getIncidentReport());
        firDto.setPoliceStationName(fir.getPoliceStationName());
        firDto.setStatus(fir.getStatus());
        firDto.setDocument_1(fir.getDocument_1());
        firDto.setDocument_2(fir.getDocument_2());
        return firDto;
    }

    public static Criminal criminalDtoToCriminal(CriminalDto criminalDto){
        Criminal criminal = new Criminal();
        criminal.setCNIC(criminalDto.getCnic());
        criminal.setId(criminalDto.getId());
        criminal.setAge(criminalDto.getAge());
        criminal.setGender(Gender.valueOf(criminalDto.getGender()));
        criminal.setLocation(ConversionDtos.locationDtoToLocation(criminalDto.getLocation()));
        criminal.setName(criminalDto.getName());
        return criminal;
    }
    public static CriminalDto criminalDtoToCriminal(Criminal criminal){
        CriminalDto criminalDto = new CriminalDto();
        criminalDto.setCnic(criminal.getCNIC());
        criminalDto.setId(criminal.getId());
        criminalDto.setAge(criminal.getAge());
        criminalDto.setGender(String.valueOf(criminal.getGender()));
        criminalDto.setLocation(ConversionDtos.locationToLocationDto(criminal.getLocation()));
        criminalDto.setName(criminal.getName());
        return criminalDto;
    }
    public static Location locationDtoToLocation(LocationDto locationDto){
        Location location = new Location();
        location.setCity(locationDto.getCity());
        location.setCountry(locationDto.getCountry());
        location.setPostal_code(locationDto.getPostal_code());
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());
        return location;
    }
    public static LocationDto locationToLocationDto(Location location){
        LocationDto locationDto = new LocationDto();
        locationDto.setCity(location.getCity());
        locationDto.setCountry(location.getCountry());
        locationDto.setPostal_code(location.getPostal_code());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setLongitude(location.getLongitude());
        return locationDto;
    }

}
