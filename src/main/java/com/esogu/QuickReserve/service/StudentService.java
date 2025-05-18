package com.esogu.QuickReserve.service;

import com.esogu.QuickReserve.dto.StudentDto;
import com.esogu.QuickReserve.mapper.StudentMapper;
import com.esogu.QuickReserve.model.Student;
import com.esogu.QuickReserve.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        return studentMapper.toDto(student);
    }


    
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }



}
