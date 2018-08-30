package com.wander.notes.model;

import java.io.Serializable;

public class AddNoteApiRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119179822360667512L;
	private String username;
	private String title;
	private String description;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "AddNoteApiRequest [username=" + username + ", title=" + title + ", description=" + description + "]";
	}
	
	/*@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date creationDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date lastUpdateDate;*/
	
	
	
}
