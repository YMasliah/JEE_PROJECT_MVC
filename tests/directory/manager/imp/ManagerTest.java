package directory.manager.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import dao.dbtest.DBExtracteur;
import dao.dbtest.DBInjecteur;
import dao.exception.DaoException;
import directory.beans.Group;
import directory.beans.Person;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ressources/spring-test.xml")
public class ManagerTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@Autowired
	ApplicationContext context;

	@Autowired
	Manager manager;

	String exportFile = "ressources/xml/testDataset.xml";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		DBInjecteur.launchInjection("tests/ressources/xml/managerDataset.xml");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("fin du test");
	}

	/**
	 * test les champs retourner par la methode newUser
	 * @throws managerException
	 */
	@Test
	public void newUserTest1() throws managerException {
		User expected = new User();
		expected.setName("No User");
		expected.setId(null);
		expected.setPassword(null);
		User actual = new User();
		actual.setId(500757L);
		actual.setName("ataeraraeazeaze");
		actual.setPassword("zaeazelizaz");
		actual = manager.newUser(actual);
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'une personne sans etre connecter
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findPersonByIdTest1() throws managerException, DaoException {
		Person expected = new Person(10L, "toto", "tata", null, "https://mail.google.com",null, null, 1L);
		Person actual = manager.findPerson(new User(), 10L);
		Assert.assertTrue(expected.equals(actual));
	}
	
	/**
	 * recherche d'une personne en etant connecter
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 * @throws ParseException 
	 */
	@Test
	public void findPersonByIdTest2() throws managerException, DaoException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("1991-03-22");
		Person expected = new Person(10L, "toto", "tata", "y.masliah@gmail.com", "https://mail.google.com", date, null, 1L);
		User testUser = new User();
		testUser.setName("test");
		Person actual = manager.findPerson(testUser, 10L);
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'une personne qui n'existe pas
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 * @throws ParseException 
	 */
	@Test
	public void findPersonByIdTest3() throws managerException, DaoException, ParseException {
		Person expected = new Person();
		Person actual = manager.findPerson(new User(), 2);
		Assert.assertEquals(expected, actual);
	}

	
	/**
	 * recherche d'une personne
	 * 0 resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findPersonByNameTest1() throws managerException, DaoException {
		Collection<Person> expected = Collections.emptyList();
		ArrayList<Person> actual = (ArrayList<Person>) manager.findPerson(new User(), "azeazeazratraz",1);
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'une personne
	 * 1 resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findPersonByNameTest2() throws managerException, DaoException {
		Person expected = new Person(14L, "wolapsha", null, null, null, null, null, null);
		ArrayList<Person> actual = (ArrayList<Person>) manager.findPerson(new User(), "wolapsha",1);
		Assert.assertTrue(expected.equals(actual.get(0)));
		Assert.assertEquals(1, actual.size());
	}
	
	/**
	 * recherche d'une personne
	 * 50 resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findPersonByNameTest3() throws managerException, DaoException {
		int expected = 50;
		ArrayList<Person> actual = (ArrayList<Person>) manager.findPerson(new User(), "to",1);
		Assert.assertEquals(expected, actual.size());
	}
	
	/**
	 * recherche d'un groupe
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findGroupByIdTest1() throws managerException, DaoException {
		Group expected = new Group(101L , "Bonjour");
		Group actual = manager.findGroup(new User(), 101L);
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'un groupe, aucun resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findGroupByIdTest2() throws managerException, DaoException {
		Group expected = new Group();
		Group actual = manager.findGroup(new User(), 99L);
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'un groupe
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findGroupByNameTest1() throws managerException, DaoException {
		Group expected = new Group(101L , "Bonjour");
		Group actual = manager.findGroup(new User(), "bonjour");
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'un groupe, aucun resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findGroupByNameTest2() throws managerException, DaoException {
		Group expected = new Group();
		Group actual = manager.findGroup(new User(), "au revoir");
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'un groupe
	 * 2 resultats
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findGroupByContainsNameTest1() throws managerException, DaoException {
		int expected = 2;
		Collection<Group> actual = manager.findGroup(new User(), "bonjour",1);
		Assert.assertEquals(expected, actual.size());
	}
	
	/**
	 * recherche d'un groupe, aucun resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findGroupByContainsNameTest2() throws managerException, DaoException {
		Collection<Group>  expected = Collections.emptyList();
		Collection<Group> actual = manager.findGroup(new User(), "au revoir",1);
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * recherche d'un groupe
	 * 2 resultats
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findGroupByContainsNameTest3() throws managerException, DaoException {
		Group expected = new Group(102L , "Bonjourbis");
		ArrayList<Group> actual = (ArrayList<Group>) manager.findGroup(new User(), "bonjourbis",1);
		Assert.assertEquals(expected, actual.get(0));
	}
	
	/**
	 * recherche d'une personne
	 * 50 resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findAllPersonTest1() throws managerException, DaoException {
		int expected = 50;
		Collection<Person> actual = manager.findAll(new User(),1,1);
		Assert.assertEquals(expected, actual.size());
	}
	
	/**
	 * recherche d'une personne
	 * 0 resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findAllPersonTest2() throws managerException, DaoException {
		int expected = 0;
		Collection<Person> actual = manager.findAll(new User(),2,1);
		Assert.assertEquals(expected, actual.size());
	}
	
	/**
	 * recherche d'une personne
	 * 2 resultat
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void findAllGroupTest1() throws managerException, DaoException {
		int expected = 3;
		Collection<Group> actual = manager.findAll(new User(),1);
		Assert.assertEquals(expected, actual.size());
	}

	/**
	 * login
	 * TODO
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void loginTest1() throws managerException, DaoException {
		int expected = 3;
		Collection<Group> actual = manager.findAll(new User(),1);
		Assert.assertEquals(expected, actual.size());
	}
	
	/**
	 * logout
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 */
	@Test
	public void logoutTest1() throws managerException, DaoException {
		User expected = new User();
		expected.setName("No User");
		expected.setId(null);
		expected.setPassword(null);
		User actual = new User();
		actual.setId(500757L);
		actual.setName("ataeraraeazeaze");
		actual.setPassword("zaeazelizaz");
		manager.logout(actual);
	}
	
	/**
	 * sauvegarde authorisée, et valide.
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 * @throws ParseException 
	 */
	@Test
	public void savePersonTest1() throws managerException, DaoException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("1991-03-22");
		Person expected = new Person(10L, "toto", "tataouéoué", "y.masliah@gmail.com", "https://mail.google.com", date, null, 1L);
		User testUser = new User();
		testUser.setName("toto");
		testUser.setId(10L);
		testUser.setPassword("vasavoirbis");
		manager.savePerson(testUser, expected);
		expected.setPassword(null);
		Assert.assertEquals(expected, manager.findPerson(testUser, 10L));
	}
	
	/**
	 * sauvegarde interdite.
	 * 
	 * @throws managerException
	 * @throws DaoException 
	 * @throws ParseException 
	 */
	@Test
	public void savePersonTest2() throws managerException, DaoException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse("1991-03-22");
		Person expected = new Person(10L, "toto", "tataouéoué", "y.masliah@gmail.com", "https://mail.google.com", date, null, 1L);
		User testUser = new User();
		testUser.setName("oupas");
		manager.savePerson(testUser, expected);
		Assert.assertNotEquals(expected, manager.findPerson(testUser, 10L));
	}
}
