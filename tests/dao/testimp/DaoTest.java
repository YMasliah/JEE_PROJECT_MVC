package dao.testimp;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.IDao;
import dao.exception.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ressources/spring-test.xml")
public class DaoTest {
	
	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);
	
    @Autowired
    ApplicationContext context;
    
    @Autowired
    IDao dao;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("debut du test");
	}

	@After
	public void tearDown() {
		System.out.println("fini");
	}

	@Test
	public void testFindAllPersons1() throws DaoException {
		//System.out.print("bonjours");
		System.out.println(dao.findAll().toString());
	}
	
	
}
