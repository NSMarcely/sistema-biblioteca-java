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
					book.setAvailable(false);
					user.addReadingBooks(loan);		
					this.loanRepository.write();
					this.regularUserRepository.write();
					this.bookRepository.write();
					System.out.println("°O empréstimo foi feito com sucesso!");
					}
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
					Book book = this.bookRepository.returnBook(isbn);
					book.setAvailable(true);
					loan.setReturned(true);
					this.updateGoal(id);
					this.loanRepository.write();
					this.regularUserRepository.write();
					this.bookRepository.write();
					
				}
				else {
					System.out.printf("\nO livro não foi emprestado para o usuário '%s'", loan.getId().getName());
				}
			}
			else {
				System.out.println("\n°O usuário não foi encontrado");
			}
		}
		else {
			System.out.println("\n°O ISBN não foi localizado");
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
    	System.out.printf("\nAs metas do usuário '%s' foi atualiza! ", user.getName());
    }
    
    public void reportLoans () {
    	this.loanRepository.printLoan();
    }
    
    public void usersReport () {
    	this.regularUserRepository.printRegularUser();
    }
    
    public void reportBooks () {
    	this.bookRepository.printBooks();
    }
    
    public void load () {
    	this.loanRepository.read();
    }
    
}
