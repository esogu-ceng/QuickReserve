package com.esogu.QuickReserve.repository;

import com.esogu.QuickReserve.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}