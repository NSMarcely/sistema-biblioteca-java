package br.com.library.service;
import br.com.library.app.RegularUserMenu;
import br.com.library.model.ReadingGoal;
import br.com.library.model.RegularUser;
import br.com.library.model.interfaces.Identification;
import br.com.library.repository.RegularUserRepository;

public class RegularUserService implements Identification {
	private RegularUserRepository readre = RegularUserRepository.getInstance();
	
	public void registerRegularUser (String name, String password) {
		RegularUser newUser = new RegularUser(name,password);
		if(this.readre.existence(newUser.getId()) || newUser.getId() == null){
			boolean sucess = false;
			while(!sucess) {
				String newId = generator((byte)7);
				newUser.setId(newId);
				if(!this.readre.existence(newUser.getId())) {
					sucess = true;
				}
			}
		}
		this.readre.addRegularUser(newUser);
		System.out.printf("\n°Cadastro concluído com sucesso!\n°ID gerado:%s",newUser.getId());
	}

	public void increaseGoal (short objective, String period, short periodTime, String userId) {
		if(this.readre.existence(userId)) {
			ReadingGoal newGoal = new ReadingGoal(objective, period, periodTime);
			RegularUser user = this.readre.returnRegularUser(userId);
			if(user.getGoal() == null) {
				user.setGoal(newGoal);
				System.out.println("\n°Meta adiciona com sucesso!");
			}
		}
		else {
			System.out.println("\n°Usuário não indentificado");
		}		
	}
	
	public void viewProfile(String isbn) {
		if(readre.existence(isbn)){
			RegularUser user = this.readre.returnRegularUser(isbn);		
			System.out.println(user.toString());
		}
		else {
			System.out.println("\n°Usuário não indentificado");
		}
	}
	
	public void loginRegularUser (String name, String password) {
		if(this.readre.retunrAllRegularUser().isEmpty()) {
			System.out.println("\n°Nenhum usário cadrastado");
			return;
		}
		for(RegularUser user : this.readre.retunrAllRegularUser()) {
			if(user.getName().equals(name)) {
				if(user.getPassword().equals(password)) {
					System.out.println("\n°Login feito com sucesso!");
					System.out.println("\nBem-vido(a) ao seu bibliofil, " + user.getName());
					RegularUserMenu regularUserMenu = new RegularUserMenu();
					regularUserMenu.userMenu(user.getId(), user.getName());
					return;
					
				}
				else {
					System.out.println("\n°Senha incorreta:(");
				}
			}
			else {
				System.out.println("\n°Usuário não indentificado");
			}
		}
	}
	 
	
}
