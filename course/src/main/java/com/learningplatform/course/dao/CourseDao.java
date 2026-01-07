package com.learningplatform.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningplatform.course.entity.Course;
import java.util.List;


public interface CourseDao extends JpaRepository<Course,Long>{
    List<Course> findByTrainerId(Long trainerId);
    
}
