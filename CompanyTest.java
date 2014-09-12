package cs414.a1.bawitt;

import static org.junit.Assert.*;

import java.util.HashSet;
//import java.util.Iterator;
import java.util.Set;
//import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {
	private Company c = new Company("Test Company");
	private Qualification manager = new Qualification("Manager");
	private Qualification tech = new Qualification("Technician");
	private Set<Qualification> testQual; 
	private Set<Qualification> testQual2;
	private Set<Qualification> pr; 
	private Set <Worker> ws; 
	
	@Before
	public void setUp() throws Exception {
		testQual = new HashSet<Qualification>();
		testQual2 = new HashSet<Qualification>();
		pr = new HashSet<Qualification>();
		ws = new HashSet<Worker>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCompany() {
		Company testC = new Company("TC");
		assertTrue(testC.getName().equals("TC"));
		assertTrue(testC.getHireableWorkers().isEmpty());
		assertTrue(testC.getHiredWorkers().isEmpty());
		assertTrue(testC.getCompanyProjects().isEmpty());
	}
	@Test
	public void testCreateWorker() {
		Worker tw1 = c.createWorker("Ben", testQual);
		assertTrue(c.getHireableWorkers().contains(tw1));
	}
	@Test
	public void testHire() {
		Worker tw1 = c.createWorker("Ben", testQual);
		c.hire(tw1);
		assertTrue(c.getHiredWorkers().contains(tw1));
		c.hire(tw1);
		assertTrue(c.getHiredWorkers().size()==1);
	}
	@Test
	public void testCreateProject() {
		Project p = c.createProject("Project", ws, testQual, ProjectSize.small);
		assertTrue(c.getCompanyProjects().contains(p));
	}
	@Test
	public void testStart() {
		testQual.add(tech);
		pr.add(tech);
		pr.add(manager);
		Worker tw1 = c.createWorker("Ben", testQual);
		testQual2.add(manager);
		Worker tw2 = c.createWorker("Tom", testQual2);
		Project p = c.createProject("Project", ws, pr, ProjectSize.small);
		c.hire(tw1);
		
		c.start(p);
		assertTrue(!(p.getStatus()==ProjectStatus.active));
		c.hire(tw2);
		assertTrue(p.getStatus()==ProjectStatus.active);
	}
	@Test
	public void testFinish() {
		testQual.add(tech);
		testQual2.add(manager);
		pr.add(tech);
		pr.add(manager);
		Worker tw1 = c.createWorker("Ben", testQual);
		Worker tw2 = c.createWorker("Tom", testQual2);
		Project p = c.createProject("Project", ws, pr, ProjectSize.small);
		c.hire(tw1);
		c.hire(tw2);;

		c.finish(p);
		
		assertTrue(p.getWorkersInvolved().size()==0);  //workers removed from project
		assertTrue(tw1.getProjectsInvolved().size()==0);  //projects removed from workers
		assertTrue(tw2.getProjectsInvolved().size()==0);		
		
		Project tp = c.createProject("Project", ws, pr, ProjectSize.small);
		tp.setStatus(ProjectStatus.suspended);
		c.finish(tp);
		assertTrue(tp.getStatus() == ProjectStatus.suspended); //suspended project is not finished
	}
	@Test
	public void testFire() {
		testQual.add(tech);
		testQual2.add(manager);
		pr.add(tech);
		pr.add(manager);
		Worker tw1 = c.createWorker("Ben", testQual);
		Worker tw2 = c.createWorker("Tom", testQual2);
		Project p = c.createProject("Project", ws, pr, ProjectSize.small);
		c.hire(tw1);
		c.hire(tw2);;
		
		assertTrue(p.getWorkersInvolved().contains(tw1));
		assertTrue(p.getacquiredQual().contains(tech));
		assertTrue(p.getStatus()==ProjectStatus.active);
		assertTrue(tech.getWorkersWithQual().contains(tw1));
		assertTrue(tw1.getProjectsInvolved().contains(p));
		assertTrue(c.getHiredWorkers().contains(tw1));
		
		c.fire(tw1);
		
		assertTrue(!p.getWorkersInvolved().contains(tw1));
		assertTrue(!p.getacquiredQual().contains(tech));
		assertTrue(p.getStatus()==ProjectStatus.suspended);
		assertTrue(!tech.getWorkersWithQual().contains(tw1));
		assertTrue(tw1.getProjectsInvolved().isEmpty());
		assertTrue(!c.getHiredWorkers().contains(tw1));
	}
}
