package com.muhammadusman92.criminalrecordservice.controllers;

import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.Response;
import com.muhammadusman92.criminalrecordservice.services.CrimeService;
import com.muhammadusman92.criminalrecordservice.services.CriminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/criminal")
public class CriminalController {
    @Autowired
    private CriminalService criminalService;

    @PostMapping("/")
    public ResponseEntity<Response> createCriminal(
            @RequestHeader("authorities") String authorities,
                                                   @RequestBody CriminalDto criminalDto) {
        if (authorities.contains("POLICE_ADMIN")) {
        CriminalDto savedCriminal = criminalService.createCriminal(criminalDto);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Criminal is successfully created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(savedCriminal)
                .build(), CREATED);
        } else {
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("You are not authorized for this service")
                    .status(FORBIDDEN)
                    .statusCode(FORBIDDEN.value())
                    .build(), FORBIDDEN);
        }
    }

    @GetMapping("/{criminalId}")
    public ResponseEntity<Response> getCriminalById(
            @RequestHeader("authorities") String authorities,
                                                    @PathVariable String criminalId) {
        if (authorities.contains("POLICE_USER")) {
        CriminalDto criminalDto = criminalService.getById(criminalId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Criminal with id " + criminalId + " is successfully get")
                .status(OK)
                .statusCode(OK.value())
                .data(criminalDto)
                .build(), OK);
        } else {
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("You are not authorized for this service")
                    .status(FORBIDDEN)
                    .statusCode(FORBIDDEN.value())
                    .build(), FORBIDDEN);
        }

    }
}