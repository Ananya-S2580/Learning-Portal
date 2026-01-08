package com.learning.report.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LearningReport {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long reportId;
private Long userId;
private Long courseId;
private Long quizId;

private  int quizScore;
private String quizResult;

private double courseCompletion;
private String courseStatus;
private boolean certificateEligible;

private LocalDateTime generatedAt;

public LearningReport(Long reportId, Long userId, Long courseId, Long quizId, int quizScore, String quizResult,
		double courseCompletion, String courseStatus, boolean certificateEligible, LocalDateTime generatedAt) {
	super();
	this.reportId = reportId;
	this.userId = userId;
	this.courseId = courseId;
	this.quizId = quizId;
	this.quizScore = quizScore;
	this.quizResult = quizResult;
	this.courseCompletion = courseCompletion;
	this.courseStatus = courseStatus;
	this.certificateEligible = certificateEligible;
	this.generatedAt = generatedAt;
}


public LearningReport() {
	
}


public Long getReportId() {
	return reportId;
}

public void setReportId(Long reportId) {
	this.reportId = reportId;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public Long getCourseId() {
	return courseId;
}

public void setCourseId(Long courseId) {
	this.courseId = courseId;
}

public Long getQuizId() {
	return quizId;
}

public void setQuizId(Long quizId) {
	this.quizId = quizId;
}

public int getQuizScore() {
	return quizScore;
}

public void setQuizScore(int quizScore) {
	this.quizScore = quizScore;
}

public String getQuizResult() {
	return quizResult;
}

public void setQuizResult(String quizResult) {
	this.quizResult = quizResult;
}

public double getCourseCompletion() {
	return courseCompletion;
}

public void setCourseCompletion(double courseCompletion) {
	this.courseCompletion = courseCompletion;
}

public String getCourseStatus() {
	return courseStatus;
}

public void setCourseStatus(String courseStatus) {
	this.courseStatus = courseStatus;
}

public boolean isCertificateEligible() {
	return certificateEligible;
}

public void setCertificateEligible(boolean certificateEligible) {
	this.certificateEligible = certificateEligible;
}

public LocalDateTime getGeneratedAt() {
	return generatedAt;
}

public void setGeneratedAt(LocalDateTime generatedAt) {
	this.generatedAt = generatedAt;
}



}
