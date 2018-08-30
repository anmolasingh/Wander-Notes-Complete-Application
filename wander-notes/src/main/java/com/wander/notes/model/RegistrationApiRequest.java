package com.wander.notes.model;

import java.io.Serializable;

public class RegistrationApiRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8858420504774552610L;
	
	private String email;
	private String password;
	private String name;
	
	public RegistrationApiRequest() {
		super();
	}
	
	public RegistrationApiRequest(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "RegistrationApiRequest [email=" + email + ", password=" + password + ", name=" + name + "]";
	}
	
}
