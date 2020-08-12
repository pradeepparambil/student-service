package com.teksenz.studentservice.services.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    @Null
    private UUID id;
    @Null
    @JsonIgnore
    private Long version;
    @Null
    @JsonIgnore
    private OffsetDateTime createdDate;
    @Null
    @JsonIgnore
    private OffsetDateTime lastModifiedDate;
}
