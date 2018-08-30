package com.wander.notes.model;

import java.io.Serializable;

public class UpdateNoteApiRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5112657399646138258L;
	private String id;
	private String username;
	private String title;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
		return "UpdateNoteApiRequest [id=" + id + ", username=" + username + ", title=" + title + ", description="
				+ description + "]";
	}
}
