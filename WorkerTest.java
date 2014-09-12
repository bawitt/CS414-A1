package cs414.a1.bawitt;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
//import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorkerTest {
	private Company c = new Company("Test Company");
	//Qualification tech = new Qualification("Technician");
	private Qualification manager = new Qualification("Manager");
	private Set<Qualification> testQual; // = new ArrayList<Qualification>();
	private Set<Worker> w; // = new ArrayList<Worker>();
	
	
	@Before
	public void setUp() throws Exception {
		testQual = new HashSet<Qualification>();
		w = new HashSet<Worker>();
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void addWorker() {
		Worker tw = new Worker("Tom", testQual, c);
		assertEquals(tw.getNickName(), "Tom");
		assertTrue(tw.getWorkerQual().equals(testQual));
		assertTrue(tw.getCompany().equals(c));
	}
	@Test
	public void testIsOverloaded() {
		testQual.add(manager);
		Worker tw = c.createWorker("Tom", testQual);
		c.hire(tw);
		Project proj1 = new Project("Project1", w, testQual, ProjectSize.big, c);
		Project proj2 = new Project("Project2", w, testQual, ProjectSize.medium, c);
		Project proj3 = new Project("Project3", w, testQual, ProjectSize.medium, c);
		proj1.setStatus(ProjectStatus.active);
		proj2.setStatus(ProjectStatus.active);
		proj3.setStatus(ProjectStatus.active);
		assertFalse(tw.isOverLoaded());
		tw.addProjectToWorker(proj1);
		assertFalse(tw.isOverLoaded());
		tw.addProjectToWorker(proj2);
		assertFalse(tw.isOverLoaded());
		tw.addProjectToWorker(proj3);
		assertTrue(tw.isOverLoaded());
	}
}
