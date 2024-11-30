package com.edu;

import java.sql.SQLException;
import java.util.Scanner;

public class MainAppStudentManagement {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int ch;
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("*******STUDENT MANAGEMENT SYSTEM***********");
		System.out.println("1.Get All Records");
		System.out.println("2.Get Student by id");
		System.out.println("3.Update Student ");
		System.out.println("4.Delete Student by id");
		System.out.println("5.Add Student");
		
		System.out.println("Enter your choice");
		ch = sc.nextInt();
		switch(ch) {
		case 1: //fetch record
			StudentOperations.getAllStudents();
		        break;
		case 2: //get Student by id
			StudentOperations.getStudentById();
			   break;
		case 3: //update record
			StudentOperations.updateStudentById();
			 break;
		case 4: //delete record
			StudentOperations.deleteStudentById();
			break;
		case 5: //addStudent
			 StudentOperations.addStudent();
		     break;
		 default: System.out.println("Invalid input!!!!!!!");
		           System.out.println("Enter values between 1 to 5");
		       
		  
		}
		
		System.out.println("do you want to continue press y to continue , any other key to stop");
		char choice = sc.next().toLowerCase().charAt(0);
		if(choice !='y')
			break;
		}//while
		System.out.println("Program is terminated");
	}

}
