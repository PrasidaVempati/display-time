package com.example.displaytime.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.displaytime.exception.InvalidTimeException;

@Service
public class DisplayTimeService {

	public String conventTimeToWords() throws InvalidTimeException{
		
		String[] single_digits = new String[]{" ", "one", "two", "three", "four", "five", 
				"six", "seven","eight","nine","ten", 
				"eleven",  "twelve", "thirteen",  "fourteen", 
				"fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
	        };
		String[] tens_multiple = new String[] {
	            "","", "twenty",  "thirty", "forty",
	            "fifty", "sixty", "seventy", "eighty", "ninety"
	        };
		
		StringBuilder dateInWords = new StringBuilder();
		
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
		String dateString = dateFormat.format(new Date()).toString();
		int hour = Integer.parseInt(dateString.substring(0, 2));
		int minute = Integer.parseInt(dateString.substring(3, 5));
		String aa = dateString.substring(6,8);
		
		if(hour == 12 && minute == 0 && aa.equals("pm")) {
			dateInWords.append("Midday");
		}else if(hour == 12 && minute == 0 && aa.equals("am")) {
			dateInWords.append("Midnight");
		}
		else {
		if(hour > 12 || hour < 0) {
			throw new InvalidTimeException("Invaid hour format");
		}
		else {
			dateInWords.append(single_digits[hour]);
		}
		
		if(minute > 59 || minute < 0) {
			throw new InvalidTimeException("Invaid minute format");
		}else if(minute < 20) {
			dateInWords.append(" ");
			dateInWords.append(single_digits[minute] + " " + aa.toUpperCase());
		}
		else {
			int u = minute % 10;
			int t = minute / 10;
			
			dateInWords.append(" ");
			dateInWords.append(tens_multiple[t] + " " + single_digits[u] + " " + aa.toUpperCase());
		}
		}
			
		return "it's " + dateInWords.toString();
	}
}
