package com.learningplatform.course.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.learningplatform.course.dao.CourseDao;
import com.learningplatform.course.dtorequest.DtoRequest;
import com.learningplatform.course.dtoresponse.DtoResponse;
import com.learningplatform.course.entity.Course;

@Service
@Component
public class CourseService {

    @Autowired
    private CourseDao repository;

    

    public List<DtoResponse> getAllCourses() {
        return repository.findAll()
                .stream()
                .map(this::mapToDtoResponse)
                .collect(Collectors.toList());
    }

    public DtoResponse getCourseById(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found with id: " + id
                ));
        return mapToDtoResponse(course);
    }

    public DtoResponse addCourse(DtoRequest request) {
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setTrainerId(request.getTrainerId());

        Course saved = repository.save(course);
        return mapToDtoResponse(saved);
    }

    public DtoResponse updateCourse(Long id, DtoRequest request) {
        Course existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Course not found with id: " + id
                ));

        existing.setCourseName(request.getCourseName());
        existing.setDescription(request.getDescription());
        existing.setTrainerId(request.getTrainerId());

        Course updated = repository.save(existing);
        return mapToDtoResponse(updated);
    }

    public void deleteCourse(Long id) {
        repository.deleteById(id);
    }



    /**************   TRAINER  *********** */


    public DtoResponse updateCourseDescription(Long id, Long trainerId, DtoResponse description){

        Course course = repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));

        if(!course.getTrainerId().equals(trainerId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Not your course");
        }

        course.setDescription(description.getDescription());
        return mapToDtoResponse(repository.save(course));
    }


    public List<DtoResponse> getCourseByTrainer(Long trainerId){
        return repository.findByTrainerId(trainerId)
                         .stream().map(this::mapToDtoResponse)
                         .collect(Collectors.toList());
    }


    public List<DtoResponse> getTraineeCourse(Long trainerId){
        return repository.findByTrainerId(trainerId)
                            .stream().map(this::mapToDtoResponse)
                            .collect(Collectors.toList());
    }

    private DtoResponse mapToDtoResponse(Course course) {
        return new DtoResponse(
                course.getId(),
                course.getCourseName(),
                course.getDescription(),
                course.getTrainerId()
        );
    }

    
    
}
