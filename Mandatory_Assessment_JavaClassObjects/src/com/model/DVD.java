package com.model;

import java.time.LocalDate;
import java.util.Date;

public class DVD {
	int id;
	String title;
	LocalDate releaseDate;
	MPAA mpAArating;
	String directorName;
	String studioName;
	String userRating;
	public DVD() {}
	public DVD(int id, String title, LocalDate releaseDate, MPAA mpAArating, String directorName, String studioName,
			String userRating) {
		super();
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.mpAArating = mpAArating;
		this.directorName = directorName;
		this.studioName = studioName;
		this.userRating = userRating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public MPAA getMpAArating() {
		return mpAArating;
	}
	public void setMpAArating(MPAA mpAArating) {
		this.mpAArating = mpAArating;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getStudioName() {
		return studioName;
	}
	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	@Override
	public String toString() {
		return "DVD [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + ", mpAArating=" + mpAArating
				+ ", directorName=" + directorName + ", studioName=" + studioName + ", userRating=" + userRating + "]";
	}
	
}
