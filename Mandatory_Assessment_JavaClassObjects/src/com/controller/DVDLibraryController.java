package com.controller;

import java.util.ArrayList;

import com.dao.DVDLibrary;
import com.dao.DVDLibraryException;
import com.dao.DVDLibraryImpl;
import com.model.DVD;
import com.view.DVDLibraryView;

public class DVDLibraryController {
	
	private DVDLibraryView view;
	private DVDLibrary dao;
	
	public DVDLibraryController(DVDLibraryView view, DVDLibrary dao) {
		this.view = view;
		this.dao = dao;
	}
	
	public void runProgram() {
		boolean continueProgram = true;
		int userChoice = 0;
		try {
			while(continueProgram) {
				
				//Get the selection from user
				userChoice = displayMenuAndGetUserInput();
				
				switch(userChoice) {
					
					case 1:
						addDVD();
						break;
					case 2:
						removeDVD();
						break;
					case 3:
						editDVD();
						break;
					case 4:
						displayDVDs();
						break;
					case 5:
						displayDVDbyId();
						break;
					case 6:
						searchDVDsbyTitle();
						break;
						
					case 7:
						continueProgram = false;
						exitProgram();
						break;
					default:
						exitProgram();
						break;
				}
			}
		}catch(DVDLibraryException e) {
				System.out.println(e);
		}
		
	}
	
	private int displayMenuAndGetUserInput() {
		return view.displayMenu();
	}
	
	private void addDVD() throws DVDLibraryException{
		DVD dvd = view.getNewDVDInfo();
		dao.addDVD(dvd);
		view.displayAddSuccessMessage();
	}
	private void removeDVD() throws DVDLibraryException{
		view.displayRemoveDVDBanner();
		int dvdID = view.getDVDbyID();
		DVD dvd = dao.removeDVD(dvdID);
		view.displayRemoveMessage(dvd);
	}
	private void editDVD() throws DVDLibraryException{
		view.displayEditBanner();
		int dvdID = view.getDVDbyID();
		ArrayList<String> editInfo = view.getEditInfo();
		DVD dvd = dao.editDVD(dvdID, editInfo.get(0), editInfo.get(1));
		view.displayEditSuccessMessage(dvd);
	}
	
	private void displayDVDbyId() throws DVDLibraryException{
		view.displayDVDByIdBanner();
		int dvdID = view.getDVDbyID();
		DVD dvd = dao.getDVDId(dvdID);
		view.displayDVD(dvd);
	}
	private void searchDVDsbyTitle() throws DVDLibraryException{
		view.displaySearchDVDBanner();
		String title = view.getDVDbyTitle();
		view.displayAllDVD(dao.searchDVD(title));
	}
	private void displayDVDs() throws DVDLibraryException {
		view.displayAllDVD(dao.getAllDVDs());
	}
	
	private void exitProgram() {
		view.displayExitMessage();
	}
	

}
