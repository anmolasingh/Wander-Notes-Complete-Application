package com.wander.notes.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {
	public User() {
		this.authorities = new ArrayList<String>();
	}
	
	public User(String username, String password, String name,
			boolean enabled, Date lastPasswordResetDate, List<String> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
	}
	
	@Id
	private String username;
	private String password;
	private String name;
	private boolean enabled;
	private Date lastPasswordResetDate;
	private List<String> authorities;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}
