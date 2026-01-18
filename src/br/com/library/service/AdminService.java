package br.com.library.service;
import java.util.Objects;

import br.com.library.app.AdminMenu;
import br.com.library.model.Admin;
import br.com.library.model.interfaces.Authenticatable;
import br.com.library.model.interfaces.Identification;
import br.com.library.repository.AdminRepository;

public class AdminService implements Identification, Authenticatable {
	AdminRepository adminRepository = AdminRepository.getInstance();
	
	public void registerAdmin (String name, String password) {
		String newId;
		do {
			newId = generator((byte)8);
		}
		while(this.adminRepository.existence(newId) || newId == null);
		Admin newAdmin = new Admin(name, password);
		newAdmin.setId(newId);
		this.adminRepository.addAdmin(newAdmin);
		this.adminRepository.write();
		System.out.println("O Administrador foi adicionado com sucesso!");
	
	}
	
	@Override
	public void login(String name, String password) {
		AdminMenu menu = new AdminMenu();
		adminRepository.returnAllAdmin().stream()
				.filter(admin -> Objects.equals(admin.getName(), name) && Objects.equals(admin.getPassword(), password))
				.findFirst()
				.ifPresentOrElse(admin -> {System.out.println("°Login feito com sucesso\n Bem-vindo(a)," + admin.getName() + "!");
					menu.adminMenu();
				},
				() -> System.out.println("Usuário ou senha incorreta, tente novamente!"));
	}
	
	public void load () {
		this.adminRepository.read();
		this.adminRepository.printAdm();
	}
	
}