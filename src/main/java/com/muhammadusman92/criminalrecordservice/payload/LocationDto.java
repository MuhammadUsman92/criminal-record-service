package com.muhammadusman92.criminalrecordservice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class LocationDto {
    private Integer id;
    private String street;
    private String city;
    private String postal_code;
    private String Country;
    private double latitude;
    private double longitude;
}
