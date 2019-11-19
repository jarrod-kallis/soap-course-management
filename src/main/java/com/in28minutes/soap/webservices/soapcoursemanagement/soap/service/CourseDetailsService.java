package com.in28minutes.soap.webservices.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.in28minutes.soap.webservices.soapcoursemanagement.soap.bean.Course;

@Component
public class CourseDetailsService {

	private static List<Course> courses = new ArrayList<Course>();

	static {
		courses.add(new Course(1, "Spring", "10 Steps"));
		courses.add(new Course(2, "Spring MVC", "10 Examples"));
		courses.add(new Course(3, "Spring Boot", "6K Students"));
		courses.add(new Course(4, "Maven", "Most popular"));
	}

	// Get a single course's details
	@RequestMapping(value = "/ws/course", method = RequestMethod.GET)
	public Course getById(int id) {
		Course result = null;

		for (Course course : courses) {
			if (course.getId() == id) {
				result = course;
				break;
			}
		}

		return result;
	}

	// Get all courses
	public List<Course> getAll() {
		return courses;
	}

	// Delete a course
	public boolean deleteById(int id) {
		boolean result = false;

		for (int i = courses.size() - 1; i >= 0; i--) {
			Course course = courses.get(i);
			if (course.getId() == id) {
				courses.remove(i);
				result = true;
				break;
			}
		}

		return result;
	}

	// Update a course

	// Create a course
}
