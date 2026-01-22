package br.com.library.app;
import java.util.Scanner;
import br.com.library.exception.InvalidPeriodException;
import br.com.library.service.LoanService;
import br.com.library.service.RegularUserService;

public class RegularUserMenu {
	private RegularUserService regularUserService = new RegularUserService();
	private LoanService loanService = new LoanService();
	
	private Scanner userInput = new Scanner(System.in);
	 public void userMenu (String userId, String userName) {
		 boolean finish = false;
			while(!finish) {
				System.out.println("\n::BIBLIOFIL::\n");
				regularUserService.viewProfile(userId);
				
				System.out.println("\n|1-Adicionar metas"
						+ "\n|2-Livros do acervo"
						+ "\n|3-Atividades"
						+ "\n|4-Deslogar"
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
					System.out.println("Quantas semanas ou meses:");
					Short periodTime = userInput.nextShort();
					userInput.nextLine();
					regularUserService.increaseGoal(objective, period, periodTime, userId );
					}
					catch (InvalidPeriodException e) {
						System.out.println("Erro" + e.getMessage());
					}
					break;
					
				case 2 :
					this.loanService.reportBooks();;
					break;
				
				case 3 :
					this.regularUserService.viewReadingBooks(userId);
					break;
					
				case 4 :
					System.out.printf("°Tchau, atá a próxima %s!",userName);
					finish = true;
					return;
					
				default :
					System.out.println("°Opção invalida, tente novamente!");
					break;
				}
			}
	}
}
