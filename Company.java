package cs414.a1.bawitt;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Company {

	private String name;
	
	private Set<Worker> hireableWorkers; 
	private Set<Worker> hiredWorkers; 
	private HashSet<Project> companyProjects; 
	//private Set<Qualification>	companyQual = new HashSet<Qualification>(); //update
	
	//constructors
	Company(String input){ 
		name = input;
		hireableWorkers = new HashSet<Worker>();
		hiredWorkers = new HashSet<Worker>();
		companyProjects = new HashSet<Project>();
	}
	Company(){
		name = "";
		hireableWorkers = new HashSet<Worker>();
		hiredWorkers = new HashSet<Worker>();
		companyProjects = new HashSet<Project>();
		//companyQual = new HashSet<Qualification>();
	}
	
	public void hire(Worker w){
		if (!hiredWorkers.contains(w) && hireableWorkers.contains(w)){ //if worker has been created and not yet hired
			hiredWorkers.add(w);
			for(Project p : companyProjects){
				if(p.getSize()==ProjectSize.small && (p.getStatus()==ProjectStatus.planned || p.getStatus()==ProjectStatus.suspended)){
					p.addWorkerToProject(w);
					start(p);
					if(w.isOverLoaded()) break;
				}
			}
		}
	}
	
	public void fire(Worker w){
		if(hiredWorkers.contains(w)) {
			for(Project p : companyProjects){
				if(p.getWorkersInvolved().contains(w)){
					p.getWorkersInvolved().remove(w);
					p.getacquiredQual().removeAll(w.getWorkerQual());
					if(p.getStatus()==ProjectStatus.active && !p.missingQualifications().isEmpty()) p.setStatus(ProjectStatus.suspended);
				}		
			}
			for(Qualification q : w.getWorkerQual()){
				q.removeWorkerFromQual(w);
			}
		w.getProjectsInvolved().clear();
		hiredWorkers.remove(w);
		}
	}
	
	public void start(Project p){
		if(p.missingQualifications().isEmpty()) p.setStatus(ProjectStatus.active); //if all qualifications exist, start
	}
	
	public void finish(Project p){
		if (p.getStatus() == ProjectStatus.active){
			p.setStatus(ProjectStatus.finished);
			for(Worker w : p.getWorkersInvolved()){
				w.getProjectsInvolved().remove(p);  //remove project from worker's set
			}
			p.getWorkersInvolved().clear();	//clear project workers
		}
	}
	
	public Worker createWorker(String nn, Set<Qualification> qs){ 
		Worker w = new Worker(nn, qs, this);
		hireableWorkers.add(w);
		for(Qualification q : w.getWorkerQual()){
			q.addWorkerToQual(w);  //add each worker qualification to specific qual set.
		}
		return w;
	}
	
	public Project createProject(String n, Set<Worker> ws, Set<Qualification> qs, ProjectSize s)
	{
		Project p = new Project(n, ws, qs, s, this);
		companyProjects.add(p);
		return p;
	}
	public String toString(){
		return name+":"+hiredWorkers.size()+":"+companyProjects.size();
	}
	
	public boolean equals(Object o){
        if (o instanceof Company) {
            Company otherCompany = (Company) o;
            if (name.equals(otherCompany.getName()) && getHiredWorkers().equals(otherCompany.getHiredWorkers())
            		&& getHireableWorkers().equals(otherCompany.getHireableWorkers()) 
            		&& getCompanyProjects().equals(otherCompany.getCompanyProjects())){
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
	public Set<Project> getCompanyProjects(){
		return companyProjects;
	}
	public Set<Worker> getHireableWorkers(){
		return hireableWorkers;
	}
	public Set<Worker> getHiredWorkers(){
		return hiredWorkers;
	}
} 
