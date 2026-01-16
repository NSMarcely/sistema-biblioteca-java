package br.com.library.service;
import br.com.library.model.Admin;
import br.com.library.model.interfaces.Identification;
import br.com.library.repository.AdminRepository;

public class AdminService implements Identification {
	AdminRepository adminRepository = AdminRepository.getInstance();
	
	public void registerAdmin (String name, String password) {
		Admin newAdmin = new Admin(name, password);
		boolean sucess = false;
		while(!sucess){
			if(this.adminRepository.existence(newAdmin.getId()) || newAdmin.getId() == null) {
				String newid = generator((byte)8);
				newAdmin.setId(newid);
			}
			else {
				sucess = true;
			}
		}
	this.adminRepository.addAdmin(newAdmin);
	System.out.println("O Administrador foi adicionado com sucesso!");
		
	}
}
