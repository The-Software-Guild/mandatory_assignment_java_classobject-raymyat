package com.dao;

import java.util.List;

import com.model.DVD;

public interface DVDLibrary {
	/**
	 * Add a DVD object to the text file
	 * @param dvd a DVD object to be added
	 * @return DVD object that's just added
	 * */
	DVD addDVD(DVD dvd) throws DVDLibraryException;
	
	/**
	 * Remove a DVD object based of given DVD id.
	 * @param dvdId an ID of DVD to remove.
	 * @return DVD object that is removed
	 * 		   Null if no such dvdId.
	 * */
	DVD removeDVD(int dvdId) throws DVDLibraryException;
	
	/**
	 * Get a DVD object based of given DVD id.
	 * @param dvdId an ID of DVD to remove.
	 * @return DVD object that is removed
	 * 		   Null if no such dvdId.
	 * */
	DVD getDVDId(int dvdId) throws DVDLibraryException;
	
	/**
	 * Edit information for an existing DVD in the collection
	 * @param dvdId an ID of DVD to edit.
	 * @param dvdField field of DVD to be changed. 
	 * @param update new detail of the dvdField.
	 * @return DVD object that is edited
	 * */
	DVD editDVD(int dvdId, String dvdField, String newValue) throws DVDLibraryException;
	
	/**
	 * Retrieve a list of DVDs in a collection.
	 * @return List of all DVDs
	 * */
	List<DVD> getAllDVDs() throws DVDLibraryException;
	
	/**
	 * Retrieve a DVD objects based of given DVD title.
	 * @param dvdId an ID of DVD for DVD details retrieval
	 * @return List of DVDs with the title
	 * 		   Null if no such title
	 * */
	List<DVD> searchDVD(String dvdTitle) throws DVDLibraryException;
	
	
	
	

}
