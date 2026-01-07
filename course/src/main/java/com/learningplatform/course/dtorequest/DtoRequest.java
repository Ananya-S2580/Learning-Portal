package com.learningplatform.course.dtorequest;

import org.springframework.stereotype.Component;

@Component
public class DtoRequest {

    private String courseName;
    private String description;
    private Long trainerId;

    public DtoRequest() {}

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }
}

