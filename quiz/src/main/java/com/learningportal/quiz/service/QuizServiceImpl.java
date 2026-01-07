package com.learningportal.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learningportal.quiz.dao.QuestionRepository;
import com.learningportal.quiz.dao.QuizRepository;
import com.learningportal.quiz.dao.QuizResultRepository;
import com.learningportal.quiz.dto.QuestionDto;
import com.learningportal.quiz.dto.QuizAttemptDto;
import com.learningportal.quiz.dto.QuizRequestDto;

import com.learningportal.quiz.entity.Question;
import com.learningportal.quiz.entity.Quiz;
import com.learningportal.quiz.entity.QuizResult;
import com.learningportal.quiz.feign.ReportFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class QuizServiceImpl implements QuizService {

	private QuizRepository quizRepository;

	private QuestionRepository questionRepository;

	private QuizResultRepository quizresultRepo;

	//private RestTemplate restTemplate;
	private ReportFeignClient reportFeignClient;
	
	

	public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository,
			QuizResultRepository quizresultRepo, ReportFeignClient reportFeignClient) {
		this.quizRepository = quizRepository;
		this.questionRepository = questionRepository;
		this.quizresultRepo = quizresultRepo;
		this.reportFeignClient= reportFeignClient;
		//this.restTemplate = restTemplate;
	}

//	public QuizServiceImpl() {
//	}

	@Override
	public Quiz createQuiz(QuizRequestDto dto, Long trainerId) {
		Quiz quiz = new Quiz();
		quiz.setCourseId(dto.getCourseId());
		quiz.setTitle(dto.getTitle());
		quiz.setTrainerId(trainerId);
		quiz.setStatus("DRAFT");
		return quizRepository.save(quiz);
	}

	@Override
	public void addQuestions(Long quizId, List<QuestionDto> questions) {
		for (QuestionDto dto : questions) {
			Question q = new Question();
			q.setQuizId(quizId);
			q.setOptionA(dto.getOptionA());
			q.setOptionB(dto.getOptionB());
			q.setOptionC(dto.getOptionC());
			q.setOptionD(dto.getOptionD());
			q.setCorrectOption(dto.getCorrectAnswer());
			questionRepository.save(q);
		}
	}

	@Override
	public void approveQuiz(Long quizId) {
		Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Invalid Id"));
		quiz.setStatus("APPROVED");
		quizRepository.save(quiz);
	}

	@Override
	public void publishQuiz(Long quizId) {
		Quiz quiz = quizRepository.findById(quizId).orElseThrow();
		quiz.setStatus("PUBLISHED");
		quizRepository.save(quiz);
	}

	@CircuitBreaker(name = "reportService", fallbackMethod = "reportFallback")
	@Override
	public int attemptQuiz(QuizAttemptDto attemptDto) {
		List<Question> questions = questionRepository.findByQuizId(attemptDto.getQuizId());
		int score = 0;
		for (Question q : questions) {
			String answer = attemptDto.getAnswers().get(q.getQuestionId());
			if (q.getCorrectOption().equalsIgnoreCase(answer)) {
				score++;
			}

		}
		QuizResult quizResult = new QuizResult();
		quizResult.setQuizId(attemptDto.getQuizId());
		quizResult.setUserId(attemptDto.getUserId());
		quizResult.setScore(score);
		quizresultRepo.save(quizResult);

	//	restTemplate.postForObject("http://REPORT-SERVICE/report", quizResult, String.class);
		reportFeignClient.sendResult(quizResult);

		return score;

	}

	public int reportFallback(QuizAttemptDto attemptDto, Throwable ex) {
		System.out.println("Report Service is Down.");

		return -1;
	}
}