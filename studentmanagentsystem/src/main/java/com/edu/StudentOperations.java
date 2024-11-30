package com.edu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentOperations {
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static int sid, sage, ret;
	private static float sfees;
	private static String semail, sname, sdob;
	private static String sql;
	private static Scanner sc = new Scanner(System.in);

	public static void getAllStudents() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();
		stmt = conn.createStatement();
		
		sql = "select * from student";
		rs = stmt.executeQuery(sql);
		System.out.printf("%-20s%-20s%-20s%-30s%-20s%-30s", "SID", "SNAME", "SAGE", "SEMAIL", "SFFES", "SDOB");
		System.out.println();
		while (rs.next()) {
			sid = rs.getInt("sid"); // rs.getInt(1);
			sname = rs.getString("sname");
			sage = rs.getInt("sage");
			semail = rs.getString("semail");
			sfees = rs.getFloat("sfees");
			sdob = rs.getString("sdob");
			// System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+semail+"\t"+sfees+"\t"+sdob);
			System.out.printf("%-20d%-20s%-20d%-30s%-20.2f%-20s", sid, sname, sage, semail, sfees, sdob);
			System.out.println();
		}

	}

	public static void getStudentById() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();
		stmt = conn.createStatement();
		System.out.println("Enter student id");
		sid = sc.nextInt();
		sql = "select * from student where sid=" + sid;

		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			// System.out.println("SID\tSNAME\tSAGE\tSEMAIL\tSFEES\tSDOB");
			System.out.printf("%-20s%-20s%-20s%-30s%-20s%-20s", "SID", "SNAME", "SAGE", "SEMAIL", "SFFES", "SDOB");
			System.out.println();
			sid = rs.getInt("sid"); // rs.getInt(1);
			sname = rs.getString("sname");
			sage = rs.getInt("sage");
			semail = rs.getString("semail");
			sfees = rs.getFloat("sfees");
			sdob = rs.getString("sdob");
			// System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+semail+"\t"+sfees+"\t"+sdob);
			System.out.printf("%-20d%-20s%-20d%-20s%-20f%-20s", sid, sname, sage, semail, sfees, sdob);
			System.out.println();

		} else {
			System.out.println("Student not exists");
		}

	}

	public static void updateStudentById() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();
		stmt = conn.createStatement();
		System.out.println("Enter student id");
		sid = sc.nextInt();

		String s = "select * from student where sid =" + sid;

		rs = stmt.executeQuery(s);

		if (rs.next()) {
			while (true) {
				// menu
				System.out.println("****Update menu*******");
				System.out.println("1.Update email");
				System.out.println("2.Change age");
				System.out.println("3.Change DOB");
				System.out.println("4.Change fees");

				System.out.println("Enter your choice");
				int upch = sc.nextInt();

				switch (upch) {
				case 1: // change email
					System.out.println("Change email");
					System.out.println("Enter new email id");
					String newemail = sc.next();
					sql = "update student set semail='" + newemail + "' where sid=" + sid;
					ret = stmt.executeUpdate(sql);
					if (ret > 0) {
						System.out.println("Email is changed successfully");
					} else {
						System.out.println("Error");
					}
					break;

				case 2: // Change age
					System.out.println("change age");
					System.out.println("Enter new age");
					int newage = sc.nextInt();
					sql = "update student set sage='" + newage + "' where sid=" + sid;
					ret = stmt.executeUpdate(sql);
					if (ret > 0) {
						System.out.println("Age is changed successfully");
					} else {
						System.out.println("Error");
					}
					break;

				case 3: // change dob
					System.out.println("Change dob");
					System.out.println("Enter new dob in (YYYY-MM-DD)");
					String newdob = sc.next();
					sql = "update student set sdob='" + newdob + "' where sid=" + sid;
					ret = stmt.executeUpdate(sql);
					if (ret > 0) {
						System.out.println("Dob is changed successfully");
					} else {
						System.out.println("Error");
					}
					break;
				case 4: // change fees
					System.out.println("Change fees");
					System.out.println("Enter new fees");
					int newfees = sc.nextInt();
					sql = "update student set sage='" + newfees + "' where sid=" + sid;
					ret = stmt.executeUpdate(sql);
					if (ret > 0) {
						System.out.println("Fees+ is changed successfully");
					} else {
						System.out.println("Error");
					}
					break;
				default:
					System.out.println("Invalid input (choice can be between 1 to 4");

				}
				System.out.println("Do you want to countinue update operation");
				System.out.println("press yes to continue else no to stop");
				String up = sc.next();
				if (up.equals("no"))
					break;

			} // while true

		} else {
			System.out.println("ID not exits");
		}
	}

	public static void deleteStudentById() throws SQLException, ClassNotFoundException {
	    conn = DatabaseConnection.getConnection();
	    
	    // First, check if the record exists
	    String checkExistenceSql = "SELECT * FROM student WHERE sid = ?";
	    PreparedStatement checkStmt = conn.prepareStatement(checkExistenceSql);
	    
	    System.out.println("Enter student id");
	    sid = sc.nextInt();
	    
	    // Set the sid parameter for the SELECT query
	    checkStmt.setInt(1, sid);
	    
	    ResultSet rs = checkStmt.executeQuery();
	    
	    if (rs.next()) { // If the student exists
	        // Proceed with the DELETE operation
	        String deleteSql = "DELETE FROM student WHERE sid = ?";
	        PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
	        
	        // Set the sid parameter for the DELETE query
	        deleteStmt.setInt(1, sid);
	        
	        int ret = deleteStmt.executeUpdate(); // Execute the DELETE query

	        if (ret > 0) {
	            System.out.println("Record deleted successfully.");
	        } else {
	            System.out.println("Error: Record not deleted.");
	        }
	    } else {
	        // If no record is found
	        System.out.println("Error: Student record does not exist.");
	    }
	}



	public static void addStudent() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		conn = DatabaseConnection.getConnection();
	    stmt = conn.createStatement();

	    System.out.println("Enter Student Details:");
	    System.out.println("Enter Student ID:");
	    sid = sc.nextInt();
	    sc.nextLine(); // Consume newline left-over
	    System.out.println("Enter Student Name:");
	    sname = sc.nextLine();
	    System.out.println("Enter Student Age:");
	    sage = sc.nextInt();
	    sc.nextLine(); // Consume newline left-over
	    System.out.println("Enter Student Email:");
	    semail = sc.nextLine();
	    System.out.println("Enter Student Fees:");
	    sfees = sc.nextFloat();
	    sc.nextLine(); // Consume newline left-over
	    System.out.println("Enter Student DOB (YYYY-MM-DD):");
	    sdob = sc.nextLine();

	    sql = "INSERT INTO student (sid, sname, sage, semail, sfees, sdob) VALUES (" +
	          sid + ", '" + sname + "', " + sage + ", '" + semail + "', " + sfees + ", '" + sdob + "')";
	    ret = stmt.executeUpdate(sql);

	    if (ret > 0) {
	        System.out.println("Student added successfully.");
	    } else {
	        System.out.println("Error adding student.");
	    }

	}

}
