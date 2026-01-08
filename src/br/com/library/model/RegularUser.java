package br.com.library.model;
import java.util.HashMap;
import java.util.Map;

public class RegularUser extends User {
	private final Map<String, Book> readingBooks;
	private float debt;
	private ReadingGoal goal;
	public RegularUser(String name, String password) {
		super(name, password);
		this.readingBooks = new HashMap<>(); 
		this.debt = 0 ;
		this.goal = null;	
	}
	@Override
	public String toString() {
		return String.format("\nNome:%s"
				+ "\nID:%s"
				+ "\nDebito:R$ %.2f"
				+ "\nSuas metas:%s"
				,this.getName(), this.getId(), this.debt, this.goal);
	}
	
	public ReadingGoal getGoal() {
		return goal;
	}
	public float getDebt() {
		return debt;
	}
	public Map<String, Book> getReadingBooks() {
		return readingBooks;
	}
	public void setDebt(float debt) {
		this.debt = debt;
	}
	public void setGoal(ReadingGoal goal) {
		this.goal = goal;
	}
      
}
