package br.com.library.repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Admin;


public class AdminRepository  {
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
	
	public void printAdm() {
		for(Admin i : returnAllAdmin()) {
			System.out.println(i);
		}
	}
	public static void main(String[] args) {
		AdminRepository adminRepository = AdminRepository.getInstance();
		adminRepository.printAdm();
	}
}