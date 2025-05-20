package com.esogu.QuickReserve.service;

import com.esogu.QuickReserve.dto.StudentDto;
import com.esogu.QuickReserve.mapper.StudentMapper;
import com.esogu.QuickReserve.model.Student;
import com.esogu.QuickReserve.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
