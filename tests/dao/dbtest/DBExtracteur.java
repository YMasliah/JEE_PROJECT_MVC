package dao.dbtest;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlMetadataHandler;

public class DBExtracteur extends DBTestCase {

	private final static String driverName = "com.mysql.jdbc.Driver";
	private final static String connexionURL = "jdbc:mysql://dbs-perso.luminy.univmed.fr/m21002022";
	private final static String identifier = "m21002022";
	private final static String password = "ispbm.";
	
	Connection connexionDataBase;
	private static String fileName = "ressources/xml/currentDataset.xml";
	
	/**
	 * 
	 * @param drive
	 * @param url
	 * @param user
	 * @param passwd
	 * @throws Exception
	 */
	private DBExtracteur(String drive, String url, String user, String passwd) throws Exception {
		IDatabaseConnection connection;
		IDataSet dataSet;
		
		try {
			Class.forName(drive);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		connexionDataBase = DriverManager.getConnection(url, user, passwd);

		connection = new DatabaseConnection(connexionDataBase, "m21002022");
		
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
		
		dataSet = connection.createDataSet();

		FlatXmlDataSet.write(dataSet, new FileOutputStream(fileName));

	}

	/**
	 * unused for extracting data
	 */
	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void launchExtraction(String fileName){
		DBExtracteur.fileName = fileName;
		try {
			new DBExtracteur(driverName, connexionURL, identifier, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
