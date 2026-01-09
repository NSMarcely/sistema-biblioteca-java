package br.com.library.model;
import java.time.LocalDate;


public class Loan {
	/*what does a book lending class have to do?
	//user - User(type) user 
	//book that is being borrowed - Book borrowedBook 
	//LocalDate startDate/ in "this.startDate" = startDate.now();
	//LocalDate endDate/ in this.endDate = endDate.plusDay(30)
	//boolean returned */
	private User id;
	private Book borrowedBook;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean returned;
	
	public Loan(User id,Book borrowedBook) {
		this.id = id;
		this.borrowedBook = borrowedBook;
		this.startDate = LocalDate.now();
		this.endDate = startDate.plusDays(30);
		this.returned = false;
	}
	
	public boolean updatesLoan() {
		returned = true;
		return returned;
	}
	
	
	public User getId() {
		return id;
	}
	public Book getBorrowedBook() {
		return borrowedBook;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public boolean isReturned() {
		return returned;
	}
	
}
