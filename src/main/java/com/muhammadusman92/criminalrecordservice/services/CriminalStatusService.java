package com.muhammadusman92.criminalrecordservice.services;

import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.CriminalStatusDto;

public interface CriminalStatusService {

    CriminalStatusDto createCriminalStatus(String criminalId,Integer crimeId,CriminalStatusDto criminalStatusDto);
}
