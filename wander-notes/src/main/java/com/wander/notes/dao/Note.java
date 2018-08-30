package com.wander.notes.dao;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="notes")
public class Note {

	@Id
	private String id;
	private String username;
	private String title;
	private String description;
	private Date creationTime;
	private Date updateTime;
	public Note() {
		
	}
	public Note(String username, String title, String description, Date creationTime, Date updateTime) {
		super();
		this.username = username;
		this.title = title;
		this.description = description;
		this.creationTime = creationTime;
		this.updateTime = updateTime;
	}
	public Note(String id,String username, String title, String description, Date creationTime, Date updateTime) {
		super();
		this.id = id;
		this.username = username;
		this.title = title;
		this.description = description;
		this.creationTime = creationTime;
		this.updateTime = updateTime;
	}
	public String getId() {
		return id;
	}
	/*public void setId(String id) {
		this.id = id;
	}*/
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
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
