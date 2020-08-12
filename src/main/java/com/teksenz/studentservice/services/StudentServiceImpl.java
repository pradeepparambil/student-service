package com.teksenz.studentservice.services;

import com.teksenz.studentservice.domain.Referee;
import com.teksenz.studentservice.domain.Student;
import com.teksenz.studentservice.repositories.RefereeRepository;
import com.teksenz.studentservice.repositories.StudentRepository;
import com.teksenz.studentservice.web.controllers.NotFoundException;
import com.teksenz.studentservice.web.mapper.RefereeMapper;
import com.teksenz.studentservice.web.mapper.StudentMapper;
import com.teksenz.studentservice.web.model.StudentDto;
import com.teksenz.studentservice.web.model.StudentPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final RefereeRepository refereeRepository;
    private final RefereeMapper refereeMapper;


    @Override
    public StudentDto save(StudentDto studentDto) {
        Referee referee = null;
        Student student = studentMapper.dtoToStudent(studentDto);
        if(studentDto.getRefereeDto()!=null){
            Optional<Referee> optionalReferee = refereeRepository.findByFirstName(studentDto.getRefereeDto().getFirstName());
            if(optionalReferee.isEmpty()) {
                referee = refereeRepository.save(refereeMapper.dtoToReferee(studentDto.getRefereeDto()));

            }else {
                referee = optionalReferee.get();
            }
            student.setReferee(referee);
        }
        return studentMapper.studentToDto(studentRepository.save(student));
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::studentToDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto findById(UUID id) {
        return studentMapper.studentToDto(studentRepository.findById(id).orElseThrow(()->new NotFoundException("Student Not Found")));
    }

    @Override
    public StudentDto findByFirstName(String firstName) {
        Student student = studentRepository.findByFirstName(firstName).orElseThrow(()->new NotFoundException("Student not found"));
        return studentMapper.studentToDto(student);
    }

    @Override
    public StudentDto updateById(UUID id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow(()->new NotFoundException("Student Not Found"));
        return studentMapper.studentToDto(studentRepository.save(studentMapper.dtoToStudent(studentDto)));
    }

    @Override
    public void deleteById(UUID id) {
        studentRepository.findById(id).orElseThrow(()->new NotFoundException("Student Not Found"));
        studentRepository.deleteById(id);
    }

    @Override
    public StudentPagedList listStudents(String firstName, PageRequest pageRequest) {
        Page<Student> studentPage;
        if(!StringUtils.isEmpty(firstName)){
            studentPage = studentRepository.findAllByFirstName(firstName,pageRequest);
        }else {
            studentPage = studentRepository.findAll(pageRequest);
        }

        return new StudentPagedList(
                studentPage.getContent().stream().map(studentMapper::studentToDto).collect(Collectors.toList()),
                pageRequest,
                studentPage.getTotalElements()
        );
    }
}
