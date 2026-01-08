package com.learning.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.report.entity.LearningReport;

public interface LearningReportRepository extends JpaRepository<LearningReport,Long>{

}
