package com.learning.report.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.report.dto.CourseProgressDTO;
import com.learning.report.dto.QuizResultDTO;
import com.learning.report.entity.LearningReport;
import com.learning.report.repository.LearningReportRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ReportService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LearningReportRepository repo;

	@CircuitBreaker(name = "reportCB", fallbackMethod = "fallback")
	public LearningReport generateReport(Long userId, Long courseId, Long quizId) {
		String quizUrl = "http://QUIZ-SERVICE/quiz/result" + userId + "/" + quizId;
		String courseUrl = "http://COURSE-SERVICE/course/progress/" + userId + "/" + courseId;

		QuizResultDTO quiz = restTemplate.getForObject(quizUrl, QuizResultDTO.class);
		CourseProgressDTO course = restTemplate.getForObject(courseUrl, CourseProgressDTO.class);

		LearningReport report = new LearningReport();
		report.setUserId(userId);
		report.setCourseId(courseId);
		report.setQuizId(quizId);

		report.setQuizScore(quiz.getScore());
		report.setQuizResult(quiz.getScore() >= 40 ? "PASS" : "FAIL");
		report.setCourseCompletion(course.getCompletionPercentage());
		report.setCourseStatus(course.getCompletionPercentage() == 100 ? "COMPLETED" : "IN PROGRESS");

		report.setCertificateEligible(quiz.getScore() >= 40 && course.getCompletionPercentage() == 100);

		report.setGeneratedAt(LocalDateTime.now());

		return repo.save(report);

	}

	public LearningReport fallback(Long userId, Long courseId, Long quizId, Exception e) {
		LearningReport report = new LearningReport();
		report.setUserId(userId);
		report.setCourseId(courseId);
		report.setQuizId(quizId);
		report.setQuizResult("SERVICE DOWN");
		report.setCourseStatus("UNKNOWN");
		report.setGeneratedAt(LocalDateTime.now());

		return report;

	}
}
