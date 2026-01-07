package com.learningportal.quiz.controller;

import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningportal.quiz.dto.QuestionDto;
import com.learningportal.quiz.dto.QuizAttemptDto;
import com.learningportal.quiz.dto.QuizRequestDto;

import com.learningportal.quiz.entity.Quiz;
import com.learningportal.quiz.service.QuizService;
import com.learningportal.quiz.service.QuizServiceImpl;

@RestController
@RequestMapping("/quiz")
public class QuizController {

   
    private QuizServiceImpl quizService;
    

    public QuizController(QuizServiceImpl quizService) {
		this.quizService = quizService;
	}

    @PostMapping
    public Quiz createQuiz(@RequestBody QuizRequestDto dto, @RequestHeader("trainerId") Long trainerId) {
    	return quizService.createQuiz(dto, trainerId);
    	
    }
    
    @PostMapping("/{quizId}/questions")
    public void addQuestions(@PathVariable Long quizId, @RequestBody List<QuestionDto> questions) {
    	quizService.addQuestions(quizId, questions);
    }
    
    @PutMapping("/{quizId}/approve")
    public void approveQuiz(@PathVariable("quizId") Long quizId) {
    	quizService.approveQuiz(quizId);
    }
    
    @PutMapping("/{quizId}/publish")
    public void publishQuiz(@PathVariable("quizId") Long quizId) {
    	quizService.publishQuiz(quizId);
    }
    
    @PostMapping("/attempt")
    public int attemptQuiz(@RequestBody QuizAttemptDto attemptDto) {
    	return quizService.attemptQuiz(attemptDto);
    }
	
}