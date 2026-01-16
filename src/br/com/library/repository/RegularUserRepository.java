package br.com.library.repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.RegularUser;
import br.com.library.model.interfaces.Exists;

public class RegularUserRepository implements Exists{
	private static RegularUserRepository instance;
	private final Map<String, RegularUser> readers;
	
	private RegularUserRepository() {
		this.readers = new HashMap<>();
	}
	
	public static RegularUserRepository getInstance(){
		if(instance == null) {
			synchronized (RegularUserRepository.class) {
				if(instance == null) {
					instance = new RegularUserRepository();
				}
			}
		}
		return instance;
	}

	public void addRegularUser(RegularUser user) {
		this.readers.put(user.getId(), user);
	}
	

	public RegularUser returnRegularUser(String id) {
		return this.readers.get(id);
	}
	
	public Collection<RegularUser> retunrAllRegularUser(){
		return this.readers.values();
		}
	
	@Override 
	public boolean existence(String id) {
		return this.readers.containsKey(id);
	}
	
    public void printRegularUser() {
    	System.out.println(":: Lista de Leitores ::");
    	int accountant = 0;
    	for(RegularUser i : retunrAllRegularUser()) {
    		System.out.println(accountant + "-" + i);
    		accountant ++;
    	}
    }


}
