package br.com.library.repository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Book;
import br.com.library.model.Loan;
import br.com.library.model.RegularUser;
import br.com.library.model.interfaces.Exists;
import br.com.library.model.interfaces.Repository;

public class LoanRepository implements Exists, Repository{
	private String loansFile = "loan.txt";
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
	public void read () {
		File file = new File(this.loansFile);
		if(!file.exists()) return;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				RegularUser id = RegularUserRepository.getInstance().returnRegularUser(parts[0]);
				Book borrowedBook = BookRepository.getInstance().returnBook(parts[1]);
				LocalDate startDate = LocalDate.parse(parts[2]);
				LocalDate endDate = LocalDate.parse(parts[3]);
				boolean returned = Boolean.parseBoolean(parts[4]);
				if(id != null && borrowedBook != null) {
					Loan loan = new Loan(id, borrowedBook);
					loan.setEndDate(endDate);
					loan.setReturned(returned);
					loan.setStartDate(startDate);
					id.addReadingBooks(loan);   
					if(!returned) {
						this.loans.put(borrowedBook.getIsbn(), loan);
					}
				}
 			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write () {
		File file = new File(this.loansFile);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			for(RegularUser regularUser : RegularUserRepository.getInstance().retunrAllRegularUser()) {
				for(Loan loan : regularUser.allReturnReadingBooks()) {
					bw.write(loan.getId() + ";" + 
							loan.getBorrowedBook() + ";" +
							loan.getStartDate() + ";" +
							loan.getEndDate() + ";" +
							loan.getReturnedStatus());
					bw.newLine();
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean existence(String borowedBook) {
		return this.loans.containsKey(borowedBook);
		}
	
}
