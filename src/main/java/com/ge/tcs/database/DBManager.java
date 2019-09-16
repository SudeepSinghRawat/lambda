package com.ge.tcs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ge.tcs.entity.TableOne;

public class DBManager {
	private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://database1.cfms0zyx5ihf.us-east-2.rds.amazonaws.com/database1";
	private static final String DBUSER = "admin";
	private static final String DBPASSWORD = "database1";

	static {
		System.out.println("Driver tying to load");
		try {
			Class.forName(DBDRIVER);
			System.out.println("diverloaded...");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.toString());
		}

	}

	public static void test() {
		System.out.println("Test");
	}

	public static List<TableOne> getAllDetialFromtable() {
		List<TableOne> list = new ArrayList<TableOne>();
		try {
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			PreparedStatement st = con.prepareStatement("select id, name , rollnumber from DemoTable1");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				TableOne t = new TableOne();
				t.setId(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setRollNumber(rs.getInt(3));
				list.add(t);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	public static TableOne getUserDetial(int rollNumber) {
		TableOne t = new TableOne();
		try {
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			PreparedStatement st = con
					.prepareStatement("select id, name , rollnumber from DemoTable1 where rollnumber = ?");
			st.setInt(1, rollNumber);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				t.setId(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setRollNumber(rs.getInt(3));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return t;

	}

	public static boolean insertData(TableOne data) {
		Connection con;
		 boolean status = false;
		try {
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			PreparedStatement st = con
					.prepareStatement("insert into database1.DemoTable1 (id,name,rollnumber) values(?,?,?)");
			st.setInt(1, data.getId());
			st.setString(2, data.getName());
			st.setInt(3, data.getRollNumber());
			st.executeUpdate();
			st.close();
			status = true;
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

}
