package com.learningportal.quiz.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuizResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resultid;
	private Long quizId;
	private Long userId;
	private int score;

	public QuizResult() {
	}

	public QuizResult(Long resultid, Long quizId, Long userId, int score) {
		this.resultid = resultid;
		this.quizId = quizId;
		this.userId = userId;
		this.score = score;
	}

	public Long getResultid() {
		return resultid;
	}

	public void setResultid(Long resultid) {
		this.resultid = resultid;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
