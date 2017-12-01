package dao.dbtest;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

public class DBInjecteur extends DBTestCase {

	private Connection connexionDataBase;
	private static String fileName = "ressources/xml/testDataset.xml";
	
	private final static String driverName = "com.mysql.jdbc.Driver";
	private final static String connexionURL = "jdbc:mysql://dbs-perso.luminy.univmed.fr/m21002022";
	private final static String identifier = "m21002022";
	private final static String password = "ispbm.";
	
	/**
	 * 
	 * @param drive
	 * @param url
	 * @param user
	 * @param passwd
	 * @throws Exception
	 */
	private DBInjecteur(String drive, String url, String user, String passwd) throws Exception {
		IDatabaseConnection connexionDataBaseDBunit;
		
		try {
			Class.forName(drive);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		connexionDataBase = DriverManager.getConnection(url, user, passwd);

		connexionDataBaseDBunit = new DatabaseConnection(connexionDataBase, "m21002022");
		connexionDataBaseDBunit.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
		connexionDataBaseDBunit.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);

		ReplacementDataSet replacementDataSet = new ReplacementDataSet(getDataSet());
		DatabaseOperation.CLEAN_INSERT.execute(connexionDataBaseDBunit, replacementDataSet);

	}

	/**
	 * retrieve data from xml file
	 */
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(fileName));
	}

	public static void launchInjection(String fileName){
		DBInjecteur.fileName = fileName;
		try {
			new DBInjecteur(driverName, connexionURL, identifier, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}