package dao.testimp;

import java.util.Collection;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.IDao;
import dao.dbtest.DBExtracteur;
import dao.dbtest.DBInjecteur;
import dao.exception.DaoException;
import directory.beans.Group;


/**
 * 
 * @author m21002022
 *
 *	test sur du BigData
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ressources/spring-test.xml")
public class DaoTest3 {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@Autowired
	ApplicationContext context;

	@Autowired
	IDao dao;

	String exportFile = "ressources/xml/testDataset.xml";

	@BeforeClass
	public static void setUpClass() throws Exception {
		DBExtracteur.launchExtraction("tests/ressources/xml/currentDataset.xml");
		DBExtracteur.launchExtraction("tests/ressources/xml/testDataset.xml");
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		DBInjecteur.launchInjection("tests/ressources/xml/bigDataset.xml");
		System.out.println("injecter");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("debut du test");
		DBInjecteur.launchInjection("tests/ressources/xml/bigDataset.xml");
	}

	@After
	public void tearDown() {
		System.out.println("fini");
	}

	/**
	 * 
	 * resultat vide
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllPersons4() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertEquals(expecteds, dao.findAll(10,1));
	}
	
}
