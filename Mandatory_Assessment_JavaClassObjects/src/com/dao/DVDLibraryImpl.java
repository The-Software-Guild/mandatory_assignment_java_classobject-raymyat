package com.dao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import com.model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class DVDLibraryImpl implements DVDLibrary{
	
	private Map<Integer, DVD> dvdMap = new HashMap<>();
	private final String DATE_FORMAT = "yyyy-MM-dd";
	private final String DVD_FILE = "src/DVD_Collection.txt";
	private final String DELIMITER = "::";
	
	@Override
	public DVD addDVD(DVD dvd) throws DVDLibraryException {
		loadDVDs();
		dvdMap.put(dvd.getId(), dvd);
		writeDVDs();
		return dvdMap.get(dvd.getId());
	}
	@Override
	public DVD removeDVD(int dvdId) throws DVDLibraryException {
		loadDVDs();
		DVD removeDVD = dvdMap.remove(dvdId);
		writeDVDs();
		return removeDVD;
	}
	@Override
	public DVD getDVDId(int dvdId) throws DVDLibraryException {
		// TODO Auto-generated method stub
		loadDVDs();
		DVD dvd = dvdMap.get(dvdId);
		return dvd;
	}
	
	@Override
	public List<DVD> getAllDVDs() throws DVDLibraryException {
		loadDVDs();
		return new ArrayList<DVD>(dvdMap.values());
	}
	@Override
	public List<DVD> searchDVD(String dvdTitle) throws DVDLibraryException {
		loadDVDs();
		ArrayList<DVD> dvdList = new ArrayList<>();
		dvdMap.forEach((key, value) -> {
	        if(value.getTitle().toLowerCase().contains(dvdTitle)) {
	        	dvdList.add(value);
	        }
	    });
		return dvdList;
	}
	@Override
	public DVD editDVD(int dvdId, String dvdField, String newValue) throws DVDLibraryException {
		loadDVDs();
		DVD dvd = dvdMap.get(dvdId);
		if(dvd != null) {
			switch(dvdField.toLowerCase()) {
				case "id": 
					dvd.setId(Integer.parseInt(newValue));
					dvdMap.remove(dvdId);
					break;
				case "title":
					dvd.setTitle(newValue);
					break;
				case "release date":
					LocalDate date = null;
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
						date = LocalDate.parse(newValue,formatter);
								
					} catch (DateTimeParseException e) {
						System.out.println("Failed to parse date");
					}
					dvd.setReleaseDate(date);
					break;
				case "mpaa":
					dvd.setMpAArating(MPAA.valueOf(newValue));
					break;
				case "director":
					dvd.setDirectorName(newValue);
					break;
				case "studio":
					dvd.setStudioName(newValue);
					break;
				case "rating":
					dvd.setUserRating(newValue);
					break;
				default:
					dvd = null;
			}
			dvdMap.put(dvd.getId(), dvd);
		}
		writeDVDs();
		return dvd;
		
	}
	
	private DVD unmarshallDVD(String dvdObjInfo) {
		String[] dvdData = dvdObjInfo.split(DELIMITER);
		DVD dvd = new DVD();
		dvd.setId(Integer.parseInt(dvdData[0]));
		dvd.setTitle(dvdData[1]);
		LocalDate date = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
			date = LocalDate.parse(dvdData[2],formatter);
		} catch (DateTimeParseException e) {
			System.out.println("Failed to parse date");
		}
		dvd.setReleaseDate(date);
		dvd.setMpAArating(MPAA.valueOf(dvdData[3]));
		dvd.setDirectorName(dvdData[4]);
		dvd.setStudioName(dvdData[5]);
		dvd.setUserRating(dvdData[6]);
		return dvd;
	}
	
	private String marshallDVD(DVD dvd) {
		String dvdData = dvd.getId() + DELIMITER;
		dvdData += dvd.getTitle() + DELIMITER;
		dvdData += dvd.getReleaseDate() + DELIMITER;
		dvdData += dvd.getMpAArating() + DELIMITER;
		dvdData += dvd.getDirectorName() + DELIMITER;
		dvdData += dvd.getStudioName() + DELIMITER;
		dvdData += dvd.getUserRating() + DELIMITER;
		return dvdData;
	}
	
	private void loadDVDs() throws DVDLibraryException{
		Scanner sc;
		try {
			sc = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DVDLibraryException("File doesn't exist", e);
		}
		String currLine;
		DVD currDVD;
		
		while(sc.hasNextLine()) {
			currLine = sc.nextLine();
			currDVD = unmarshallDVD(currLine);
			dvdMap.put(currDVD.getId(), currDVD);
		}
		
		sc.close();
	}
	
	public void writeDVDs() throws DVDLibraryException{
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileWriter(DVD_FILE));
		}catch(Exception  e) {
			throw new DVDLibraryException("Fail to save data", e);
		}
		String dvdData;
		List<DVD> dvdList = this.getAllDVDs();
		for(DVD dvd: dvdList) {
			dvdData = marshallDVD(dvd);
			output.println(dvdData);
			output.flush();
		}
		output.close();
	}
	
}
