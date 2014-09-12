package cs414.a1.bawitt;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Qualification {

	private String description;
	
	private Set<Worker> workersWithQual;
	
	Qualification(String input){
		description = input;
		workersWithQual = new HashSet<Worker>();
	}
	public void addWorkerToQual(Worker w){  //add worker to specific qualification
		workersWithQual.add(w);
	}
	public void removeWorkerFromQual(Worker w){ //remove worker from specific qualification
		workersWithQual.remove(w);
	}
	public String toString(){
		return description;
	}
	public boolean equals(Object o){
        if (o instanceof Qualification) {
            Qualification otherQual = (Qualification) o;
            if(description.equals(otherQual.getDescription()) && getWorkersWithQual().equals(otherQual.getWorkersWithQual())){
            	return true;
            }
        }
        return false;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String newDescription){
		description = newDescription;
	}
	public Set<Worker> getWorkersWithQual(){
		return workersWithQual;
	}
}
