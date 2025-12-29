package br.com.library.model;

public abstract class User {
	private String name;
	private int id;
	public User (String name, int id){
		this.name = name;
		this.id = id;
	}
	//Qualquer coisa eu add os setters
	public String getName() {
		return this.name;
	}
	public int getId() {
		return this.id;
	}

}
