package com.nargilemag.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";
	
	// Specify name of a specific view that will be used to display the error:
	@ExceptionHandler(SQLException.class)
	public String databaseError(Model model, Exception e) {
		// Nothing to do.  Returns the logical view name of an error page, passed
		// to the view-resolver(s) in usual way.
		// Note that the exception is NOT available to this view (it is not added
		// to the model) but see using ModelAndView or 
		//"Extending ExceptionHandlerExceptionResolver" below.
		model.addAttribute("error", "Unable to retrieve or update your data in the database. "
				+ "This may be due to an issue in our server. "
				+ "Please try again and make sure you enter valid data.");
		return DEFAULT_ERROR_VIEW;
	}

	// Total control - setup a model and return the view name yourself. Or
	// consider subclassing ExceptionHandlerExceptionResolver (see below).
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it - like the OrderNotFoundException example
		// at the start of this post.
		// AnnotationUtils is a Spring Framework utility class.
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		// Otherwise setup and send the user to a default error-view.
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
}