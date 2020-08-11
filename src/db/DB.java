package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;							//Instantiate a connection type object with null value
	
	public static Connection getConnection() {						//Connect to SQL Database
		
		if (conn == null) {
			try {
				Properties props = loadProperties();				//Instantiate a properties object calling the loadProperties method
				String url = props.getProperty("dburl");			//Retrieves the url value from the property object
				conn = DriverManager.getConnection(url, props);		//Assign the connection to the object 
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
		return conn;
	}
	
	public static void closeConnection() {
		
		if (conn != null) {
			try {
				conn.close();										//Close connection if conn is not null
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		
		try (FileInputStream fs = new FileInputStream("db.properties")) {	//Load the db.properties file
			Properties props = new Properties();							//Instantiate a properties object 
			props.load(fs);													//Assign to the properties object the file values
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
