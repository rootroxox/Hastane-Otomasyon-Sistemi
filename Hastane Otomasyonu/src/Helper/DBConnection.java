package Helper;

import java.sql.*;

public class DBConnection {
	Connection c = null;
	
	public DBConnection() {}
	
	public Connection connDB()
	{
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:2574/hospital?user=root&password=Ms7144723?");
			return c;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return c;
	}

	
}
