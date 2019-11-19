package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.in28minutes.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;

import soap_course_management.courses.CourseDetails;
import soap_course_management.courses.DeleteCourseDetailsRequest;
import soap_course_management.courses.DeleteCourseDetailsResponse;
import soap_course_management.courses.GetAllCourseDetailsRequest;
import soap_course_management.courses.GetAllCourseDetailsResponse;
import soap_course_management.courses.GetCourseDetailsRequest;
import soap_course_management.courses.GetCourseDetailsResponse;
import soap_course_management.courses.Status;

@Endpoint
public class CourseDetailsEndpoint {
	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "soap-course-management/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse getCourseDetails(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();

		Course course = service.getById(request.getId());
		CourseDetails courseDetails = course.convert();

		response.setCourseDetails(courseDetails);

		return response;
	}

	@PayloadRoot(namespace = "soap-course-management/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse getAllCourseDetails(@RequestPayload GetAllCourseDetailsRequest request) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

		for (Course course : service.getAll()) {
			response.getCourseDetails().add(course.convert());
		}

		return response;
	}

	@PayloadRoot(namespace = "soap-course-management/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourse(@RequestPayload DeleteCourseDetailsRequest request) {
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();

		boolean success = service.deleteById(request.getId());

		response.setSuccess(success);
		response.setStatus(success == true ? Status.SUCCESS : Status.FAILURE);

		return response;
	}
}
