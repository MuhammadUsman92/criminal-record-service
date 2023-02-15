package com.muhammadusman92.criminalrecordservice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationDto {
    private Integer id;
    private String houseNum;
    private String street;
    private String district;
    private String city;
    private String postal_code;
    private String Country;
    private String latitude;
    private String longitude;
}
