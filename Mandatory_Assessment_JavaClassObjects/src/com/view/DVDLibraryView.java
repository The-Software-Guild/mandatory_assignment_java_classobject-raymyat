package com.view;
import com.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DVDLibraryView {
    private IO userIO;
    
    public DVDLibraryView(IO userIO) {
		this.userIO = userIO;
	}
    
    public int displayMenu() {
    	userIO.print("=============== DVD Library ===============");
    	userIO.print("Enter one of the choices:");
    	userIO.print("1. Add a DVD");
		userIO.print("2. Remove a DVD");
		userIO.print("3. Edit information for existing DVD");
		userIO.print("4. List all DVDs");
		userIO.print("5. Display information for a particular DVD by ID");
		userIO.print("6. Search DVD by title");
		userIO.print("7. Exit");
		userIO.print("");
		
		userIO.print("Enter a number (1-7)");
		return userIO.readInteger();
	}
    
    private void displayMessage(String message) {
		userIO.print("=====================================================");
		userIO.print(message);
		userIO.print("=====================================================");
	}

	public DVD getNewDVDInfo() {
		userIO.print("=============== Add DVD ===============");
		userIO.print("Enter ID:");
		int id = userIO.readInteger();
		
		userIO.print("Enter title:");
		String title = userIO.readString();
		
		userIO.print("Enter release date(yyyy-MM-dd):");
		LocalDate releaseDate = userIO.readDate();
		
		userIO.print("Enter MPAARating(G, PG, PG13, R, NC17):");
		MPAA mpaaRating = userIO.readMPAA();
		
		userIO.print("Enter director's name");
		String directorName = userIO.readString();
		
		userIO.print("Enter studio's name");
		String studio = userIO.readString();

		userIO.print("Enter rating(0-10)");
		String rating = userIO.readString();
		
		
		DVD currentDVD = new DVD(id, title, releaseDate, mpaaRating, directorName, studio, rating);
		
		return currentDVD;
	}
	
	public void displayAddSuccessMessage() {
		displayMessage("DVD successfully added!");
	}
	
	public void displayAllDVD(List<DVD> dvdList) {
		userIO.print("=============== DVDs in the library ===============");
		if(dvdList.isEmpty()) {
			userIO.print("No DVD in the library.");
		}else {
			for(DVD dvd: dvdList) {
				displayDVD(dvd);
			}
		}
	}
	public void displayDVD(DVD dvd) {
		if(dvd != null) {
			userIO.print("====####===="+ "ID: " + dvd.getId()+" || Title: " + dvd.getTitle()+"====####====");
			userIO.print("Release Date: " + dvd.getReleaseDate()+
					     "\nMPAA Rating: " + dvd.getMpAArating()+
					     "\nDirector's Name: " + dvd.getDirectorName() + 
					     "\nStudio's Name: " + dvd.getStudioName() +
					     "\nUser Rating: " + dvd.getUserRating());
		}else {
			userIO.print("No such DVD in the library.");
		}
		
	}
	public void displayEditBanner() {
		userIO.print("=============== Edit DVD ===============");
	}
	public int getDVDbyID() {
		userIO.print("Enter DVD ID:");
		return userIO.readInteger();
	}
	public int printEditMenuAndGetSelection() {
		userIO.print("Edit Menu");
		userIO.print("1. Edit the ID");
		userIO.print("2. Edit the Title");
		userIO.print("3. Edit the Release date");
	    userIO.print("4. Edit the MPAA rating");
	    userIO.print("5. Edit the Director name");
	    userIO.print("6. Edit the Studio name");
	    userIO.print("7. Edit the User rating");

	    return userIO.readInteger();
	}
	public ArrayList<String> getEditInfo() {
		int editOption = 0;
		ArrayList<String> editInfo = new ArrayList<>();
		String newValue = null;
		String field = null;
		while(editOption < 1 || editOption > 7 ) {
			editOption = printEditMenuAndGetSelection();
			switch(editOption) {
				case 1:
					userIO.print("Enter new ID:");
					newValue = Integer.toString(userIO.readInteger());
					field = "id";
					break;
				case 2:
					userIO.print("Enter new title:");
					newValue = userIO.readString();
					field = "title";
					break;
				case 3:
					userIO.print("Enter new release date(yyyy-MM-dd):");
					LocalDate releaseDate = userIO.readDate();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        newValue = releaseDate.format(formatter);
			        field = "release date";
					break;
				case 4:
					userIO.print("Enter MPAARating(G, PG, PG13, R, NC17):");
					newValue = userIO.readMPAA().toString();
					field = "mpaa";
					break;
				case 5:
					userIO.print("Enter director's name");
					newValue = userIO.readString();
					field = "director";
					break;
				case 6:
					userIO.print("Enter studio's name");
					newValue = userIO.readString();
					field = "studio";
					break;
				case 7:
					userIO.print("Enter rating(0-10)");
					newValue = userIO.readString();
					field = "rating";
					break;
				default:
					userIO.print("Enter a number (1-7)");
			}
			
		}
		editInfo.add(field);
		editInfo.add(newValue);
		return editInfo;
	}

	public void displayEditSuccessMessage(DVD dvd) {
		if(dvd != null) {
			displayMessage("DVD successfully edited!");
		}else {
			displayMessage("DVD doesn't exist");
		}
	}
	public void displaySearchDVDBanner() {
		userIO.print("=============== Search dvd by title ===============");
	}
	public String getDVDbyTitle() {
		userIO.print("Enter DVD Title:");
		return userIO.readString();
	}
	
	public void displayRemoveDVDBanner() {
		userIO.print("=============== Remove DVD ===============");
	}
	public void displayDVDByIdBanner() {
		userIO.print("=============== Display information for a particular DVD by ID ===============");
	}
	
	public void displayRemoveMessage(DVD dvd) {
		if(dvd != null) {
			displayMessage("DVD successfully removed!");
		}else {
			displayMessage("DVD doesn't exist");
		}
	}
	public void displayExitMessage() {
		displayMessage("Program ended.");
	}

}
