package com.teksenz.studentservice.services.course;

import com.teksenz.studentservice.services.course.model.CourseDto;
import com.teksenz.studentservice.services.course.model.CoursePagedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@ConfigurationProperties(prefix = "teksenz.course", ignoreUnknownFields = false)
@Component
public class CourseServiceImpl implements CourseService {
    private final String COURSE_PATH = "/api/v1/course?courseState=ENROLLMENT";
    private final RestTemplate restTemplate;

    private String courseServiceHost;

    public void setCourseServiceHost(String courseServiceHost) {
        this.courseServiceHost = courseServiceHost;
    }

    public CourseServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }


    @Override
    public Optional<CourseDto> findCourseToBeEnrolled() {
        log.debug("Invoking Course service");
        CoursePagedList coursePagedList = restTemplate.getForObject(courseServiceHost+COURSE_PATH, CoursePagedList.class);
        if(coursePagedList.getTotalElements()>0) {
            return Optional.of(coursePagedList.getContent().get(0));
        }else {
            return Optional.empty();
        }

    }
}
