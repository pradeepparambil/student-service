package com.teksenz.studentservice.web.mapper;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Timestamp timestamp){
        if(timestamp != null){
            LocalDateTime dateTime = timestamp.toLocalDateTime();
            return OffsetDateTime.of(dateTime.getYear(),dateTime.getMonthValue(),dateTime.getDayOfMonth()
                    ,dateTime.getHour(),dateTime.getMinute(),dateTime.getSecond(),dateTime.getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }
    public Timestamp asTimeStamp(OffsetDateTime offsetDateTime){
        if(offsetDateTime != null){
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }

}
