package de.hwg_lu.bw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppInstallUrlaubszieleTable {
	
	Connection dbConn;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstallUrlaubszieleTable myApplication = new AppInstallUrlaubszieleTable();
		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();
	}
	
	public void doSomething() throws SQLException, ClassNotFoundException{
		dropAccountTable();
		createAccountTable();
		
	}
	
	public void createAccountTable() throws SQLException {
		String sql = "CREATE TABLE urlaubsziel ("
				+ "		urlaubsziel   CHAR(64)     NOT NULL PRIMARY KEY,"
				+ "		bewertung     CHAR(64)     NOT NULL           "
				+ "	  )";
		System.out.println(sql);

		// JDBC macht immer 2 Schritte (wenn mann den SQL-Befehl als String schon hat):
		// erst ein "Statement" - Statement, PreparedStatement
		// dann execute         - executeUpdate(), executeQuery(), execute()
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table urlaubsziele erfolgreich angelegt");
	}
	public void dropAccountTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS urlaubsziel";
		System.out.println(sql);
		dbConn.prepareStatement(sql).executeUpdate();
		System.out.println("Table urlaubsziele existiert (jetzt) nicht (mehr)");
	}
	
	
	
	
	public void showAllAccounts1() throws SQLException{
		String sql = "select userid, password, active, admin, username, email " +
						"from account";
		System.out.println(sql);
		Statement stmt = this.dbConn.createStatement();
		ResultSet dbRes = stmt.executeQuery(sql);
		
//		dbRes.next(); //auf die erste Zeile, return true (gefunden) oder false (nicht gefunden)
		while(dbRes.next()){
			String myUserid   = dbRes.getString("userid");
			String myPassword = dbRes.getString("password");
			String myActive   = dbRes.getString("active");
			String myAdmin    = dbRes.getString("admin");
			String myUsername = dbRes.getString("username");
			String myEmail    = dbRes.getString("email");
			System.out.println(myUserid + myPassword + myActive + myAdmin + myUsername + myEmail);
		}
		
	}
	public void createAccountTable1() throws SQLException{
		String sql = "create table bw440_654321.account                ("
				+ "				userid   CHAR(16) NOT NULL PRIMARY KEY,"
				+ "				password CHAR(32) NOT NULL            ,"
				+ "				active   CHAR(1)  NOT NULL DEFAULT 'Y',"
				+ "				admin    CHAR(1)  NOT NULL DEFAULT 'N',"
				+ "				username VARCHAR(256)                 ,"
				+ "				email    VARCHAR(256)                 )";
		System.out.println(sql);
		Statement stmt = this.dbConn.createStatement();
		stmt.executeUpdate(sql);
		System.out.println("Tabelle account angelegt");
	}
	public void dropAccountTable1(Connection dbConn) throws SQLException{
		String sql = "drop table bw440_654321.account";
		System.out.println(sql);
		Statement stmt = this.dbConn.createStatement();
		try{
			stmt.executeUpdate(sql);
			System.out.println("Tabelle account gedroppt");
		}catch(SQLException se){
			System.out.println("Tabelle account konnte nicht gedroppt werden weil sie nicht da war");
		}
	}
	public void insertFirstAccount1() throws SQLException{
		String sql = "insert into bw440_654321.account "
						+ "(userid, password, active, admin, username, email) "
						+ "values"
						+ "(?,?,?,?,?,?)";
		System.out.println(sql);
		//Statement jetzt mit Daten!!! -> PreparedStatement
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.setString(1, "testus1");
		prep.setString(2, "geheim");
		prep.setString(3, "Y");
		prep.setString(4, "N");
		prep.setString(5, "Testuser 1 wenn alles klappt");
		prep.setString(6, "testus1@abc.de");
		prep.executeUpdate();
		System.out.println("Erster Account angelegt");
	}
	public void insertSecondAccountStatisch1() throws SQLException{
		String sql = "insert into bw440_654321.account "
						+ "(userid, password, active, admin, username, email) "
						+ "values"
						+ "('testus2','geheim','Y','N','Testuser 2','testus2@abc.de')";
		System.out.println(sql);
		//Statement jetzt mit Daten!!! -> PreparedStatement
		this.dbConn.createStatement().executeUpdate(sql);
		System.out.println("Zweiter Account angelegt");
	}
	public void insertThirdAccountFromVariables1() throws SQLException{
		String userid = "testus3";
		String password = "geheim";
		String active = "Y";
		String admin = "N";
		String username = "Testuser 3";
		String email = "testus3@abc.de";
		
		String sql = "insert into bw440_654321.account "
				+ "(userid, password, active, admin, username, email) "
				+ "values"
				+ "(?,?,?,?,?,?)";
		System.out.println(sql);
		
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.setString(1, userid);
		prep.setString(2, password);
		prep.setString(3, active);
		prep.setString(4, admin);
		prep.setString(5, username);
		prep.setString(6, email);
		prep.executeUpdate();
		
		System.out.println("Dritter Account angelegt");
	}
	public void insertFourthAccountFromVariablesRetro1() throws SQLException{
		String userid = "testus4";
		String password = "geheim";
		String active = "Y";
		String admin = "N";
		String username = "Testuser 4";
		String email = "testus4@abc.de";
		String sql = "insert into bw440_654321.account "
				+ "(userid, password, active, admin, username, email) "
				+ "values "
				+ "('" + userid + "','" + password + "','" + active + "','" + admin + "','" + username + "','" + email + "')"; 
		System.out.println(sql);
		this.dbConn.createStatement().executeUpdate(sql);
		System.out.println("Vierter Account angelegt");
	}
	public void insertSeveralAccounts1() throws SQLException{
		String sql = "insert into bw440_654321.account "
				+ "(userid, password, active, admin, username, email) "
				+ "values"
				+ "(?,?,?,?,?,?)";
		System.out.println(sql);

		String userid = "testus";
		String password = "geheim";
		String active = "Y";
		String admin = "N";
		String username = "Testuser ";
		String email = "testusx@abc.de";

		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		for (int myNumber = 5; myNumber <= 10; myNumber++){
			String myNumberString = Integer.toString(myNumber);
			prep.setString(1, userid + myNumberString);
			prep.setString(2, password);
			prep.setString(3, active);
			prep.setString(4, admin);
			prep.setString(5, username + myNumberString);
			prep.setString(6, email.replace("x", myNumberString));
			prep.executeUpdate();
			
			System.out.println("Account testus " + myNumberString + " angelegt");

			
		}
	}

}
