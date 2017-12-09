/**
 * 
 */
package springapp.web.controller;

import static org.junit.Assert.fail;

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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.dbtest.DBExtracteur;
import dao.dbtest.DBInjecteur;
import dao.exception.DaoException;
import directory.manager.beans.User;
import directory.manager.exception.managerException;

/** 
 * 
 * @author m21002022
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ressources/spring-test.xml")
@WebAppConfiguration
public class BaseControllerTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@Autowired
	ApplicationContext context;

	@Autowired
	BaseController baseController;

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
	 * test le renvoi de valeur de la methode
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#getUser()}.
	 */
	@Test
	public void testNewUser() {
		User expected = baseController.user;
		User actual = baseController.getUser();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * renvoi la page d'index si la personne est un utilisateur deja connecter.
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#getShow()}.
	 * @throws DaoException 
	 * @throws managerException 
	 */
	@Test
	public void testShow1() throws managerException, DaoException {
		String expected = "index";
		String actual ;
		baseController.user.setName("toto");
		actual = baseController.getShow();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * renvoi la page d'index si la personne a rentrer un identifiant vide ou incorrect.
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#getShow()}.
	 * @throws DaoException 
	 * @throws managerException 
	 */
	@Test
	public void testShow2() throws managerException, DaoException {
		String expected = "index";
		String actual ;
		baseController.user.setId(null);
		actual = baseController.getShow();
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * renvoi la page d'index si la personne a rentrer un mot de passe vide ou incorrecte.
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#getShow()}.
	 * @throws DaoException 
	 * @throws managerException 
	 */
	@Test
	public void testShow3() throws managerException, DaoException {
		String expected = "index";
		String actual ;
		baseController.user.setPassword(null);
		actual = baseController.getShow();
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * authentifie l'user si les informations sont correcte
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#getShow()}.
	 * @throws DaoException 
	 * @throws managerException 
	 */
	@Test
	public void testShow4() throws managerException, DaoException {
		String expected = "index";
		User expected2 = new User(10,"vasavoirbis");
		expected2.setName("toto");
		baseController.user.setId(10L);
		baseController.user.setPassword("vasavoirbis");
		Assert.assertEquals(expected, baseController.getShow());
		Assert.assertEquals(expected2.toString(), baseController.user.toString());
	}
	
	/**
	 * login reussi
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#postLogin(directory.manager.beans.User, org.springframework.validation.BindingResult)}.
	 * @throws managerException 
	 * @throws DaoException 
	 */
	@Test
	public void testLogin1() throws DaoException, managerException {
		String expected = "redirect:login";
		User expected2 = new User(10,"vasavoirbis");
		baseController.user.setId(10L);
		baseController.user.setPassword("vasavoirbis");
		Assert.assertEquals(expected, baseController.postLogin(expected2, new BindException(expected2, "user")));
		Assert.assertEquals(expected2.toString(), baseController.user.toString());
	}

	/**
	 * mot de passe incorrect
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#postLogin(directory.manager.beans.User, org.springframework.validation.BindingResult)}.
	 * @throws managerException 
	 * @throws DaoException 
	 */
	@Test
	public void testLogin2() throws DaoException, managerException {
		String expected = "index";
		User expected2 = new User(10,"aze");
		expected2.setName("No User");
		baseController.user.setId(10L);
		baseController.user.setPassword("aze");
		Assert.assertEquals(expected, baseController.postLogin(expected2, new BindException(expected2, "user")));
	}
	
	/**
	 * identifiant incorrect
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#postLogin(directory.manager.beans.User, org.springframework.validation.BindingResult)}.
	 * @throws managerException 
	 * @throws DaoException 
	 */
	@Test
	public void testLogin3() throws DaoException, managerException {
		String expected = "index";
		User expected2 = new User(1,"vasavoirbis");
		expected2.setName("No User");
		baseController.user.setId(1L);
		baseController.user.setPassword("vasavoirbis");
		Assert.assertEquals(expected, baseController.postLogin(expected2, new BindException(expected2, "user")));
	}
	
	/**
	 * identifiant vide
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#postLogin(directory.manager.beans.User, org.springframework.validation.BindingResult)}.
	 * @throws managerException 
	 * @throws DaoException 
	 */
	@Test
	public void testLogin4() throws DaoException, managerException {
		String expected = "index";
		baseController.user.setId(null);
		baseController.user.setPassword("vasavoirbis");
		Assert.assertEquals(expected, baseController.postLogin(baseController.user, new BindException(baseController.user, "user")));
	}
	
	/**
	 * identifiant vide
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#postLogin(directory.manager.beans.User, org.springframework.validation.BindingResult)}.
	 * @throws managerException 
	 * @throws DaoException 
	 */
	@Test
	public void testLogin5() throws DaoException, managerException {
		String expected = "index";
		baseController.user.setId(10L);
		baseController.user.setPassword(null);
		Assert.assertEquals(expected, baseController.postLogin(baseController.user, new BindException(baseController.user, "user")));
	}
	
	/**
	 * Test method for {@link springapp.web.controller.BaseController#newUserRequest()}.
	 */
	@Test
	public void testPasswordLost() {
		String expected = "passwordLost";
		Assert.assertEquals(expected, baseController.getPasswordLost());
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#getLogout(org.springframework.web.servlet.mvc.support.RedirectAttributes)}.
	 * @throws managerException 
	 */
	@Test
	public void testLogout() throws managerException {
//		String expected = "redirect:login";
//		String expected2 = "No user";
//		Assert.assertEquals(expected, baseController.logout(null));
//		Assert.assertEquals(expected2, baseController.user.getName());
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#search(java.lang.String, java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testSearch() {
//		fail("Not yet implemented");
	}

}
