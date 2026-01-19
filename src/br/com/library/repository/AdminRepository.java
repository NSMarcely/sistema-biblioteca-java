package br.com.library.repository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Admin;
import br.com.library.model.interfaces.Exists;


public class AdminRepository implements Exists  {
	private final String Adminfile = "admin.txt";
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

	public void read () {
		File file = new File(this.Adminfile);
		if(!file.exists()) return;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;	
			while((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				String name = parts[0];
				String password = parts[1];
				String id = parts[2];
				Admin admin = new Admin(name, password);
				admin.setId(id);
				this.admins.put(id, admin);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void write() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.Adminfile))){
			for(Admin i : this.admins.values()) {
				bw.write(i.getName() + ";" + i.getId());
				bw.newLine();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
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