package demo;

import java.sql.*;
import java.util.Scanner;

public class students_data {
	public static void main(String[] args) throws SQLException {

		try {
			String url = ("jdbc:mysql://localhost:3306/student_data");
			String username = "root";
			String password = "Vedant@007";

			Connection con = DriverManager.getConnection(url, username, password);

			int opr = 0;
			String update_sel = null;

			while (opr != 6) {
				System.out.println(
						"Menu:\n1. show students list\n2. add a student\n3. update a record\n4. delete a record\n5. Search student \n6. exit\n");
				System.out.println("select any one operation:");

				Scanner sc = new Scanner(System.in);
				opr = sc.nextInt();
				switch (opr) {
				case 1:
					Statement st = con.createStatement();
					String sql = String.format("select * from data;");
					ResultSet rs = st.executeQuery(sql);
					System.out.println("roll No\t\tName\t\tcity\t\tPhone No");

					while (rs.next())
						System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t"
								+ rs.getLong(4));
					break;

				case 2:
					System.out.println("enter Roll No:");
					int id = sc.nextInt();
					System.out.println("enter name:");
					String name = sc.next();
					System.out.println("enter city:");
					String city = sc.next();
					System.out.println("enter Phone No:");
					Long phone = sc.nextLong();
					st = con.createStatement();
					sql = String.format("insert into data(id,name,city,phone)values(%d,'%s','%s',%d);", id, name, city,
							phone);

					st.executeUpdate(sql);
					System.out.println("record added successfully...");
					System.out.println();
					break;

				case 3:

					System.out.println("Enter id of student to update:");
					int update_id = sc.nextInt();
					System.out.println("	Menu:");
					System.out.println("	a. update name\n	b. update City\n	c. update Phone No\n	x.exit");
					update_sel = sc.next();

					switch (update_sel) {
					case "a":
						System.out.println("enter updated name:");
						name = sc.next();

						st = con.createStatement();
						sql = String.format("update data set name='%s' where id = %d;", name, update_id);
						st.executeUpdate(sql);
						break;
					case "b":
						System.out.println("enter updated city:");
						city = sc.next();

						st = con.createStatement();
						sql = String.format("update data set city='%s' where id = %d;", city, update_id);

						st.executeUpdate(sql);
						break;

					case "c":
						System.out.println("enter updated phone no.:");
						phone = sc.nextLong();

						st = con.createStatement();
						sql = String.format("update data set name=%d where id = %d;", phone, update_id);
						st.executeUpdate(sql);
						break;

					case "x":
						System.out.println("bye");
					default:
						System.out.println("Please select valid option");

						break;
					}

					break;

				case 4:
					System.out.println("Enter id of student to delete record:");
					int del_id = sc.nextInt();
					st = con.createStatement();

					sql = String.format("delete from data where id = %d;", del_id);
					int s = st.executeUpdate(sql);
					System.out.println(s);
					if (s != 0) {
						System.out.println("record deleted successfully...");
					} else {
						System.out.println("record not available");
					}
					break;

				case 5:
					System.out.println("Enter Roll No to find");
					int search_id = sc.nextInt();
					st = con.createStatement();
					sql = String.format("select * from data where id=%d;", search_id);
					rs = st.executeQuery(sql);
					if (rs.next()) {
						System.out.println("roll No\t\tName\t\tcity\t\tPhone No");
						System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t"
								+ rs.getLong(4));
					} else
						System.out.println("record not available!!");
					break;

				case 6:
					System.out.println("good bye!");
					break;
				default:
					System.out.println("Please enter valid operation");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}