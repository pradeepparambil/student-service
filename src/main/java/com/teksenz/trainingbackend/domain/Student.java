package com.teksenz.trainingbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student extends BaseEntity{
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNo;
    private String qualification;
    private String occupation;
    private String employer;
    @JsonIgnoreProperties("students")
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Referee referee;

    @Builder
    public Student(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String firstName,
                   String lastName, String email, String phoneNo, String qualification, String occupation,
                   String employer, Referee referee) {
        super(id, version, createdDate, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.qualification = qualification;
        this.occupation = occupation;
        this.employer = employer;
        this.referee = referee;
    }

}
