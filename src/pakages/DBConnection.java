package pakages;
import java.sql.*;
public class DBConnection {

	public static Connection createConnection() {
		Connection con=null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
