package dao.dbtest;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ressources/spring-test.xml")
public class DbUnitTest extends DBTestCase {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@Autowired
	ApplicationContext context;
	
	private String driverName = "com.mysql.jdbc.Driver";
	private String connexionURL = "jdbc:mysql://dbs-perso.luminy.univmed.fr/m21002022";
	private String identifier = "m21002022";
	private String password = "ispbm.";

	IDao dao;
	DBInjecteur dBInjecteur;
	DBExtracteur dbeExtracteur;
	
	private Connection connexionDataBase;
	private IDatabaseConnection connexionDataBaseDBunit;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("debut du test");

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		connexionDataBase = DriverManager.getConnection(connexionURL, identifier, password);

		connexionDataBaseDBunit = new DatabaseConnection(connexionDataBase, "m21002022");
		connexionDataBaseDBunit.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
		connexionDataBaseDBunit.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
	}

	@After
	public void tearDown() throws SQLException {
		System.out.println("fini");
	}

	/**
	 * Description: inject data to database
	 * @throws Exception 
	 */
	@Test
	public void testDataBase() throws Exception {
        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("person");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("person");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
		
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
	}
}
