package com.muhammadusman92.criminalrecordservice.controllers;

import com.muhammadusman92.criminalrecordservice.payload.CrimeDto;
import com.muhammadusman92.criminalrecordservice.payload.Response;
import com.muhammadusman92.criminalrecordservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping("/latitude/{latitude}/longitude/{longitude}/radius/{radius}")
    public ResponseEntity<Response> getAllCrimesWithInLocation(@RequestHeader("authorities") String authorities,
                                                                 @PathVariable double latitude,
                                                                 @PathVariable double longitude,
                                                                 @PathVariable double radius){
        if (authorities.contains("POLICE_ADMIN")) {
            List<CrimeDto> allDiseasesWithInLocation = locationService.getAllCrimesWithInLocation(latitude, longitude, radius);
            return new ResponseEntity<>(Response.builder()
                    .timeStamp(now())
                    .message("All crimes with in radius are get successfully")
                    .status(OK)
                    .data(allDiseasesWithInLocation)
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
}
