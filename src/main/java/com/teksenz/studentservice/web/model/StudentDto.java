package com.teksenz.studentservice.web.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class StudentDto extends BaseDto{
    @NotNull(message = "firstName  must not be null")
    @Size(min = 1, max = 100)
    private String firstName;
    @NotNull(message = "lastName must not be null")
    @Size(min = 1, max = 100)
    private String lastName;
    @NotNull(message = "email id must not be null")
    @Email
    private String email;
    @NotNull(message = "phone number must not be null")
    @Size(min = 10,max = 13)
    private String phoneNo;
    @NotNull(message = "qualification must not be null")
    @Size(min = 1,max = 50)
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
