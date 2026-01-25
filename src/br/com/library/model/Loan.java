package br.com.library.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Loan {
	/*what does a book lending class have to do?
	//user - User(type) user 
	//book that is being borrowed - Book borrowedBook 
	//LocalDate startDate/ in "this.startDate" = startDate.now();
	//LocalDate endDate/ in this.endDate = endDate.plusDay(30)
	//boolean returned */
	private RegularUser id;
	private Book borrowedBook;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean returned;
	
	public Loan(RegularUser id,Book borrowedBook) {
		this.id = id;
		this.borrowedBook = borrowedBook;
		this.startDate = LocalDate.now();
		this.endDate = startDate.plusDays(30);
		this.returned = false;
	}
	
	public String getReturnedStatus() {
		String description = this.returned ? "Devolvido" : "Pendente";
		return description;
	}
	
	public String toString () {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("\n-----------------------------------------------"
				+ "\nID do usuário:%s"
				+ "\nLivro:%s"
				+ "\nEmpréstimo realizado em:%s"
				+ "\nData da devolução:%s"
				+ "\nStatus de devolução:%s"
				+ "\n-----------------------------------------------\n",
				this.id.getId(), this.borrowedBook.toString(), 
				startDate.format(dateFormat), endDate.format(dateFormat), getReturnedStatus()) ;
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

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	

 
	

}
