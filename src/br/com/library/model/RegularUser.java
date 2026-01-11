package br.com.library.model;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RegularUser extends User {
	private final Map<String, Loan> readingBooks;
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
	
	public void addReadingBooks (Loan loan) {
		this.readingBooks.put(loan.getBorrowedBook().getIsbn(), loan);
	}
	
	public Collection<Loan> allReturnReadingBooks (){
		return this.readingBooks.values();
		
	}
	public void removeReadingBooks (String isbn) {
		this.readingBooks.remove(isbn);
	}
	
	public ReadingGoal getGoal() {
		return goal;
	}
	public float getDebt() {
		return debt;
	}
	public Map<String, Loan> getReadingBooks() {
		return readingBooks;
	}
	public void setDebt(float debt) {
		this.debt = debt;
	}
	public void setGoal(ReadingGoal goal) {
		this.goal = goal;
	}
      
}
