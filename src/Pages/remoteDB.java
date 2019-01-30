package Pages;

import java.sql.*;

public class remoteDB {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection cSession;
		
		try {
			String sHost = "jdbc:mysql://10.78.100.41:1740/FAB_CAC_FACT_DIGITAL_DOCUMENT";
			String sUser = "A000132";
			String sPass = "dskore154_";
			
			//Class.forName("C:\\Program Files (x86)\\MySQL\\Connector J 8.0\\mysql-connector-java-8.0.14.jar");
			cSession = DriverManager.getConnection(sHost, sUser, sPass);
			
			Statement stmt=cSession.createStatement();
		}
		catch (SQLException sqleError) {
			System.out.println("SQL Connection error with the following message: " + sqleError.getMessage());
		}
		
		
	}

}