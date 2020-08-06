package com.teksenz.trainingbackend.web.mapper;

import com.teksenz.trainingbackend.domain.Student;
import com.teksenz.trainingbackend.web.model.StudentDto;
import org.mapstruct.Mapper;


@Mapper(uses = DateMapper.class)
public interface StudentMapper {
    StudentDto studentToDto(Student student);
    Student dtoToStudent(StudentDto studentDto);
}
