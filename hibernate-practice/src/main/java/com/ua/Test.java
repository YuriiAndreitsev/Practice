package com.ua;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost/hibernate-practice?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pass = "";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);

			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("Connection successful!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}
}
