package com.learning.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.report.entity.LearningReport;
import com.learning.report.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
@Autowired
private ReportService service;
@PostMapping("/generate/{userId}/{courseId}/{quizId}")
public LearningReport generate(@PathVariable Long userId,@PathVariable Long courseId,@PathVariable Long quizId) {
	return service.generateReport(userId,courseId,quizId);



    
    
}

}
