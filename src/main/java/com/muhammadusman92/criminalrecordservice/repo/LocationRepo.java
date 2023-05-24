package com.muhammadusman92.criminalrecordservice.repo;

import com.muhammadusman92.criminalrecordservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Integer> {
}
