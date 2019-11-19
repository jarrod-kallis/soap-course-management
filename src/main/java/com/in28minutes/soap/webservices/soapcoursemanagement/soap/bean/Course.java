package com.in28minutes.soap.webservices.soapcoursemanagement.soap.bean;

import soap_course_management.courses.CourseDetails;

public class Course {
	private int id;
	private String name;
	private String description;

	public Course(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseDetails convert() {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(this.getId());
		courseDetails.setName(this.getName());
		courseDetails.setDescription(this.getDescription());

		return courseDetails;
	}

	@Override
	public String toString() {
		return String.format("Course [id=%s, name=%s, description=%s]", id, name, description);
	}

}
