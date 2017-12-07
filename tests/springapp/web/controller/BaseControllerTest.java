/**
 * 
 */
package springapp.web.controller;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
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

import dao.dbtest.DBExtracteur;
import dao.dbtest.DBInjecteur;
import directory.manager.beans.User;
import org.junit.Assert;

/**
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
	 * 
	 * Test method for {@link springapp.web.controller.BaseController#newUser()}.
	 */
	@Test
	public void testNewUser() {
		User expected = baseController.user;
		User actual = baseController.newUser();
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#show()}.
	 */
	@Test
	public void testShow() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#login(directory.manager.beans.User, org.springframework.validation.BindingResult)}.
	 */
	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#newUserRequest()}.
	 */
	@Test
	public void testNewUserRequest() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#logout(org.springframework.web.servlet.mvc.support.RedirectAttributes)}.
	 */
	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#productTypes()}.
	 */
	@Test
	public void testProductTypes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link springapp.web.controller.BaseController#search(java.lang.String, java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}

}
