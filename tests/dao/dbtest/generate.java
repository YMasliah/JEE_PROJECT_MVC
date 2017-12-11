import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Packages à importer afin d'utiliser les objets

public class generate {
	public static void main(String[] args) throws IOException {

		PrintWriter out = new PrintWriter(new FileWriter("tests/ressources/xml/bigData.xml"));
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		out.println("<dataset>");
		int idPerson = 101;
		for (int idGroup = 1; idGroup <= 1000; idGroup++) {
			out.println("<Group Id=\"" +String.valueOf(idGroup)+ "\" Name=\"Group" +String.valueOf(idGroup)+ "\" />");
			for (int i = 1; i <= 1000; i++) {
				out.println("<Person Id=\"" +String.valueOf(idPerson)+ "\" LastName=\"nom" +String.valueOf(idPerson)+ "\" Password=\""
						+ crypt(String.valueOf(idPerson)) + "\" GroupId=\"" +String.valueOf(idGroup)+ "\" />");
				idPerson++;
			}
		}
		out.print("</dataset>");
		out.close();
		System.out.println("Generation du fichier xml terminer : "+crypt("101"));
	}

	private static String crypt(String password) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(password.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}