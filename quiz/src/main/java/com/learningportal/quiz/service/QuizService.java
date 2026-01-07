package com.learningportal.quiz.service;


import java.util.List;

import com.learningportal.quiz.dto.QuestionDto;
import com.learningportal.quiz.dto.QuizAttemptDto;
import com.learningportal.quiz.dto.QuizRequestDto;
import com.learningportal.quiz.entity.Quiz;


public interface QuizService {
	Quiz createQuiz(QuizRequestDto dto,Long trainerId);
	void addQuestions(Long quizId,List<QuestionDto> questions);
	void approveQuiz(Long quizId);
	void publishQuiz(Long quizId);
	int attemptQuiz(QuizAttemptDto attemptDto);

}
