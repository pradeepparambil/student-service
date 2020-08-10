package com.teksenz.studentservice.web.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseDto {
    @Null
    private UUID id;
    @Null
    @JsonIgnore
    Long version;
    @Null
    @JsonIgnore
    private OffsetDateTime createdDate;
    @Null
    @JsonIgnore
    private OffsetDateTime lastModifiedDate;

}
