package tw.idv.aloha.lineBot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository("jdbcDao")
public class JdbcDAOImpl implements JdbcDAO {

	@Resource(name = "dataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void query() throws SQLException {
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT version()");

		if (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}

	// public Customer findByCustomerId(int custId){
	//
	// String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	//
	// Connection conn = null;
	//
	// try {
	// conn = dataSource.getConnection();
	// PreparedStatement ps = conn.prepareStatement(sql);
	// ps.setInt(1, custId);
	// Customer customer = null;
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// customer = new Customer(
	// rs.getInt("CUST_ID"),
	// rs.getString("NAME"),
	// rs.getInt("Age")
	// );
	// }
	// rs.close();
	// ps.close();
	// return customer;
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// } finally {
	// if (conn != null) {
	// try {
	// conn.close();
	// } catch (SQLException e) {}
	// }
	// }
	// }
}
