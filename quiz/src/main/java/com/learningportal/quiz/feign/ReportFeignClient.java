package com.learningportal.quiz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learningportal.quiz.entity.QuizResult;

@FeignClient(name="REPORT-SERVICE")
public interface ReportFeignClient {
	@PostMapping
	void sendResult(@RequestBody QuizResult quizResult);
}
