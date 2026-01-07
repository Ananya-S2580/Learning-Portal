package com.learningplatform.course.dtoresponse;



public class DtoResponse {

    private Long id;
    private String courseName;
    private String description;
    private Long trainerId;

    public DtoResponse() {}

    public DtoResponse(Long id, String courseName, String description, Long trainerId) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.trainerId = trainerId;
    }

    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
