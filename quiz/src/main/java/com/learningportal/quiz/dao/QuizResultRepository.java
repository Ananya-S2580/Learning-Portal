package com.learningportal.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningportal.quiz.entity.QuizResult;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long>{

}
