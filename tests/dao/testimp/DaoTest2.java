package dao.testimp;

import java.util.ArrayList;
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
 *	test sur une base de donnée avec moins de 50 groupes et personnes par groupe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ressources/spring-test.xml")
public class DaoTest2 {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@Autowired
	ApplicationContext context;

	@Autowired
	IDao dao;

	String exportFile = "tests/ressources/xml/testDataset.xml";

	@BeforeClass
	public static void setUpClass() throws Exception {
		DBExtracteur.launchExtraction("tests/ressources/xml/currentDataset.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DBExtracteur.launchExtraction("tests/ressources/xml/testDataset.xml");
		DBInjecteur.launchInjection("tests/ressources/xml/currentDataset.xml");
		System.out.println("injecter");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("debut du test");
		DBInjecteur.launchInjection("tests/ressources/xml/mediumSizeDataset.xml");
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
	 * resultat non vide et inferieur ou egal a 50
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllPersons2() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertNotEquals(expecteds, dao.findAll(1,1));
		Assert.assertTrue(50>=dao.findAll(1).size());
	}

	/**
	 * 
	 * resultat non vide et egal a 36
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllPersons3() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertNotEquals(expecteds, dao.findAll(1,1));
		Assert.assertTrue(36==dao.findAll(1,1).size());
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
	 * resultat non vide et inferieur ou egal a 50
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllGroup2() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertNotEquals(expecteds, dao.findAll(1));
		Assert.assertTrue(50>=dao.findAll(1).size());
	}
	
	/**
	 * 
	 * resultat non vide et egal a 2
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllGroup3() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertNotEquals(expecteds, dao.findAll(1));
		Assert.assertTrue(2==dao.findAll(1).size());
	}
	
	/**
	 * 
	 * resultat vide
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindAllGroup4() throws DaoException {
		Collection<Group> expecteds = Collections.emptyList();
		Assert.assertEquals(expecteds, dao.findAll(2));
	}
	
	/**
	 * 
	 * resultat non vide
	 * recherche par id
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindGroup1() throws DaoException {
		Group expected = new Group(1,"No group");
		Assert.assertEquals(expected, dao.findGroup(1));
	}
	
	/**
	 * 
	 * resultat non vide
	 * recherche par nom
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindGroup2() throws DaoException {
		Group expected = new Group(1,"No group");
		Assert.assertEquals(expected, dao.findGroup("No group"));
	}
	
	/**
	 * 
	 * resultat vide
	 * recherche par id
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindGroup3() throws DaoException {
		Group expected = new Group();
		Assert.assertEquals(expected, dao.findGroup(0));
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
		Assert.assertEquals(expected, dao.findPerson(0));
	}
	
	/**
	 * 
	 * resultat non vide
	 * recherche par nom
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindPerson2() throws DaoException {
		Person expected = new Person();
		expected.setId(10L);
		expected.setLastName("toti");
		Assert.assertEquals(expected, ((ArrayList<Person>) dao.findPerson("toti",1)).get(0));
	}
	
	/**
	 * 
	 * recuperation d'une liste de personne
	 * recherche par nom
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindPerson3() throws DaoException {
		long expected = 1;
		Assert.assertTrue(expected<dao.findPerson("toto",1).size());
	}
	
	/**
	 * 
	 * resultat non vide
	 * recherche par id
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindPerson4() throws DaoException {
		Person expected = new Person();
		expected.setId(10L);
		expected.setPassword("vasavoir");
		expected.setLastName("toti");
		expected.setGroupId(1L);
		Assert.assertEquals(expected, dao.findPerson(10));
	}
	
	/**
	 * 
	 * resultat vide
	 * recherche par nom
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testFindPerson5() throws DaoException {
		Collection<Person> expected = Collections.emptyList();
		Assert.assertEquals(expected, dao.findPerson("rondoudou",1));
	}
	
	/**
	 * 
	 * edition d'un groupe
	 * 
	 * @throws DaoException
	 */
	@Test
	public void testSaveBeanGroup1() throws DaoException {
		Group expected = new Group(1,"toto");
		dao.saveBean(expected);
		Assert.assertEquals(expected, dao.findGroup(1));
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
		expected.setPassword("vasavoirarzaerazrararaz");
		expected.setLastName("ttoatoaotaotaoetzeotaeota");
		expected.setGroupId(1L);
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
		int expected = dao.findAll(1).size();  
		Person p = new Person();
		p.setId(0L);
		p.setLastName("tata");
		dao.saveBean(p);
		Assert.assertEquals(expected, dao.findAll(1).size());
	}
}
