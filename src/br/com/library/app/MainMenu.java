package br.com.library.app;
import java.util.Scanner;
import br.com.library.service.RegularUserService;

public class MainMenu {
	private RegularUserService regularUserService = new RegularUserService();
	private Scanner userInput = new Scanner(System.in);
	public void app() {
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
							System.out.println("Nome:");
							String nameUser = userInput.nextLine();
							System.out.println("Senha:");
							String passwordUser = userInput.nextLine();
							regularUserService.loginRegularUser(nameUser, passwordUser);
							break;
						case 2 :
							System.out.println("°Ainda em desenvolvimento");
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

}
