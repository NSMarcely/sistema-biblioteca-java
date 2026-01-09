package br.com.library.repository;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Loan;
import br.com.library.model.interfaces.Exists;

public class LoanRepository implements Exists{
	private static LoanRepository instance;
	private final Map<String, Loan> loans;
	
	private LoanRepository() {
		this.loans = new HashMap<>();
	}
	
	public static LoanRepository getInstance() {
		if(instance == null) {
			synchronized (LoanRepository.class) {
				if(instance == null) {
					instance = new LoanRepository();
				}
			}
		}
		return instance;
	}
	
	public void addLoan (Loan loan) {
		this.loans.put(loan.getBorrowedBook().getIsbn(), loan);
	}
	
	public Loan returnLoan(String borrowedBook) {
		return this.loans.get(borrowedBook);
		
	}
	public void remove (String borrowedBook) {
		this.loans.remove(borrowedBook);
	}
	
	@Override
	public boolean existence(String borowedBook) {
		return this.loans.containsKey(borowedBook);
		}
	
}
