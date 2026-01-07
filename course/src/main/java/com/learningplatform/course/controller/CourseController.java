package com.learningplatform.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learningplatform.course.dtorequest.DtoRequest;
import com.learningplatform.course.dtoresponse.DtoResponse;

import com.learningplatform.course.service.CourseService;



@RestController

public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/admin")
    public List<DtoResponse> getAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/admin/{id}")
    public DtoResponse getCourseById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @PostMapping("/admin")
    public DtoResponse addCourse(@RequestBody DtoRequest request) {
        return service.addCourse(request);
    }

    @PutMapping("/admin/{id}")
    public DtoResponse updateCourse(
            @PathVariable Long id,
            @RequestBody DtoRequest request) {
        return service.updateCourse(id, request);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
    }


    /****************** TRAINER ********************* */

    @GetMapping("/trainer/{trainerId}")
    public List<DtoResponse> getTrainerCourses(@PathVariable Long trainerId){
        return service.getCourseByTrainer(trainerId);
    }
    
    
    @PutMapping("/trainer/{id}")
    public DtoResponse updateCourseDescription(@PathVariable Long id, @RequestParam Long trainerId,@RequestBody  DtoResponse description) {
        
        return service.updateCourseDescription(id, trainerId, description);
    }


    // *********************TRAINEEEE************************
    
    @GetMapping("/trainee/{trainerId}")
    public List<DtoResponse> getTraineeCourse(@PathVariable Long trainerId){
        return service.getTraineeCourse(trainerId);
    }


}

    


