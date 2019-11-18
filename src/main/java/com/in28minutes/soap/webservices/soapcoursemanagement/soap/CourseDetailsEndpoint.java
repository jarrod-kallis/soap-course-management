package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import soap_course_management.courses.CourseDetails;
import soap_course_management.courses.GetCourseDetailsRequest;
import soap_course_management.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {

	@PayloadRoot(namespace = "soap-course-management/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse getCourseDetails(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();

		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Course 1");
		courseDetails.setDescription("Description of Course 1");

		response.setCourseDetails(courseDetails);

		return response;
	}
}
