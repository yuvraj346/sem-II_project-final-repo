import java.sql.*;
import java.util.Scanner;

public class SubmissionBox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/complaint";
        String user = "root";
        String pass = "";

        Connection con = null;
        try {
             con = DriverManager.getConnection(url, user, pass);
            System.out.println(" Connected to database successfulyy.\n");

            System.out.println("Choose the type of submission:");
            System.out.println("1. Complaint");
            System.out.println("2. Confession");
            System.out.println("3. Suggestion");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            String type = "";

            switch (choice) {
                case 1:
                    type = "Complaint";
                    break;
                case 2:
                    type = "Confession";
                    break;
                case 3:
                    type = "Suggestion";
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to 'Confession'");
                    type = "Confession";
            }

            System.out.print("Write your message:\n> ");
            String description = sc.nextLine();

            String query = "INSERT INTO submission (type, description) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, description);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("\n Message submitted successfully!");
            } else {
                System.out.println("\n Failed to submit message.");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(" error: " + e.getMessage());
        }

        sc.close();
        System.out.println("Thank you for using");
    }
}

/**
 before running the program make sure to create a database name complaint
 and then first run this program as run.java to create the table in that
 database :

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
 import java.sql.Statement;

 public class queryrun {
 public static void main(String[] args) throws SQLException {
 //        String dburl = "jdbc:mysql://localhost:3306/taskmanager";
 String dburl = "jdbc:mysql://localhost:3306/complaint";
 String dbuser = "root";
 String dbpass = "";
 String driverName = "com.mysql.cj.jdbc.Driver";
 Connection con = null;
 try
 {
 Class.forName(driverName);
 con = DriverManager.getConnection(dburl, dbuser, dbpass);
 System.out.println("connection success");

 Statement st = con.createStatement();
 String sql = "create table submission (id int primary key, type varchar(20) not null , description text not null, status varchar(20) default 'pending', submitted_at DATETIME default CURRENT_TIMESTAMP) ";
 st.execute(sql);
 System.out.println("successful");

 }
 catch (Exception e)
 {
 System.out.println("connection failed ");
 System.out.println("reason : "+e);
 }


 }
 }
 */
