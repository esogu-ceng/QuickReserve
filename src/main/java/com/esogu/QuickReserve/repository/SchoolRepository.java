package com.esogu.QuickReserve.repository;

import com.esogu.QuickReserve.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}