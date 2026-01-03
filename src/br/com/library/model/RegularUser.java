package br.com.library.model;
import java.util.HashMap;
import java.util.Map;

public class RegularUser extends User {
	private final Map<String, Book> readingBooks;
	private short debt;
	private ReadingGoal goal;
	public RegularUser(String name, String password) {
		super(name, password);
		this.readingBooks = new HashMap<>(); 
		this.debt = 0 ;
		this.goal = null;
		
	}
	public ReadingGoal getGoal() {
		return goal;
	}
	public short getDebt() {
		return debt;
	}
	public Map<String, Book> getReadingBooks() {
		return readingBooks;
	}
	public void setDebt(short debt) {
		this.debt = debt;
	}
	public void setGoal(ReadingGoal goal) {
		this.goal = goal;
	}
      
      
}
