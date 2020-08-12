package com.teksenz.studentservice.services;

import com.teksenz.studentservice.web.model.StudentDto;
import com.teksenz.studentservice.web.model.StudentPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;


public interface StudentService {

    StudentDto save(StudentDto studentDto);

    List<StudentDto> findAll();

    StudentDto findById(UUID id);

    StudentDto findByFirstName(String firstName);

    StudentDto updateById(UUID id, StudentDto studentDto);

    void deleteById(UUID id);

    StudentPagedList listStudents(String firstName, PageRequest of);
}

