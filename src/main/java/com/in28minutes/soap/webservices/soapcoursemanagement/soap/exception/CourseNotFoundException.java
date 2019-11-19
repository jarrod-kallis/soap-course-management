package com.in28minutes.soap.webservices.soapcoursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{soap-course-management/courses}COURSE_NOT_FOUND")
@SoapFault(faultCode = FaultCode.CLIENT)
public class CourseNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
