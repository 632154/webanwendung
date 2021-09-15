package de.hwg_lu.bw.jdbc;

import java.sql.*;

import de.hwg_lu.bw.jdbc.PostgreSQLAccess;

public class AppInstallKlausurTables {
	
	PostgreSQLAccess myPGA;
	Connection dbConn;
	String matrikelnr;

	public AppInstallKlausurTables()  throws SQLException {
		this.myPGA = new PostgreSQLAccess();
		this.dbConn = myPGA.getConnection();
		
		String mySchema = myPGA.dbSchema;
		this.matrikelnr = myPGA.dbSchema.substring(mySchema.length() - 6);
		System.out.println(this.matrikelnr);
	}

	public static void main(String[] args)  throws SQLException {
		new AppInstallKlausurTables().doSomething();
	}
	public void doSomething()  throws SQLException {
		this.dropTables();
		this.createTables();
		this.insertTables();
	}
	
	public void dropTables() throws SQLException {
		String sql;
		sql = "drop table if exists vormerkung" + "_" + this.matrikelnr;
		System.out.println(sql);
		this.dbConn.createStatement().executeUpdate(sql);
		
		sql = "drop table if exists ort" + "_" + this.matrikelnr;
		System.out.println(sql);
		this.dbConn.createStatement().executeUpdate(sql);
		System.out.println("Tabellen gedropped");
	}
	public void createTables() throws SQLException {
		String sql;
		sql = "create table ort" + "_" + this.matrikelnr
				+ " (ortsname   char(64) not null primary key)";
		System.out.println(sql);	
		this.dbConn.createStatement().executeUpdate(sql);
		
		sql = "create table vormerkung" + "_" + this.matrikelnr + " ( "
						+ "id serial not null primary key    , "
						+ "name varchar(256) not null        , "
						+ "alter integer                     , "
						+ "ort varchar(256)                  ) ";
		System.out.println(sql);
		dbConn.createStatement().executeUpdate(sql);
		System.out.println("Tabellen angelegt");
	}
	public void insertTables() throws SQLException {
		String sql = "insert into ort" + "_" + this.matrikelnr
				+ " (ortsname) values ('Kaiserslautern'), ('Frankfurt'), ('Stuttgart'), ('Muenchen'), ('Hamburg'), ('Berlin')";
		System.out.println(sql);
		dbConn.createStatement().executeUpdate(sql);
		System.out.println("Datensätze eingetragen");
	}
		

}
