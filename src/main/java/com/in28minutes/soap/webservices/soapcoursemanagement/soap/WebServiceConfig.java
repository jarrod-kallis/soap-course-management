package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

// Enable Spring Web Services
@EnableWs
// Spring Configuration
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	// All web service requests sent to /ws will get caught by this servlet -
	// much like the dispatcher servlet (front controller) for SpringMVC
	@Bean
	public ServletRegistrationBean messasgeDispatchServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}

	// Generate a WSDL: courses.wsdl
	@Bean(name = "courses")
	public DefaultWsdl11Definition generateWsdlDefinition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();

		// Almost like an interface
		definition.setPortTypeName("CoursePort");
		// Namespace used in XSD
		definition.setTargetNamespace("soap-course-management/courses");
		// Listen to all web service request starting with this URL
		definition.setLocationUri("/ws");
		// Define what to generate the WSDL off of
		definition.setSchema(coursesSchema);

		return definition;
	}

	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

	// Security
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor interceptor = new XwsSecurityInterceptor();

		interceptor.setCallbackHandler(passwordValidationCallbackHandler());
		interceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));

		return interceptor;
	}

	@Bean
	public SimplePasswordValidationCallbackHandler passwordValidationCallbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();

		handler.setUsersMap(Collections.singletonMap("user", "password"));

		return handler;
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
}
