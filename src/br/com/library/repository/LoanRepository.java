package br.com.library.repository;
import java.util.HashMap;
import java.util.Map;

import br.com.library.model.Loan;

public class LoanRepository {
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
		this.loans.put(loan.getBorrowedBook().getBookTitle(), loan);
	}
	
}
