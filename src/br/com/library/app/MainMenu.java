package br.com.library.app;
import java.util.Scanner;

import br.com.library.service.AdminService;
import br.com.library.service.BookService;
import br.com.library.service.RegularUserService;

public class MainMenu {
	private RegularUserService regularUserService = new RegularUserService();
	private AdminService adminService = new AdminService();
	private BookService bookService = new BookService();
	private Scanner userInput = new Scanner(System.in);
	public void app() {
		bookService.load();
		boolean finish = false;
		while(!finish) {
			System.out.println("::Biblioteca::");
			System.out.println("\n|1-Cadastrar-se"		
					+ "\n|2-Logar"
					+ "\n|Sair do sistema"
					+ "\n°Escolha uma opção:");
			int option = userInput.nextInt();
			userInput.nextLine();
			switch(option) {
				case 1 :
					System.out.println("Nome:");
					String name = userInput.nextLine();
					System.out.println("Senha:");
					String password = userInput.nextLine();
					regularUserService.registerRegularUser(name, password);
					break;
				case 2 :
					boolean finish2 = false;
					while(!finish2) {
						System.out.println("\nLogar como:"
								+ "\n|1-Leitor"
								+ "\n|2-Adminstrador"
								+ "\n|3-Voltar"
								+ "\n°Escolha uma opção:");
						int option2 = userInput.nextInt();
						userInput.nextLine();
						switch(option2) {
						case 1 : 
							System.err.println("<Logar usuário>");
							System.out.println("Nome:");
							String userName = userInput.nextLine();
							System.out.println("Senha:");
							String userPassword = userInput.nextLine();
							regularUserService.login(userName, userPassword);
							break;
							
						case 2 :
							System.out.println("<Logar Administrador>");
							System.out.println("Nome:");
							String adminName = userInput.nextLine();
							System.out.println("Senha:");
							String adminPassword = userInput.nextLine();
							adminService.login(adminName, adminPassword);
							break;
							
						case 3:
							finish2 = true;
							break;
							
						default:
							System.out.println("°Opção inválida");
						}
					}
					break;
				case 3 :
					System.out.println("Saindo do sistema...");
					finish = true;
					break;
				default :
					System.out.println("Opção inválida");
			}
		}
		
	}
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		menu.app();
	}

}//O Pequeno Principe;Antoine de Saint-Exupery;96;9788520936412;Infantil;true
//"Master Administrator", "Pi314159Pi"