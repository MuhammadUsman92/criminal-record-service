package com.muhammadusman92.criminalrecordservice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CrimeDto {
    private int id;
    private LocationDto incidentLocation;
    private Date incidentDate;
    private String type;
    private Set<FirDto> firSet = new HashSet<>();
    private Set<CriminalDto> criminalDtos = new HashSet<>();
}
