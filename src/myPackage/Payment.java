package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Payment {

	private double pendingPay = 2000;
	static Scanner sc = new Scanner(System.in);
	static String rs = " \u20B9";

	public void setPay(int studentId, double pay) {
		try {
			String url = "jdbc:mysql://localhost:3306/StudentRecordManagement";
			String userName = "root";
			String password = "6204";
			Connection conn = DriverManager.getConnection(url, userName, password);

			String query = "SELECT PendingFee from student WHERE StudentID = " + studentId;
			Statement stm = conn.createStatement();
			ResultSet resultSet = stm.executeQuery(query);
			if (resultSet.next()) {
				pendingPay = resultSet.getDouble("PendingFee");
			} else {
				System.out.println("No records found for student with ID " + studentId);
			}

			String query1 = "UPDATE student SET PendingFee =  ? WHERE StudentID = " + studentId;
			PreparedStatement pstm1 = conn.prepareStatement(query1);
			System.out.println(); // PAYING PENDING FEE
			if (pay <= pendingPay) {
				pendingPay = pendingPay - pay;
				pstm1.setDouble(1, pendingPay);
				pstm1.execute();
				System.out.println("***" + rs + pay + " has been paid successfully and the balance to be paid :"
						+ rs + pendingPay + "***");
				System.out.println();
			} else {
				System.out.println("Transaction Failed!");
			}
			conn.close();
			stm.close();
			pstm1.close();
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pendingFee(int studentId) {
		try {
			String url = "jdbc:mysql://localhost:3306/StudentRecordManagement";
			String userName = "root";
			String password = "6204";
			Connection conn = DriverManager.getConnection(url, userName, password);

			String query = "SELECT PendingFee from student WHERE StudentID = " + studentId;
			Statement stm = conn.createStatement();
			ResultSet resultSet = stm.executeQuery(query);
			if (resultSet.next()) {
				pendingPay = resultSet.getDouble("PendingFee");
				System.out
						.println("Pending fee for student with ID " + studentId + "      : " + rs + pendingPay);
			} else {
				System.out.println("No records found for student with ID " + studentId);
			}
			conn.close();
			stm.close();
			resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public double getInfo(int cin) {
		System.out.println();
		pendingPay = 2000;
		if (cin == 1) {
			pendingPay = pendingPay - 200;
			System.out.println("***Pending fee :" + rs + pendingPay + "***");
		} else if (cin == 2) {
			pendingPay = pendingPay - 100;
			System.out.println("***Pending fee :" + rs + pendingPay + "***");
		} else if (cin == 3) {
			pendingPay = pendingPay - 300;
			System.out.println("***Pending fee :" + rs + pendingPay + "***");
		} else if (cin == 4) {
			pendingPay = pendingPay - 500;
			System.out.println("***Pending fee :" + rs + pendingPay + "***");
		} else if (cin == 5) {
			System.out.println("***Pending fee :" + rs + pendingPay + "***");
		}else {
			return 0;
		}
		System.out.println("***You can pay later***");
		return pendingPay;
	}

}
