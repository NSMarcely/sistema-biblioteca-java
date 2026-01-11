package br.com.library.app;
import java.util.Scanner;
import br.com.library.service.RegularUserService;

public class MainMenu {
	private RegularUserService regularUserService = new RegularUserService();
	private Scanner userInput = new Scanner(System.in);
	public void app() {
		boolean finish = false;
		while(!finish) {
			System.out.println("\n|1-Cadastrar-se"
					+ "\n2-Logar"
					+ "\nSair do sistema"
					+ "\n°Escolha uma opção:");
			int option = userInput.nextInt();
			userInput.nextLine();
			switch(option) {
				case 1 :
					String name = userInput.nextLine();
					String password = userInput.nextLine();
					regularUserService.registerRegularUser(name, password);
					break;
				case 2 :
					boolean finish2 = false;
					while(!finish2) {
						System.out.println("\nLogar como:"
								+ "\n1-Leitor"
								+ "\n2-Adminstrador"
								+ "\n°Escolha uma opção:");
						int option2 = userInput.nextInt();
						userInput.nextLine();
						switch(option2) {
						case 1 : 
							String nameUser = userInput.nextLine();
							String passwordUser = userInput.nextLine();
							regularUserService.loginRegularUser(nameUser, passwordUser);
							break;
						case 2 :
							System.out.println("Ainda em desenvolvimento");
							break;
						default:
							System.out.println("Opção inválida");
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

}
