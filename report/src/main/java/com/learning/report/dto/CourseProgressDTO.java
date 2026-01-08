package com.learning.report.dto;

public class CourseProgressDTO {
private double completionPercentage;


public CourseProgressDTO() {
	super();
}

public CourseProgressDTO(double completionPercentage) {
	super();
	this.completionPercentage = completionPercentage;
}

public double getCompletionPercentage() {
	return completionPercentage;
}

public void setCompletionPercentage(double completionPercentage) {
	this.completionPercentage = completionPercentage;
}

}
