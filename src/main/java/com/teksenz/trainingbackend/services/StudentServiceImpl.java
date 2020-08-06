package com.teksenz.trainingbackend.services;

import com.teksenz.trainingbackend.domain.Student;
import com.teksenz.trainingbackend.repositories.StudentRepository;
import com.teksenz.trainingbackend.web.mapper.StudentMapper;
import com.teksenz.trainingbackend.web.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public StudentDto save(StudentDto studentDto) {
        return studentMapper.studentToDto(studentRepository.save(studentMapper.dtoToStudent(studentDto)));
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::studentToDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto findById(UUID id) {
        return studentMapper.studentToDto(studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student Not Found")));
    }

    @Override
    public StudentDto findByFirstName(String firstName) {
        Student student = studentRepository.findByFirstName(firstName).orElseThrow(()->new RuntimeException("Student not found"));
        return studentMapper.studentToDto(student);
    }

    @Override
    public StudentDto updateById(UUID id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student Not Found"));
        return studentMapper.studentToDto(studentRepository.save(studentMapper.dtoToStudent(studentDto)));
    }

    @Override
    public void deleteById(UUID id) {
        studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student Not Found"));
        studentRepository.deleteById(id);
    }
}
