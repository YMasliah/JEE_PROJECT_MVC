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
import directory.beans.Person;


/**
 * 
 * @author m21002022
 *
 *	test sur une base de donnée vide
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ressources/spring-test.xml")
public class DaoTest1 {

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
		DBExtracteur.launchExtraction("ressources/xml/testDataset.xml");
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		DBInjecteur.launchInjection("tests/ressources/xml/currentDataset.xml");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("debut du test");
		DBInjecteur.launchInjection("tests/ressources/xml/emptyDataset.xml");
	}

	@After
	public void tearDown() {
		System.out.println("fini");
	}

	/**
	 * 
	 * requete invalide sur le 2eme argument
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllPersons1() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertEquals(expecteds, dao.findAll(1,0));
	}
	
	/**
	 * 
	 * resultat vide
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllPersons2() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertEquals(expecteds, dao.findAll(1,1));
	}

	/**
	 * 
	 * requete invalide
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllGroup1() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertEquals(expecteds, dao.findAll(0));
	}

	/**
	 * 
	 * resultat vide
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllGroup2() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertEquals(expecteds, dao.findAll(1));
	}
	
	/**
	 * 
	 * resultat vide
	 * recherche par id
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindGroup1() throws DaoException {
		Group expected = new Group();
		Assert.assertEquals(expected, dao.findGroup(1));
	}
	
	/**
	 * 
	 * resultat vide
	 * recherche par nom
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindGroup2() throws DaoException {
		Group expected = new Group();
		Assert.assertEquals(expected, dao.findGroup("toto"));
	}
	
	/**
	 * 
	 * resultat vide
	 * recherche par id
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindPerson1() throws DaoException {
		Person expected = new Person();
		Assert.assertEquals(expected, dao.findPerson(1));
	}
	
	/**
	 * 
	 * resultat vide
	 * recherche par nom
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindPerson2() throws DaoException {
		Person expected = new Person();
		Assert.assertEquals(expected, dao.findPerson("toto",1));
	}
	
	/**
	 * 
	 * edition d'un groupe
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testSaveBeanGroup1() throws DaoException {
		Group expected = new Group(10,"toto");
		dao.saveBean(expected);
		Assert.assertEquals(expected, dao.findGroup(10));
	}
	
	/**
	 * 
	 * ajout d'un groupe interdit
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testSaveBeanGroup2() throws DaoException {
		Group expected = new Group();
		dao.saveBean(new Group(0,"toto"));
		Assert.assertEquals(expected, dao.findGroup("toto"));
	}
	
	/**
	 * 
	 * edition d'une personne
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testSaveBeanPerson1() throws DaoException {
		Person expected = new Person();
		expected.setId(10L);
		expected.setLastName("tata");
		dao.saveBean(expected);
		Assert.assertEquals(expected, dao.findPerson(10));
	}
	
	/**
	 * 
	 * ajout d'une personne interdit
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testSaveBeanPerson2() throws DaoException {
		Collection<Person> expected = Collections.emptyList();  
		Person p = new Person();
		p.setId(0L);
		p.setLastName("tata");
		dao.saveBean(p);
		Assert.assertEquals(expected, dao.findAll(1));
	}
}
