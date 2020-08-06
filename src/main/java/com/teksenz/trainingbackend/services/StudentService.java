package com.teksenz.trainingbackend.services;

import com.teksenz.trainingbackend.domain.Student;
import com.teksenz.trainingbackend.web.model.StudentDto;

import java.util.List;
import java.util.UUID;


public interface StudentService {

    StudentDto save(StudentDto studentDto);

    List<StudentDto> findAll();

    StudentDto findById(UUID id);

    StudentDto findByFirstName(String firstName);

    StudentDto updateById(UUID id, StudentDto studentDto);

    void deleteById(UUID id);

}
