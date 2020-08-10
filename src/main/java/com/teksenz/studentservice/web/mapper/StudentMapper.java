package com.teksenz.studentservice.web.mapper;

import com.teksenz.studentservice.domain.Referee;
import com.teksenz.studentservice.domain.Student;
import com.teksenz.studentservice.web.model.RefereeDto;
import com.teksenz.studentservice.web.model.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = {DateMapper.class,RefereeMapper.class})
public interface StudentMapper {
    @Mapping(source = "referee", target = "refereeDto")
    StudentDto studentToDto(Student student);
    @Mapping(source = "refereeDto", target = "referee")
    Student dtoToStudent(StudentDto studentDto);
}
