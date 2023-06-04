package com.muhammadusman92.criminalrecordservice.payload;

import com.muhammadusman92.criminalrecordservice.entity.CriminalStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CriminalDto {
    private String cnic;
    private String name;
    private int age;
    private String gender;
    private LocationDto location;
    private Set<CriminalStatusDto> criminalStatuses = new HashSet<>();
}
