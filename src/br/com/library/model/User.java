package br.com.library.model;

public abstract class User {
	private String name;
	private String id;
	private String password;
	public User (String name, String password){
		this.name = name;
		this.id = null;
		this.password = password;
	}
	
	public String toString() {
		return String.format("\nNome:%s\nID:%s\n",this.name, this.id);
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
	public void setId(String id) {
		this.id = id;
	}

}
