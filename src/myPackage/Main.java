package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static String n;
	static int count = 0;
	static String rs = " \u20B9";

	public int opening() {
		System.out.println("What do you want to explore?"); // TO PRINT THE OPENING OPTIONS FOR USER
		System.out.println("1.Register for a Course(Enter 1)");
		System.out.println("2.Payment (Enter 2)");
		System.out.println("3.View students details (Enter 3)");
		System.out.println("4.Exit");
		System.out.println();
		System.out.print("Enter your choice here : ");
		int in = sc.nextInt();
		return in;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Student s1 = new Student();
		Main m1 = new Main();
		Payment pay = new Payment();

		// JDBC CONNECTION
		String query = "INSERT INTO student(StudentID, Name, YearOfJoining, CourseOpted, PendingFee) VALUES(?,?,?,?,?)";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentRecordManagement", "root",
				"6204");
		PreparedStatement st = con.prepareStatement(query);
		do {
			int option = m1.opening();
			if (option == 1) {
				String course = "";
				System.out.println("***Register a Student***");
				System.out.print("Name                   : ");
				String name = sc.nextLine();
				name += sc.nextLine();
				st.setString(2, name);
				s1.setName(name);

				System.out.print("Year of joining        : ");
				String yoj = sc.next();
				st.setString(3, yoj);
				s1.setYear(yoj);
				System.out.println();
				System.out.println("Please select the course ");
				System.out.println(" 1.Fullstack Developer          Price:" + rs + "1800");
				System.out.println(" 2.Data Science                 Price:" + rs + "1900");
				System.out.println(" 3.Cyber Security               Price:" + rs + "1700");
				System.out.println(" 4.Machine Learning             Price:" + rs + "1500");
				System.out.println(" 5.Java Fullstack Developer     Price:" + rs + "2000");
				System.out.println();
				int cin;
				do {
					System.out.print("Enter your choice       : ");
					cin = sc.nextInt();
					switch (cin) {
					case 1:
						s1.setCourse("Fullstack Developer");
						course = s1.getCourse();
						break;
					case 2:
						s1.setCourse("Data Science");
						course = s1.getCourse();
						break;
					case 3:
						s1.setCourse("Cyber Security");
						course = s1.getCourse();
						break;
					case 4:
						s1.setCourse("Machine Learning");
						course = s1.getCourse();
						break;
					case 5:
						s1.setCourse("Java Fullstack Developer");
						course = s1.getCourse();
						break;
					default:
						System.err.println("***Wrong input please try again!***");
					}
				} while (cin > 5);
				System.out.println("Your StudentId is       : " + s1.generateSerialNumber() + " <----");
				int id = s1.generateSerialNumber();
				st.setInt(1, id);
				st.setString(4, course);

				Double pf = pay.getInfo(cin);
				st.setDouble(5, pf);
				System.out.println();

				st.executeUpdate();
				count++;
				System.out.println("***********Student details are added successfully************");

			}

			if (option == 2) {
				System.out.println("************************PAYMENT******************************");
				System.out.print("Enter StudentId: ");
				int StudentId = sc.nextInt();
				int choice;
				do {
					System.out.println("1.View pending fees"); // TO VIEW THE PENDING FEES AND MAKING PAYMENT
					System.out.println("2.Do payment");
					System.out.print("Please enter your choice here : ");
					choice = sc.nextInt();
					switch (choice) {
					case 1:
						pay.pendingFee(StudentId);
						break;
					case 2:
						System.out.println();
						System.out.print("Enter the amount to be paid : " + "\u20B9");
						double payIn = sc.nextDouble();
						pay.setPay(StudentId, payIn);
						count++;
						break;
					default:
						System.err.println("***Wrong input please try again!***");
					}
					System.out.print("Do you want to continue payment? (y/n) : ");
					n = sc.next();
					System.out.println();
				} while (!n.equals("n"));
			}
			if (option == 3) { // FETCHES ALL THE DATA ABOUT A STUDENT THAT WAS REGISTERED AT LATEST
				System.out.println();
				System.out.println("************** STUDENTS DETAILS **************");
				System.out.println();
				s1.readData();
			}
			if (option == 4) { // EXITING FROM THE PROGRAM
				s1.exitPage();
			}
			if (option > 4) {
				System.err.println("***Wrong input please try again***");
			}
			System.out.print("Do you want to continue? (y/n) : ");
			n = sc.next();
			System.out.println();
		} while (!n.equals("n"));
		System.out.println("Data are added into the database successfully " + count + " row(s) affected");
		System.out.println();
		con.close();
		st.close();
		sc.close();
		System.out.println("------- THANKS FOR USING STUDENT MANAGEMENT INTERFACE -------");
		System.out.println("--------------------- SEE YOU AGAIN!!! ----------------------");
	}
}
