package com.teksenz.studentservice.web.mapper;

import com.teksenz.studentservice.domain.Student;
import com.teksenz.studentservice.web.model.StudentDto;
import org.mapstruct.Mapper;


@Mapper(uses = {DateMapper.class,RefereeMapper.class})
public interface StudentMapper {
    StudentDto studentToDto(Student student);
    Student dtoToStudent(StudentDto studentDto);
}
