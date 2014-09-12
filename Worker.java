package cs414.a1.bawitt;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Worker{
	
	private String nickName;
	private int salary;
	private int maxWorkload=4;
	private Company company;
	
	private Set<Project> projectsInvolved; // = new HashSet<Project>();
	private Set<Qualification> workerQual; // = new HashSet<Qualification>();
	
	Worker(String nn, Set<Qualification> qs, Company c){
		nickName = nn;
		workerQual = qs;
		company = c;
		salary = 0;
		projectsInvolved = new HashSet<Project>();
	}
	
	boolean isOverLoaded(){
		int counter=0;
		for(Project p : projectsInvolved){
			if(p.getSize()==ProjectSize.big && p.getStatus()==ProjectStatus.active) counter++;
			if(p.getSize()==ProjectSize.medium && p.getStatus()==ProjectStatus.active) counter=counter+2;
		}
		if(counter>maxWorkload) return true;
		else return false;
	}
	
	public void addProjectToWorker(Project p){
		projectsInvolved.add(p);
	}
	
	public String toString(){
		return nickName+":"+projectsInvolved.size()+":"+workerQual.size()+":"+salary;
	}
	
	public boolean equals(Object o){
        if (o instanceof Worker) {
            Worker otherWorker = (Worker) o;
            if (nickName.equals(otherWorker.getNickName()) && salary==otherWorker.getSalary()
            	&& company.equals(otherWorker.getCompany())){
            return true;
            }
        }
        return false;
	}
		
	public String getNickName(){
		return nickName;
	}
	public void setNickName(String newNickName){
		nickName = newNickName;
	}	
	public int getSalary(){
		return salary;
	}
	public void setSalary(int newSalary){
		salary = newSalary;
	}
	public Company getCompany(){
		return company;
	}
	public void setCompany(Company c){
		company = c;
	}
	public Set<Project> getProjectsInvolved(){
		return projectsInvolved;
	}
	public Set<Qualification> getWorkerQual(){
		return workerQual;
	}
	public void setWorkerQual(Set<Qualification> q){
		this.workerQual = q;
	}

}
