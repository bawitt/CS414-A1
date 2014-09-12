package cs414.a1.bawitt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Project {
	
	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	private Company company;
	
	private Set<Worker> workersInvolved; 
	private Set<Qualification> requiredQual; //project required qual
	private ArrayList<Qualification> acquiredQual; //list of all worker quals within a project
	private Set<Qualification> missingQual; //required qual minus acquired qual
	
	Project(String n, Set<Worker> ws, Set<Qualification> qs, ProjectSize s, Company c){
		name = n;
		workersInvolved = new HashSet<Worker>();
		missingQual = new HashSet<Qualification>();
		acquiredQual = new ArrayList<Qualification>();
		requiredQual = qs;
		size = s;
		status = ProjectStatus.planned;
		company = c;
		for(Worker w : ws){
			if(company.getHiredWorkers().contains(w)) {
				workersInvolved.add(w);
				w.addProjectToWorker(this);
				this.getacquiredQual().addAll(w.getWorkerQual()); //add worker's quals to project acquired quals
			}
		}
	}

	Set<Qualification> missingQualifications(){
		missingQual = new HashSet<Qualification>();
		for(Qualification q : requiredQual){
			if(!acquiredQual.contains(q)) missingQual.add(q); 
		}
		return missingQual;
	}
	
	public void addWorkerToProject(Worker w){
		if (company.getHiredWorkers().contains(w) && !this.workersInvolved.contains(w) && isHelpful(w) && !w.isOverLoaded()){
			workersInvolved.add(w);
			w.addProjectToWorker(this);
			this.getacquiredQual().addAll(w.getWorkerQual()); //add worker's quals to project acquired quals
		}
	}
	
	boolean isHelpful(Worker w){
		missingQual = missingQualifications();
		for(Qualification q : w.getWorkerQual()){
			if(missingQual.contains(q)) return true;  //if missing qual contains a worker qual
		}
		return false;
	}
	
	public String toString(){
		return name+":"+workersInvolved.size()+":"+status.toString();
	}
	
	public boolean equals(Object o){
        if (o instanceof Project) {
            Project otherProject = (Project) o;
            if (name.equals(otherProject.getName()) && getStatus().equals(otherProject.getStatus())
            		&& getSize().equals(otherProject.getSize()) && getCompany().equals(otherProject.getCompany())){
            			return true;
            		}
        }
        return false;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String newName){
		name = newName;
	}
	public ProjectSize getSize(){
		return size;
	}
	public void setSize(ProjectSize newSize){
		size  = newSize;
	}
	public ProjectStatus getStatus(){
		return status;
	}
	public void setStatus(ProjectStatus newStatus){
		status = newStatus;
	}
	public Company getCompany(){
		return company;
	}
	public void setCompany(Company c){
		company = c;
	}
	public Set<Worker> getWorkersInvolved(){
		return workersInvolved;
	}
	public ArrayList<Qualification> getacquiredQual(){
		return acquiredQual;
	}
	public Set<Qualification> getRequiredQual(){
		return requiredQual;
	}
}
