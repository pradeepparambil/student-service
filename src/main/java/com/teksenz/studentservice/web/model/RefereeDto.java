package com.teksenz.studentservice.web.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefereeDto extends BaseDto{
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;
    private List<StudentDto> students;
}
