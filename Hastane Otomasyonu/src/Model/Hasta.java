package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Helper.*;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDB();
	PreparedStatement preparedStatement = null;

	public Hasta() {
		super();
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}

	public boolean register(String tcno, String sifre, String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(tcno,sifre,name,type) VALUES" + "(?,?,?,?)";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");

			while (rs.next()) {
				duplicate = true;
				Helper.showMessage("Bu bilgiler ile eþleþen bir kayýt bulunmaktadýr !");
				break;
			}
			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, sifre);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = 1;
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

}
