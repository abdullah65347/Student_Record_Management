package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//THIS IS AN ENCAPSULATED CLASS
public class Student extends Payment { // INHERITANCE TO GET THE PAYMENT DETAILS FROM PAYMENT CLASS
	private String name;
	private String year;
	private String course;
	private int lastSerialNumber = 0;

	public Student() { // CONSTRUCTOR TO PRINT THE UI HEADER

		System.out.println(
				"------------------------------------------Lovely Professional University----------------------------------------");
		System.out.println("                                               (Since 2000)");
		System.out.println("                                    Welcome to Student Management Interface!");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int generateSerialNumber() {
		try {
			String url = "jdbc:mysql://localhost:3306/StudentRecordManagement";
			String userName = "root";
			String password = "6204";
			Connection con = DriverManager.getConnection(url, userName, password);
			String query = "SELECT MAX(StudentID) AS last_serial_number FROM student";
			Statement stm = con.createStatement();
			ResultSet resultSet = stm.executeQuery(query);
			if (resultSet.next()) {
				lastSerialNumber = resultSet.getInt("last_serial_number");
			} else {
				System.out.println("No serial numbers found in the table.");
			}
			resultSet.close();
			con.close();
			stm.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lastSerialNumber == 0) {
			lastSerialNumber = 2000;
		}
		return (lastSerialNumber + 1);
	}

	public void readData() {
		try {
			String url = "jdbc:mysql://localhost:3306/StudentRecordManagement";
			String userName = "root";
			String password = "6204";

			Connection con = DriverManager.getConnection(url, userName, password);
			String query = "select * from Student";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				System.out.println("Student ID                   : " + rs.getInt(1));
				System.out.println("Name                         : " + rs.getString(2));
				System.out.println("Year of joining              : " + rs.getString(3));
				System.out.println("Course opted                 : " + rs.getString(4));
				System.out.println("Pending fee                  : " + "\u20B9" + rs.getString(5));
				System.out.println();
				System.out.println("*************************************************************");
				System.out.println();
			}
			rs.close();
			con.close();
			stm.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void exitPage() {
		System.out.println(
				"---------------------------THANKS FOR USING STUDENT MANAGEMENT INTERFACE-----------------------");
		System.out.println(
				"---------------------------------------SEE YOU AGAIN!!!----------------------------------------");
		System.exit(0);
	}
}
