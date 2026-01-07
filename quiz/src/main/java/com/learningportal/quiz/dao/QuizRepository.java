package com.learningportal.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningportal.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

}

