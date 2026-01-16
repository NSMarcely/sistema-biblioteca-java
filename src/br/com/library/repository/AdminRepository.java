package br.com.library.repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.library.model.Admin;
import br.com.library.model.interfaces.Exists;


public class AdminRepository implements Exists  {
	private final Map<String, Admin> admins;
	private static AdminRepository instance;
	
	private AdminRepository() {
		this.admins = new HashMap<> ();
		Admin root = new Admin("Master Administrator", "Pi314159Pi");
		root.setId("01101101");
		this.admins.put(root.getId(), root);
	}
	
	public static AdminRepository getInstance() {
		if(instance== null) {
			synchronized (AdminRepository.class) {
				if(instance== null) {
					instance= new AdminRepository();
				}
			}
			
		}
		return instance;
	}
	
	public Collection<Admin > returnAllAdmin( ) {
		return this.admins.values();
	}
	
	public void addAdmin (Admin admin) {
		this.admins.put(admin.getId(), admin);
	}
	
	@Override
	public boolean existence(String id) {
		return this.admins.containsKey(id);
	}
	
	public void printAdm() {
		System.out.println(":: Lista de Administradores ::");
		int accountant = 0;
		for(Admin i : returnAllAdmin()) {
			System.out.println(accountant + "-" + i);
			accountant ++;
		}
	}
	
}