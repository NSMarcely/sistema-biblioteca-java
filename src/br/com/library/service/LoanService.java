package br.com.library.service;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import br.com.library.model.Book;
import br.com.library.model.Loan;
import br.com.library.model.RegularUser;
import br.com.library.repository.BookRepository;
import br.com.library.repository.LoanRepository;
import br.com.library.repository.RegularUserRepository;

public class LoanService {
	private LoanRepository loanRepository = LoanRepository.getInstance();
	private BookRepository bookRepository = BookRepository.getInstance();
	private RegularUserRepository regularUserRepository = RegularUserRepository.getInstance();
	
	
	public void registerLoan (String isbn, String id) {
		if(this.bookRepository.existence(isbn)) {
			Book book = this.bookRepository.returnBook(isbn);
			if (book.isAvailable()) {
				if(this.regularUserRepository.existence(id)) {
					RegularUser user = this.regularUserRepository.returnRegularUser(id);
					Loan loan = new Loan(user, book);
					this.loanRepository.addLoan(loan);
					user.addReadingBooks(loan);				}
				else {
					System.out.println("°O usuário não foi encontrado");
				}
		    }
			else {
				System.out.println("°O livro não está disponível para empréstimo");
			}
		}
		else {
			System.out.println("°Livro não encontrado");
		}
	}
	
	public void finishLoan (String isbn, String id) {
		Loan loan = loanRepository.returnLoan(isbn);
		if(loan != null) {
			if(this.regularUserRepository.existence(id)) {
				if(loan.getId().getId().equals(id)) {
					loan.updatesLoan();
					this.loanRepository.remove(isbn);
					this.updateGoal(id);
				}
				else {
					System.out.printf("O livro não foi emprestado para o usuário '%s'", loan.getId().getName());
				}
			}
			else {
				System.out.println("°O usuário não foi encontrado");
			}
		}
		else {
			System.out.println("°O ISBN não foi localizado");
		}
	}
	
    public void updateGoal (String id) {
    	RegularUser user = this.regularUserRepository.returnRegularUser(id);
    	if(user.getGoal() == null) {
    		return;
    	}
    	long readingBooks = user.allReturnReadingBooks().stream()
    			.filter(loan -> loan.isReturned() )
    			.filter(loan -> !loan.getStartDate().isBefore(user.getGoal().getStartGoal()) )
    	        .count();
    	user.getGoal().setProgress((short) readingBooks);
    	System.out.printf("As metas do usuário '%s' foi atualiza! ", user.getName());
    }
    
}
