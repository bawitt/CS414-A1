package cs414.a1.bawitt;


import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
//import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
	private Set<Worker> workersInvolved; 
	private Set<Qualification> workerQual; 
	private Set<Qualification> requiredQual;
	private ProjectSize size;
	//private ProjectStatus status;
	//private Project proj;
	private Qualification manager = new Qualification("Manager");
	private Qualification tech = new Qualification("Technician");
	
	private Company tc = new Company("Test Company");
	
	//private final Qualification tech = new Qualification("Tech");
	
	@Before
	public void setUp() throws Exception {
		workerQual= new HashSet<Qualification>();
		requiredQual= new HashSet<Qualification>();
		workersInvolved= new HashSet<Worker>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testProject(){
		workerQual.add(manager);
		requiredQual.add(manager);
		requiredQual.add(tech);
		Worker tw = tc.createWorker("Ben", workerQual);
		Worker tw1 = tc.createWorker("Tom", workerQual);

		size = ProjectSize.small;
		
		tc.hire(tw);
		
		workersInvolved.add(tw);
		workersInvolved.add(tw1);
		
		Project proj = new Project("Project", workersInvolved, requiredQual, size, tc);
		assertTrue(proj.getName().equals("Project"));
		assertTrue(proj.getWorkersInvolved().contains(tw));
		assertTrue(!proj.getWorkersInvolved().contains(tw1));
		assertTrue(proj.getSize().equals(ProjectSize.small));
		assertTrue(proj.getCompany().equals(tc));
	}
	
	@Test
	public void testAddWorkerToProject(){
		requiredQual.add(manager);
		Project proj = new Project("Project", workersInvolved, requiredQual, size, tc);
		workerQual.add(tech);
		Worker tw = tc.createWorker("Ben", workerQual);
		proj.addWorkerToProject(tw);
		assertTrue(proj.getWorkersInvolved().isEmpty()); //worker not added if not yet hired
		tc.hire(tw);
		proj.addWorkerToProject(tw);
		assertTrue(proj.getWorkersInvolved().isEmpty()); //not added if qual not needed
		workerQual.add(manager);
		Worker tw1 = tc.createWorker("Tom", workerQual);
		tc.hire(tw1);
		proj.addWorkerToProject(tw1);
		assertTrue(proj.getWorkersInvolved().contains(tw1)); //worker added
		proj.addWorkerToProject(tw1);
		assertTrue(proj.getWorkersInvolved().size()==1);  //can not be added twice
		assertTrue(proj.getacquiredQual().contains(manager)); //manager qual added to acquired proj qual
		assertTrue(proj.getacquiredQual().contains(tech)); //tech qual added to acquired proj qual
		assertTrue(proj.getacquiredQual().size()==2); //these are the only two present
	}
	
	@Test
	public void testMissingQualifications(){

		workerQual.add(manager);
		requiredQual.add(manager);
		requiredQual.add(tech);
		Worker tw = tc.createWorker("Ben", workerQual);
		Worker tw1 = tc.createWorker("Tom", workerQual);

		size = ProjectSize.small;
		
		tc.hire(tw);
		
		workersInvolved.add(tw);
		workersInvolved.add(tw1);
		
		Project proj = new Project("Project", workersInvolved, requiredQual, size, tc);
		Set<Qualification> missing = proj.missingQualifications();
		assertTrue(missing.contains(tech));
		assertTrue(!missing.contains(manager));
		//System.out.println(missing);
	}
	
	@Test
	public void testIsHelpful(){
		workerQual.add(manager);
		workerQual.add(tech);
		requiredQual.add(manager);
		Worker tw = tc.createWorker("Ben", workerQual);
		size = ProjectSize.small;
		workersInvolved.add(tw);
		Project proj = new Project("Project", workersInvolved, requiredQual, size, tc);
		assertTrue(proj.isHelpful(tw));
	}
}
