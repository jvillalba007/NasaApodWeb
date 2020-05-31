package com.nasa_apod.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nasa_apod.spring.nasa.NasaRest;
import com.nasa_apod.spring.pages.Pages;

@Controller
@RequestMapping("/")
public class ControllerSping {

	@Autowired
	@Qualifier("NasaRest")
	NasaRest nasaREST;

	@GetMapping(path = "/")
	public ModelAndView Home() {
		ModelAndView modelAndView = new ModelAndView(Pages.Home);
		modelAndView.addObject("nasa", nasaREST.getNasaAPODS());
		return modelAndView;
	}

	@GetMapping(path = "/ApodDay")
	public ModelAndView ApodDay() {
		ModelAndView modelAndView = new ModelAndView(Pages.ApodDay);
		modelAndView.addObject("nasa", nasaREST.getNasaAPODToday());
		return modelAndView;
	}

}
