package br.com.library.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.library.exception.InvalidPeriodException;
public class ReadingGoal {
	/*Oque vamos ter nas metas:
	 Objetivo- short, ex:5 livros
	 progresso- short, ex: 2 livros já lidos 
	 TEMPO:
	 quando a meta iniciou- LocalDate now(),
	 ele vai acontar apartir do momento que o user acionar as metas
	 quando a meta terminou- LocalDate
	 limite/periodo que  a meta vai ser comprida- mês ou semana 
	 */
	private short objective;
	private short progress;
	private LocalDate startGoal;
	private LocalDate endGoal;
	private String period;
	private short periodTime;
	
	//Here is the constructor 
	public ReadingGoal(short objective, String period, short periodTime) {
		this.objective = objective;
		this.period = period;
		this.periodTime =  periodTime;
		this.progress = 0;
		this.startGoal = LocalDate.now();
		this.endGoal = deadLine();
	}
	//Here is the instance method 
	public LocalDate deadLine() {
		if (this.period.matches("(?i)m[êe]s")) {
			return this.startGoal.plusMonths(this.periodTime);	
			}
		else if(this.period.equalsIgnoreCase("semana")) {
			return this.startGoal.plusWeeks(this.periodTime);
		}
		else {

			throw new InvalidPeriodException("Entrada inválida, a entrada deve ser as palavras 'mês' ou 'semana'");
		}
	}
		
	public String toString() {
		LocalDate today = LocalDate.now();
		long dataMissing = ChronoUnit.DAYS.between(today, this.endGoal);
		return String.format("\nLivros já lidos: %d | Meta de leitura:%d (faltam %d dias)", this.progress, this.objective, dataMissing);
	}
	//Here are the getters and setters methods 
	public short getObjective() {
		return objective;
	}
	public short getProgress() {
		return progress;
	}
	public LocalDate getStartGoal() {
		return startGoal;
	}
	public LocalDate getEndGoal() {
		return endGoal;
	}
	public String getPeriod() {
		return period;
	}
	public void setProgress(short progress) {
		this.progress = progress;
	}
}
