package com.teksenz.studentservice.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
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
    @JsonIgnoreProperties("students")
    @Valid
    @JsonProperty("referredBy")
    private RefereeDto refereeDto;
    @Builder
    public StudentDto(@Null UUID id, @Null Long version, @Null OffsetDateTime createdDate, @Null OffsetDateTime lastModifiedDate, @NotNull(message = "firstName  must not be null") @Size(min = 1, max = 100) String firstName, @NotNull(message = "lastName must not be null") @Size(min = 1, max = 100) String lastName, @NotNull(message = "email id must not be null") @Email String email, @NotNull(message = "phone number must not be null") @Size(min = 10, max = 13) String phoneNo, @NotNull(message = "qualification must not be null") @Size(min = 1, max = 50) String qualification, String occupation, String employer, @Valid RefereeDto refereeDto) {
        super(id, version, createdDate, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.qualification = qualification;
        this.occupation = occupation;
        this.employer = employer;
        this.refereeDto = refereeDto;
    }
}
