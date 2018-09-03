package tw.idv.aloha.lineBot.Utli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	public static void main(String[] args) {

		String jdbcUrl = "jdbc:postgresql://localhost:5432/";
		String username = "postgres";
		String password = " ";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(jdbcUrl, username, password);
			Class.forName("org.postgresql.Driver");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT version()");

			if (rs.next()) {
				System.out.println(rs.getString(1));
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}