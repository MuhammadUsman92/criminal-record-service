package com.muhammadusman92.criminalrecordservice.controllers;


import com.muhammadusman92.criminalrecordservice.config.AppConstants;
import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.CriminalDto;
import com.muhammadusman92.criminalrecordservice.payload.PageResponse;
import com.muhammadusman92.criminalrecordservice.payload.Response;
import com.muhammadusman92.criminalrecordservice.services.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/crime")
public class CrimeController {
    @Autowired
    private CrimeService crimeService;
    @PostMapping("/")
    public ResponseEntity<Response> createCrime(
           @RequestHeader("authorities") String authorities,
           @RequestBody CrimeDto crimeDto){
        if (authorities.contains("POLICE_ADMIN")) {
            CrimeDto savedCrime = crimeService.createCrime(crimeDto);
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("Crime is successfully created with crime id "+savedCrime.getId())
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .data(savedCrime)
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
    @PutMapping("/{crimeId}")
    public ResponseEntity<Response> updateCrime(@RequestBody CrimeDto crimeDto,
                                                @RequestHeader("authorities") String authorities,
                                                @PathVariable Integer crimeId){
        if (authorities.contains("POLICE_ADMIN")) {
            CrimeDto updateCrime = crimeService.updateCrime(crimeDto,crimeId);
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("Crime is successfully updated")
                    .status(OK)
                    .statusCode(OK.value())
                    .data(updateCrime)
                    .build(),OK);
        } else {
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("You are not authorized for this service")
                    .status(FORBIDDEN)
                    .statusCode(FORBIDDEN.value())
                    .build(), FORBIDDEN);
        }
    }
    @DeleteMapping("/{crimeId}")
    public ResponseEntity<Response> deleteCrime(@RequestHeader("authorities") String authorities,
                                                @PathVariable Integer crimeId){
        if (authorities.contains("POLICE_ADMIN")) {
            crimeService.deleteCrime(crimeId);
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("Crime deleted successfully")
                    .status(OK)
                    .statusCode(OK.value())
                    .build(),OK);
        } else {
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("You are not authorized for this service")
                    .status(FORBIDDEN)
                    .statusCode(FORBIDDEN.value())
                    .build(), FORBIDDEN);
        }

    }
    @GetMapping("/criminalId/{criminalId}")
    public ResponseEntity<Response> getAllCrimeOfPatient(@RequestHeader("authorities") String authorities,
            @PathVariable String criminalId,
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(name = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir){
//        if (authorities.contains("POLICE_USER")) {
            PageResponse<CrimeDto> pageResponse = crimeService.getAllCrimesOfCriminal(criminalId,pageNumber,pageSize,sortBy,sortDir);
            return new  ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("All Crime of Criminal with id "+criminalId+" are successfully get")
                    .status(OK)
                    .statusCode(OK.value())
                    .data(pageResponse)
                    .build(),OK);
//        } else {
//            return new ResponseEntity<>(Response.builder()
//                    .timeStamp(now())
//                    .message("You are not authorized for this service")
//                    .status(FORBIDDEN)
//                    .statusCode(FORBIDDEN.value())
//                    .build(), FORBIDDEN);
//        }

    }
    @GetMapping("/{crimeId}")
    public ResponseEntity<Response> getCrimeById(
//            @RequestHeader("authorities") String authorities,
            @PathVariable Integer crimeId){
//        if (authorities.contains("POLICE_USER")) {
            CrimeDto crimeDto=crimeService.getById(crimeId);
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("Crime with id "+crimeId+" are successfully get")
                    .status(OK)
                    .statusCode(OK.value())
                    .data(crimeDto)
                    .build(),OK);
//        } else {
//            return new ResponseEntity<>(Response.builder()
//                    .timeStamp(now())
//                    .message("You are not authorized for this service")
//                    .status(FORBIDDEN)
//                    .statusCode(FORBIDDEN.value())
//                    .build(), FORBIDDEN);
//        }
    }
}
