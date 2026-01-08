package br.com.library.model;

public class Admin extends User {
	public Admin(String name,String password) {
		super(name, password);
	}
	public String toString() {
		return String.format("\nNome:%s\nID:%s\n",this.getName(), this.getId());
		}

}
