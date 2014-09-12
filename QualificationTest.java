package cs414.a1.bawitt;


import static org.junit.Assert.*;

//import java.util.ArrayList;
//import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QualificationTest {
	private final Qualification manager = new Qualification("Manager");
	//private final Qualification tech = new Qualification("Tech");
	private Set<Qualification> testQual;
	private Company tc = new Company("Test Company");
	private Worker TestWorker = new Worker("Ben", testQual, tc);
	private Worker TestWorker2 = new Worker("Tom", testQual, tc);
	
	@Before
	public void setUp() throws Exception {
	
		//ArrayList<Qualification> testQual = new ArrayList<Qualification>();
	}
	@Test
	public void testQualification(){
		Qualification tech = new Qualification("Technician");
		assertTrue(tech.getDescription().equals("Technician"));
		assertTrue(tech.getWorkersWithQual().isEmpty());
		
	}
	
	@Test
	public void testAddWorkerToQual(){
		assertTrue(manager.getWorkersWithQual().isEmpty());
		manager.addWorkerToQual(TestWorker);
		assertTrue(!manager.getWorkersWithQual().isEmpty());
		//System.out.println(TestWorker);
		//System.out.println(manager.getWorkersWithQual().get(0));
		assertTrue(manager.getWorkersWithQual().contains(TestWorker));
	}

	@Test
	public void testRemoveWorkerFromQual(){
		manager.addWorkerToQual(TestWorker);
		manager.addWorkerToQual(TestWorker2);
		//System.out.println(manager.getWorkersWithQual());
		manager.removeWorkerFromQual(TestWorker);
		//System.out.println(manager.getWorkersWithQual());
		//assertEquals(manager.getWorkersWithQual().get(0), TestWorker2);
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		//fail("Not yet implemented");
	}

}
