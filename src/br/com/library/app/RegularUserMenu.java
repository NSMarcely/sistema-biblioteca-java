package br.com.library.app;
import java.util.Scanner;
import br.com.library.exception.InvalidPeriodException;
import br.com.library.service.RegularUserService;

public class RegularUserMenu {
	private RegularUserService regularUserService = new RegularUserService();
	private Scanner userInput = new Scanner(System.in);
	 public void userMenu (String userId, String userName) {
		 boolean finish = false;
			while(!finish) {
				regularUserService.viewProfile(userId);
				System.out.println("\n|1-Adicionar metas"
						+ "\n|2-Deslogar"
						+ "\n°Escolha uma opção:");
				int option = userInput.nextInt();
				userInput.nextLine();
				switch(option) {
				case 1 :
					try {
					System.out.println("Quantidade de livros que deseja ler: ");
					short objective = userInput.nextShort();
					userInput.nextLine();
					System.out.println("Período que a meta deve ser comprida (semana ou mês): ");
					String period = userInput.nextLine();
					System.out.println("Quantos dias ou semanas:");
					Short periodTime = userInput.nextShort();
					userInput.nextLine();
					regularUserService.increaseGoal(objective, period, periodTime, userId );
					}
					catch (InvalidPeriodException e) {
						System.out.println("Erro" + e.getMessage());
					}
					break;
				
				case 2 :
					System.out.printf("Tchau, atá a próxima %s!",userName);
					finish = true;
					break;
					
				default :
					System.out.println("Opção invalida");
					break;
				}
			}
	}
}
