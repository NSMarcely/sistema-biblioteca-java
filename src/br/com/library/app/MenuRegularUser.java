package br.com.library.app;
import java.util.Scanner;

import br.com.library.service.RegularUserService;

public class MenuRegularUser {
	private RegularUserService regularUserService = new RegularUserService();
	private Scanner userInput = new Scanner(System.in);
	 public void menuUser (String userId) {
		 boolean finish = false;
			while(!finish) {
				int option = userInput.nextInt();
				regularUserService.viewProfile(userId);
				System.out.println("\n|1-Adicionar metas"
						+ "\n|2-Deslogar");
				switch(option) {
				case 1 :
					System.out.println("Quantidade de livros que deseja ler: ");
					short objective = userInput.nextShort();
					userInput.nextLine();
					System.out.println("Período que a meta deve ser comprida (semana ou mês): ");
					String period = userInput.nextLine();
					System.out.println("Quantos dias ou semanas:");
					Short periodTime = userInput.nextShort();
					regularUserService.increaseGoal(objective, period, periodTime, userId );
					break;
				
				case 3 :
					break;
					
				}
			}
	}
}
