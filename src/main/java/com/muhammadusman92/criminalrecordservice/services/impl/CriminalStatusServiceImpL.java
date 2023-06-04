package com.muhammadusman92.criminalrecordservice.services.impl;

import com.muhammadusman92.criminalrecordservice.config.ConversionDtos;
import com.muhammadusman92.criminalrecordservice.entity.Crime;
import com.muhammadusman92.criminalrecordservice.entity.Criminal;
import com.muhammadusman92.criminalrecordservice.entity.CriminalStatus;
import com.muhammadusman92.criminalrecordservice.entity.CriminalStatusKey;
import com.muhammadusman92.criminalrecordservice.exception.ResourceNotFoundException;
import com.muhammadusman92.criminalrecordservice.payload.CriminalStatusDto;
import com.muhammadusman92.criminalrecordservice.repo.CrimeRepo;
import com.muhammadusman92.criminalrecordservice.repo.CriminalRepo;
import com.muhammadusman92.criminalrecordservice.repo.CriminalStatusRepo;
import com.muhammadusman92.criminalrecordservice.services.CriminalStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriminalStatusServiceImpL implements CriminalStatusService {
    @Autowired
    private CriminalRepo criminalRepo;
    @Autowired
    private CrimeRepo crimeRepo;
    @Autowired
    private CriminalStatusRepo criminalStatusRepo;
    @Override
    public CriminalStatusDto createCriminalStatus(String criminalId, Integer crimeId, CriminalStatusDto criminalStatusDto) {
        CriminalStatus criminalStatus = ConversionDtos.criminalStatusDtoTOCriminalStatus(criminalStatusDto);
        Criminal criminal = criminalRepo.findById(criminalId).orElseThrow(() -> new ResourceNotFoundException("Criminal", "criminalId", criminalId));
        Crime crime = crimeRepo.findById(crimeId).orElseThrow(() -> new ResourceNotFoundException("Crime", "crimeId", crimeId));
        criminalStatus.setCriminal(criminal);
        criminalStatus.setCrime(crime);
        CriminalStatusKey criminalStatusKey = new CriminalStatusKey();
        criminalStatusKey.setCriminalCNIC(criminalId);
        criminalStatusKey.setCrimeId(crimeId);
        criminalStatus.setId(criminalStatusKey);
        CriminalStatus save = criminalStatusRepo.save(criminalStatus);
        CriminalStatusDto criminalStatusDto1 = ConversionDtos.criminalStatusTOCriminalStatusDto(save);
        criminalStatusDto1.setCrime(ConversionDtos.crimeToCrimeDto(crime));
        return criminalStatusDto1;
    }
}
