package com.esogu.QuickReserve.mapper;

import com.esogu.QuickReserve.dto.StudentDto;
import com.esogu.QuickReserve.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setTimezone(student.getTimezone());
        return dto;
    }



    public Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setTimezone(dto.getTimezone());
        return student;
    }
}
