package de.hwg_lu.bw.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppInstallAccountTable {
	Connection dbConn;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstallAccountTable myApplication = new AppInstallAccountTable();

		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();

//	public void createConnection() throws ClassNotFoundException, SQLException {
//		Class.forName("org.postgresql.Driver");
//		System.out.println("JDBC-Treiberklasse geladen");
//		this.dbConn = DriverManager.getConnection("jdbc:postgresql://143.93.200.243:5432/BWUEBDB",
//									"user1",
//									"pgusers"
//									);
//		System.out.println("JDBC-Connection hergestellt");
	}

	public void doSomething() throws SQLException, ClassNotFoundException {
		dropAccountTable();
		createAccountTable();
		// insertFirstAccount();
		// insertSecondAccountStatisch();
		// insertThirdAccountFromVariables();
		// insertFourthAccountFromVariablesRetro();
		// insertSeveralAccounts();
//		showAllAccounts();
	}

	public void showAllAccounts() throws SQLException {
		String sql = "select userid, password, active,username, email from account";
		System.out.println(sql);
		PreparedStatement prep = dbConn.prepareStatement(sql);
		ResultSet dbRes = prep.executeQuery();

		dbRes.next();
		System.out.println(dbRes.getString("userid"));
		System.out.println(dbRes.getString("password"));
		System.out.println(dbRes.getString("active"));
		System.out.println(dbRes.getString("admin"));
		System.out.println(dbRes.getString("username"));
		System.out.println(dbRes.getString("email"));

	}

	public void createAccountTable() throws SQLException {
		String sql = "CREATE TABLE account (" + "			userid   CHAR(16)     NOT NULL PRIMARY KEY,"
				+ "			password CHAR(32)     NOT NULL            ,"
				+ "			active   CHAR(1)      NOT NULL DEFAULT 'Y',"
				+ "			admin    CHAR(1)      NOT NULL DEFAULT 'N',"
				+ "			username VARCHAR(256) NOT NULL            ," + "			email    VARCHAR(256) NOT NULL"
				+ "			)";
		System.out.println(sql);

// JDBC macht immer 2 Schritte (wenn mann den SQL-Befehl als String schon hat):
// erst ein "Statement" - Statement, PreparedStatement
// dann execute         - executeUpdate(), executeQuery(), execute()
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table account erfolgreich angelegt");
	}

	public void dropAccountTable() throws SQLException{
	String sql = "DROP TABLE IF EXISTS account";
			System.out.println(sql);
			dbConn.prepareStatement(sql).executeUpdate();
			System.out.println("Table account existiert (jetzt) nicht (mehr)");

	
// JDBC macht immer 2 Schritte (wenn mann den SQL-Befehl als String schon hat):
// erst ein "Statement" - Statement, PreparedStatement
// dann execute         - executeUpdate(), executeQuery(), execute()
//	PreparedStatement prepStat = dbConn.prepareStatement(sql);
//	prepStat.executeUpdate();
//	System.out.println("Table account erfolgreich angelegt");

}
}
