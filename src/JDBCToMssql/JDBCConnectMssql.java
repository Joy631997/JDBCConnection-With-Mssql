package JDBCToMssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnectMssql {
	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.168.12:1433;databaseName=New_joinee_2022", "NewJoinee2022", "P@ssw0rd");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from JOY_EMPLOYEES");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
			}

			con.close();

			Connection connection = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.168.12:1433;databaseName=New_joinee_2022", "NewJoinee2022", "P@ssw0rd");

			PreparedStatement statement = connection.prepareStatement("insert into JOY_LOGIN_JDBC values(?, ?)");
			statement.setString(1, "vijay");
			statement.setString(2, "123");

			int records = statement.executeUpdate();

			System.out.println(records + " records added");

			Statement stmts = connection.createStatement();
			ResultSet res = stmts.executeQuery("select * from JOY_LOGIN_JDBC");

			while (res.next()) {
				System.out.println(res.getString(1) + " " + res.getString(2));
			}

			int del = stmts.executeUpdate("delete from JOY_LOGIN_JDBC where USER_NAME='vijay'");

			System.out.println("Deleting record : " + del);

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
