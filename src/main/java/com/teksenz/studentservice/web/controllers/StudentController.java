package com.teksenz.studentservice.web.controllers;

import com.teksenz.studentservice.domain.Student;
import com.teksenz.studentservice.services.StudentService;
import com.teksenz.studentservice.web.model.StudentDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/student", produces = {"application/json"})
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/enroll",consumes = {"application/json"})
    public ResponseEntity saveStudent(@Validated @RequestBody StudentDto studentDto){
        StudentDto savedDto = studentService.save(studentDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/student/" + savedDto.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable UUID id){
        return new ResponseEntity(studentService.findById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable UUID id,@Validated @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateById(id,studentDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable UUID id){
        studentService.deleteById(id);
    }

}
