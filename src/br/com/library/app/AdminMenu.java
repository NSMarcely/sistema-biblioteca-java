package br.com.library.app;
import java.util.Scanner;
import br.com.library.service.AdminService;
import br.com.library.service.BookService;
import br.com.library.service.LoanService;

public class AdminMenu {
	boolean finish = false;
	private Scanner userInput = new Scanner(System.in);
	private BookService bookService = new BookService();
	private LoanService loanService = new LoanService();
	private AdminService adminService = new AdminService();
	
	public void adminMenu() {
		while(!finish) {
			System.out.println(":: ADMINSTRADOR ::");
			System.out.println("\n1-Adicionar Livro\n2-Remover livro"
					+ "\n3-Registrar Administradores\n4-Registrar Empréstimo"
	
					+ "\n5-Registrar Devolução\n6-Sair"
					+ "\n°Opção:");
			int option = userInput.nextInt();
			userInput.nextLine();
			
			switch (option) {
			case 1 : 
				System.out.println("<Registrando Livro>");
				System.out.println("Título do livro:");
				String bookTitle = userInput.nextLine();
				System.out.println("Autor do livro:");
				String author = userInput.nextLine();
				System.out.println("Número de páginas:");
				short page = userInput.nextShort();
				userInput.nextLine();
				System.out.println("Tipo de livro:");
				String typeBook = userInput.nextLine();
				bookService.registerBook(bookTitle, author, page, typeBook);
				break;
				
			case 2 :
				System.out.println("<Removendo Livro>");
				System.err.println("ISBN do livro:");
				String isbn1 = userInput.nextLine();
				bookService.deleteBook(isbn1);
				break;
				
			case 3 :
				System.out.println("<Registrando Administrador>");
				System.out.println("Nome do Administrador:");
				String name = userInput.nextLine();
				System.out.println("Senha:");
				String password = userInput.nextLine();
				adminService.registerAdmin(name, password);
				break;
				
			case 4 :
				System.out.println("<Registrando empéstimo>");
				System.out.println("ISBN do livro:");
				String isbn2 = userInput.nextLine();
				System.out.println("ID do leitor:");
				String id1 = userInput.nextLine();
				loanService.registerLoan(isbn2, id1);
				break;
				
			case 5 :
				System.out.println("<Resgistrar devolução>");

				System.out.println("ISBN do livro:");
				String isbn3 = userInput.nextLine();
				System.out.println("ID do usuário:");
				String id2 = userInput.nextLine();
				loanService.finishLoan(isbn3, id2);
				break;
				
			case 6 :
				System.out.println("°Saindo do sitema, até a próxima...");
				finish = true;
				break;
				
			default:
				System.out.println("°Opção invalida, tente novamente!");
				break;
			}
		}
	}
}
