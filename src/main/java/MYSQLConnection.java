import java.io.PrintWriter;
import java.sql.*;

import org.apache.catalina.connector.Response;

public class MYSQLConnection {
	public static Connection getMySQLConnection()  {
		try {
			//Class.forName("com.mysql.driver.MySQLDriver");
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e) {
System.out.println("jytr");
			}
			String url = "jdbc:mysql://localhost:3306/sample";
			String user = "root";
			String password = "uday";
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (SQLException e) {
			System.out.println("SQL Error");
			e.printStackTrace();
		}
		return null;
	}
}
