package com.muhammadusman92.criminalrecordservice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
class CriminalStatusDto {
    private Date arrestDate;
    private Date DischargeDate;
    private int numOfDayInJail;
}