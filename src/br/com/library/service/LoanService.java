package br.com.library.service;
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
		if(this.loanRepository.existence(isbn)) {
			if(this.regularUserRepository.existence(id)) {
				Loan loan = this.loanRepository.returnLoan(id);
				loan.updatesLoan();
				this.loanRepository.remove(isbn);
			}
			else {
				System.out.println("O usuário não foi encontrado");
			}
		}
		else {
			System.out.println("O ISBN não foi localizado");
		}
	}
}
