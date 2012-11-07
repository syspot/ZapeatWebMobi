package br.com.zapeat.mobile.util;

import com.restfb.Facebook;

public class Usuario {

	@Facebook
	private String id;
	@Facebook
	private String name;
	@Facebook
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
