package com.view;
import java.time.LocalDate;
import java.util.Date;

import com.model.MPAA;
public interface IO {
	
	void print(String msg);
	
	String readString();
	
	int readInteger();
	
	MPAA readMPAA();
	
	LocalDate readDate();
	
	String readOptions();
}
