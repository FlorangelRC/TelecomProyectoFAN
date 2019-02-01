package Pages;

import java.sql.*;
import java.util.ResourceBundle;


public class remoteDB {
	
	/*static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			System.err.println("Unable to load MySQL Driver");
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection cSession;
		
		try {
			String sHost = "jdbc:mysql://10.78.100.41:1740/FAB_CAC_FACT_DIGITAL_DOCUMENT";
			String sUser = "A000132";
			String sPass = "dskore154_";
			
			
			cSession = DriverManager.getConnection(sHost, sUser, sPass);
			
			Statement stmt=cSession.createStatement();
		}
		catch (SQLException sqleError) {
			System.out.println("SQL Connection error with the following message: " + sqleError.getMessage());
		}
		
		
	}*/
	
	private static Connection con=null;
	private static Connection cnx;
	private static String bd = "login";
	private static String user = "root";
	//private static String password = "admin";
	private static String password = "adrian";
	//private static String host = "localhost:3306";
	private static String host = "127.0.0.1:3306";
	private static String server = "jdbc:mysql://"+host+"/"+bd;
	
	public static Connection getConnection() {
		try	{
			if( con == null ) {
				// con esto determinamos cuando finalize el programa
				Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
				ResourceBundle rb  =ResourceBundle.getBundle("conexion.bruno");
				String driver = rb.getString("driver");
				String url = rb.getString("url");
				String user = rb.getString("user");
				String password = rb.getString("password");
				String bd = rb.getString("bd");
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, password);
			}
			//System.out.println("conectado");
			return con;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException("Error al crear la conexion",ex);
		}
	}

	static class MiShDwnHook extends Thread {
		// justo antes de finalizar el programa la JVM invocara a este metodo donde podemos cerrar la conexion
		public void run() {
			try	{
				Connection con = remoteDB.getConnection();
				con.close();
			}
			catch( Exception ex ) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}

}