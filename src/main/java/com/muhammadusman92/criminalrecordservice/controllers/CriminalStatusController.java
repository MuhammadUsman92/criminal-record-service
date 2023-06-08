package com.muhammadusman92.criminalrecordservice.controllers;

import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.CriminalStatusDto;
import com.muhammadusman92.criminalrecordservice.payload.Response;
import com.muhammadusman92.criminalrecordservice.services.CriminalService;
import com.muhammadusman92.criminalrecordservice.services.CriminalStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/criminal-status")
public class CriminalStatusController {
    @Autowired
    private CriminalStatusService criminalStatusService;
    @PostMapping("/criminalId/{criminalId}/crimeId/{crimeId}")
    public ResponseEntity<Response> createCriminalStatus(
            @RequestHeader("authorities") String authorities,
                                                   @PathVariable String criminalId,
                                                   @PathVariable Integer crimeId,
                                                   @RequestBody CriminalStatusDto criminalStatusDto){
        if (authorities.contains("POLICE_ADMIN")) {
            CriminalStatusDto savedCriminal = criminalStatusService.createCriminalStatus(criminalId,crimeId,criminalStatusDto);
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("Criminal Status is successfully added")
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
}
