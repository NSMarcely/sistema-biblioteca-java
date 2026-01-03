package br.com.library.model;

import br.com.library.model.interfaces.Identification;

public abstract class User implements Identification{
	private String name;
	private String id;
	private String password;
	public User (String name, String password){
		this.name = name;
		this.id = generator((byte)7);
		this.password = password;
	}
	
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}
	public String getPassword() {
		return password;
	}

}
