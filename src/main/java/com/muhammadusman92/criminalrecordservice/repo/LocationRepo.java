package com.muhammadusman92.criminalrecordservice.repo;

import com.muhammadusman92.criminalrecordservice.entity.Crime;
import com.muhammadusman92.criminalrecordservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location, Integer> {
    @Query("SELECT l.crime FROM Location l WHERE l.latitude BETWEEN :minLatitude AND :maxLatitude AND l.longitude BETWEEN :minLongitude AND :maxLongitude")
    List<Crime> findCrimesWithinBounds(@Param("minLatitude") double minLatitude, @Param("maxLatitude") double maxLatitude, @Param("minLongitude") double minLongitude, @Param("maxLongitude") double maxLongitude);

}
