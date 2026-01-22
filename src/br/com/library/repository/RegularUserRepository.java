package br.com.library.repository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.library.model.ReadingGoal;
import br.com.library.model.RegularUser;
import br.com.library.model.interfaces.Exists;
import br.com.library.model.interfaces.Repository;

public class RegularUserRepository implements Exists, Repository{
	private String usersFile = "user.txt";
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
	public void read () {
		File file = new File(this.usersFile);
		if(!file.exists()) return;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				String name = parts[0];
				String password = parts[1];
				String id = parts[2];
				float debt = Float.parseFloat(parts[3]);
				RegularUser regularUser = new RegularUser(name, password);
				regularUser.setDebt(debt);
				regularUser.setId(id);
				String objectiveStr = parts[4];
				if (!objectiveStr.equals("0") && !objectiveStr.equals("null")) {
					short objective = Short.parseShort(parts[4]);
					short progress = Short.parseShort(parts[5]);
					LocalDate startGoal = LocalDate.parse(parts[6]);
					LocalDate endGoal = LocalDate.parse(parts[7]);
					String period = parts[8];
					short periodTime = Short.parseShort(parts[9]);
					ReadingGoal readingGoal = new ReadingGoal(objective, period, periodTime);
					readingGoal.setProgress(progress);
					readingGoal.setStartGoal(startGoal);
					readingGoal.setEndGoal(endGoal);
					regularUser.setGoal(readingGoal);
				}
				else {
					regularUser.setGoal(null);
				}
				this.readers.put(id, regularUser);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void write () {
		File file = new File(usersFile);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			for(RegularUser i : this.readers.values()) {
				StringBuilder sb = new StringBuilder();
				sb.append(i.getName()).append(";").append(i.getPassword())
				.append(";").append(i.getId()).append(";").append(i.getDebt()).append(";");
				ReadingGoal readingGoal = i.getGoal();
				if(readingGoal != null) {
					sb.append(readingGoal.getObjective()).append(";")
	                  .append(readingGoal.getProgress()).append(";")
	                  .append(readingGoal.getStartGoal()).append(";")
	                  .append(readingGoal.getEndGoal()).append(";")
	                  .append(readingGoal.getPeriod()).append(";")
	                  .append(readingGoal.getPeriodTime());
				}
				else {
					sb.append("0;0;null;null;nenhum;0");
				}
				bw.write(sb.toString());
				bw.newLine();
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	public boolean existence(String id) {
		return this.readers.containsKey(id);
	}
	
    public void printRegularUser() {
    	if(this.readers.isEmpty()) {
    		System.out.println("Ainda não há usários cadrastados");
    	}
    	System.out.println(":: Lista de Leitores ::");
    	int accountant = 1;
    	for(RegularUser i : retunrAllRegularUser()) {
    		System.out.println(accountant + ":\n" + i);
    		accountant ++;
    	}
    }
    

}
