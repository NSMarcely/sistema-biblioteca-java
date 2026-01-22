package br.com.library.app;
import java.util.Scanner;
import br.com.library.service.AdminService;
import br.com.library.service.BookService;
import br.com.library.service.LoanService;

public class AdminMenu {
	boolean finish = false;
	private Scanner userInput = new Scanner(System.in);
	private final BookService bookService = new BookService();
	private final LoanService loanService = new LoanService();
	private final AdminService adminService = new AdminService();
	
	
	public void adminMenu() {
		while(!finish) {
			System.out.println(":: ADMINSTRADOR ::");
			System.out.println("\n|1-Adicionar Livro\n|2-Remover livro"
					+ "\n|3-Registrar Administradores\n|4-Registrar Empréstimo"
					+ "\n|5-Registrar Devolução\n|6-Ver relatório\n|7-Sair"
					+ "\n°Opção:");
			int option = this.userInput.nextInt();
			this.userInput.nextLine();
			
			switch (option) {
			case 1 : 
				System.out.println("\n<Registrando Livro>");
				System.out.println("Título do livro:");
				String bookTitle = this.userInput.nextLine();
				System.out.println("Autor do livro:");
				String author = this.userInput.nextLine();
				System.out.println("Número de páginas:");
				short page = this.userInput.nextShort();
				this.userInput.nextLine();
				System.out.println("Tipo de livro:");
				String typeBook = this.userInput.nextLine();
				bookService.registerBook(bookTitle, author, page, typeBook);
				break;
				
			case 2 :
				System.out.println("\n<Removendo Livro>");
				System.err.println("ISBN do livro:");
				String isbn1 = this.userInput.nextLine();
				bookService.deleteBook(isbn1);
				break;
				
			case 3 :
				System.out.println("\n<Registrando Administrador>");
				System.out.println("Nome do Administrador:");
				String name = this.userInput.nextLine();
				System.out.println("Senha:");
				String password = this.userInput.nextLine();
				adminService.registerAdmin(name, password);
				break;
				
			case 4 :
				System.out.println("\n<Registrando empéstimo>");
				System.out.println("ISBN do livro:");
				String isbn2 = this.userInput.nextLine();
				System.out.println("ID do leitor:");
				String id1 = this.userInput.nextLine();
				loanService.registerLoan(isbn2, id1);
				break;
				
			case 5 :
				System.out.println("\n<Resgistrar devolução>");
				System.out.println("ISBN do livro:");
				String isbn3 = this.userInput.nextLine();
				System.out.println("ID do usuário:");
				String id2 = this.userInput.nextLine();
				loanService.finishLoan(isbn3, id2);
				break;
			
			case 6 :
				boolean finish2 =  false;
				while(!finish2) {
					System.out.println("\n<Relatórios>");
					System.out.println("\n|1-Epréstimos\n|2-Usuários\n|3-Livros\n|4-Voltar ");
					int option2 = this.userInput.nextInt();
					this.userInput.nextLine();
					switch(option2) {
					case 1 :
						this.loanService.reportLoans();
						break;
					
					case 2 :
						this.loanService.usersReport();
						break;
					
					case 3 :
						this.loanService.reportBooks();
						break;
					case 4 :
						finish2= true;
						break;
					default:
						System.out.println("°Opção invalida, tente novamente!");
						break;
					}
				}
				break;
			case 7 :
				System.out.println("°Saindo do sitema, até a próxima...");
				finish = true;
				return;
				
			default:
				System.out.println("°Opção invalida, tente novamente!");
				break;
			}
		}
	}
}
