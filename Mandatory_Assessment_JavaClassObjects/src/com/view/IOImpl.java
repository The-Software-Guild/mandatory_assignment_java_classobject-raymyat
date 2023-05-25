package com.view;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.DateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.MPAA;

public class IOImpl implements IO{
	private final Scanner sc = new Scanner(System.in); 
	private final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Override
	public void print(String msg) {
		System.out.println(msg);
	}

	@Override
	public String readString() {
		boolean valid = true;
		String userString = null;
		do {
			String input = sc.nextLine();
			Pattern pattern = Pattern.compile("[\\t\\s]+"); 
	        Matcher matcher = pattern.matcher(input);
	        if(matcher.matches()|| input.isBlank()) {
	        	this.print("Not allowed to enter blank spaces only.");
	        	valid = false;
	        }else {
	        	userString = input;
	        	valid = true;
	        }
	        
		}while(!valid);
		return userString;
	}

	@Override
	public int readInteger() {
		boolean valid = true;
		int userInt = 0;
		do {
			try {
				String input = sc.nextLine();
				userInt = Integer.parseInt(input);
				valid = true;
			}catch (NumberFormatException e) {
                this.print("Wrong input. Enter integer.");
                valid = false;
            }
			
		}while(!valid);
		return userInt;
	}

	@Override
	public MPAA readMPAA() {
		boolean valid = true;
		MPAA userMpaa = null;
		do {
			String input = sc.nextLine();
			userMpaa = switch(input.toUpperCase()){
							case "G" -> MPAA.G;
							case "PG" -> MPAA.PG;
							case "PG13" -> MPAA.PG13;
							case "R" ->  MPAA.R;
							case "NC17" -> MPAA.NC17;
							default -> null;
							};
			if(userMpaa != null) {
				valid = true;
			}else{
				this.print("Enter a valid MPAA:");
	            valid = false;
	        }
		}while(!valid);
		return userMpaa;
	}

	@Override
	public LocalDate readDate() {
		LocalDate date = null;
		boolean valid = true;
		SimpleDateFormat df =  new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		do {
			String input = sc.nextLine();
			try {
				date = LocalDate.parse(input,formatter);
				valid = true;
			} catch (DateTimeParseException e) {
				this.print("Re-enter date in correct format");
				valid = false;
			}
			
		}while(!valid);
		return date;
	}

	@Override
	public String readOptions() {
		String userOption = null;
		boolean valid = true;
		do {
			String input = sc.nextLine();
			userOption = switch(input.toLowerCase()) {
						case "id" -> "id";
						case "title" -> "title";
						case "release date" -> "release date";
						case "mpaa" -> "mpaa";
						case "director" -> "director";
						case "studio" -> "studio";
						case "rating" -> "rating";
						default -> null;};
			if(userOption != null) {
				valid = true;
			}else {
				this.print("Wrong input. Re-enter the choice");
				valid = false;
			}
			
		} while(!valid);
		return userOption;
	}

}
