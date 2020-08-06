package com.teksenz.studentservice.web.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class StudentDto extends BaseDto{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNo;
    @NotNull
    private String qualification;

    private String occupation;

    private String employer;
    @Builder
    public StudentDto(@Null UUID id, @Null Long version, @Null OffsetDateTime createdDate,
                      @Null OffsetDateTime lastModifiedDate, @NotNull String firstName, @NotNull String lastName,
                      @NotNull String email, @NotNull String phoneNo, @NotNull String qualification,
                      String occupation, String employer) {
        super(id, version, createdDate, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.qualification = qualification;
        this.occupation = occupation;
        this.employer = employer;
    }


}
