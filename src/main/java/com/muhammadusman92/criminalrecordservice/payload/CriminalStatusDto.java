package com.muhammadusman92.criminalrecordservice.payload;

import com.muhammadusman92.criminalrecordservice.entity.Crime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public
class CriminalStatusDto {
    private String status;
    private Date arrestDate;
    private Date dischargeDate;
    private int numOfDayInJail;
    private CrimeDto crime;
}