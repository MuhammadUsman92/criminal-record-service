package com.muhammadusman92.criminalrecordservice.controllers;

import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.Response;
import com.muhammadusman92.criminalrecordservice.services.CrimeService;
import com.muhammadusman92.criminalrecordservice.services.CriminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/criminal")
public class CriminalController {
    @Autowired
    private CriminalService criminalService;
    @PostMapping("/")
    public ResponseEntity<Response> createCrimeOfPatientById(@RequestBody CriminalDto criminalDto){
        CriminalDto savedCriminal = criminalService.createCriminal(criminalDto);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Crime is successfully created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(savedCriminal)
                .build(), CREATED);
    }
}
