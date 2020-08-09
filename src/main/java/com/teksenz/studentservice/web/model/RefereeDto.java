package com.teksenz.studentservice.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class RefereeDto extends BaseDto{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    @NotNull
    private String phone;
    private List<StudentDto> students;
    @Builder
    public RefereeDto(@Null UUID id, @Null Long version, @Null OffsetDateTime createdDate,
                      @Null OffsetDateTime lastModifiedDate, String firstName, String lastName,
                      @Email String email, String phone, List<StudentDto> students) {
        super(id, version, createdDate, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.students = students;
    }
}
