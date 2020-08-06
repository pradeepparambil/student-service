package com.teksenz.trainingbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Referee extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @JsonIgnoreProperties("referee")
    @OneToMany(mappedBy = "referee", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Student> students = new ArrayList<>();

    @Builder
    public Referee(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String firstName, String lastName, String email, String phone, List<Student> students) {
        super(id, version, createdDate, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.students = students;
    }
}
