package com.muhammadusman92.criminalrecordservice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CriminalDto {
    private String cnic;
    private String id;
    private String Name;
    private int Age;
    private String gender;
    private LocationDto location;
}
