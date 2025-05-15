package com.esogu.QuickReserve.repository;

import com.esogu.QuickReserve.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeskRepository extends JpaRepository<Desk, Long> {

    List<Desk> findBySchoolId(Long schoolId);
}
