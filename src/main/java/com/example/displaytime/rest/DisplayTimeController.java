package com.example.displaytime.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.displaytime.exception.InvalidTimeException;
import com.example.displaytime.service.DisplayTimeService;

@RestController
public class DisplayTimeController {
	
	@Autowired
	private DisplayTimeService displayTimeService;
	
	@GetMapping("/")
	public String conventTimeToWords(){
		String timeInWords = "";
		try {
			timeInWords = displayTimeService.conventTimeToWords();
		} catch (InvalidTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeInWords;
		}

	
}
