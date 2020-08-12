package com.teksenz.studentservice.web.controllers;

import com.teksenz.studentservice.domain.Student;
import com.teksenz.studentservice.services.StudentService;
import com.teksenz.studentservice.services.course.CourseService;
import com.teksenz.studentservice.services.course.model.CourseDto;
import com.teksenz.studentservice.web.model.StudentDto;
import com.teksenz.studentservice.web.model.StudentPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/student", produces = {"application/json"})
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final Integer DEFAULT_PAGE_NO = 0;
    private final Integer DEFAULT_PAGE_SIZE = 25;


    @PostMapping(value = "/enroll",consumes = {"application/json"})
    public ResponseEntity saveStudent(@Valid @RequestBody StudentDto studentDto){
        CourseDto courseDto = courseService.findCourseToBeEnrolled().orElseThrow(()-> new RuntimeException("Course not found"));
        StudentDto savedDto = studentService.save(studentDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/student/" + savedDto.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<StudentPagedList> findAll(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                    @RequestParam(value = "firstName", required = false)String firstName){
        if(pageNumber == null || pageNumber <0){
            pageNumber = DEFAULT_PAGE_NO;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        StudentPagedList studentPagedList =  studentService.listStudents(firstName, PageRequest.of(pageNumber,pageSize));
        return new ResponseEntity<StudentPagedList>(studentPagedList,HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable UUID id){
        return new ResponseEntity(studentService.findById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<StudentDto> updateStudent(@PathVariable UUID id,@Validated @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateById(id,studentDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable UUID id){
        studentService.deleteById(id);
    }

}
