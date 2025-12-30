package br.com.library.model;

public abstract class User {
	private String name;
	private String id;
	public User (String name, String id){
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}

}
