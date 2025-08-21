/**
 this java file is to create tables studentinfo and tasks in the taskmanager database run it and make sure to add autoincrement
 to title id after creating the table , I forgot to  add that in query
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class queryrun {
    public static void main(String[] args) throws SQLException {
//        String dburl = "jdbc:mysql://localhost:3306/taskmanager";
//        String dburl = "jdbc:mysql://localhost:3306/complaint";
//        String dburl = "jdbc:mysql://localhost:3306/events";
//        String dburl = "jdbc:mysql://localhost:3306/feedback";
        String dburl = "jdbc:mysql://localhost:3306/login";
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
//            String sql = "INSERT INTO participants (enrollment_no, event_name, community, points_earned)\n" +
//                    "VALUES \n" +
//                    "('24002171310090', 'Treasure Hunt', 'binarybrains', 30),\n" +
//                    "('24002171310003', 'Active Coding', 'binarybrains', 25),\n" +
//                    "('24002171710016', 'Battles of Ground VO', 'binarybrains', 20),\n" +
//                    "\n" +
//                    "('24002171410007', 'Hackathon', 'lfa', 35),\n" +
//                    "('24002171310014', 'Seavenger Hunt', 'lfa', 25),\n" +
//                    "('24002171310090', 'Scramble Words', 'lfa', 20),\n" +
//                    "\n" +
//                    "('24002171310014', 'Lumina Dance', 'ljsc', 15),\n" +
//                    "('24002171310003', 'Lumina Music', 'ljsc', 30),\n" +
//                    "('24002171410007', 'Lumina Drama', 'ljsc', 25),\n" +
//                    "('24002171310090', 'Lumina Mr & Ms LJ', 'ljsc', 40),\n" +
//                    "('24002171710016', 'Lumina Carpadium - Cricket', 'ljsc', 30),\n" +
//                    "('24002171310003', 'Lumina Carpadium - Chess', 'ljsc', 20);\n";
//            st.execute(sql);
//            System.out.println("success");


//String sql = "INSERT INTO participants (enrollment_no, event_name, community, points_earned)\n" +
//        "VALUES \n" +
//        "('24002171310090', 'Treasure Hunt', 'binarybrains', 30),\n" +
//        "('24002171310003', 'Active Coding', 'binarybrains', 25),\n" +
//        "('24002171710016', 'Battles of Ground VO', 'binarybrains', 20),\n" +
//        "\n" +
//        "('24002171410007', 'Hackathon', 'lfa', 35),\n" +
//        "('24002171310014', 'Seavenger Hunt', 'lfa', 25),\n" +
//        "('24002171310090', 'Scramble Words', 'lfa', 20),\n" +
//        "\n" +
//        "('24002171310014', 'Lumina Dance', 'ljsc', 15),\n" +
//        "('24002171310003', 'Lumina Music', 'ljsc', 30),\n" +
//        "('24002171410007', 'Lumina Drama', 'ljsc', 25),\n" +
//        "('24002171310090', 'Lumina Mr & Ms LJ', 'ljsc', 40),\n" +
//        "('24002171710016', 'Lumina Carpadium - Cricket', 'ljsc', 30),\n" +
//        "('24002171310003', 'Lumina Carpadium - Chess', 'ljsc', 20);\n";
//st.execute(sql);
//   String sql = "INSERT INTO feed_back (initials, name, subject)\n" +
//           "VALUES \n" +
//           "('JAS', 'Jinal Zala', 'DS'),\n" +
//           "('ARP', 'Ankur Patel', 'JAVA'),\n" +
//           "('KGB', 'Kamaldeep Bhatia', 'FEE'),\n" +
//           "('RMP', 'Anuj Bhat', 'MATHS'),\n" +
//           "('KPP', 'Kishan Pala', 'DBMS');";
//       st.execute(sql);

            String sql = "CREATE TABLE loginPage (\n" +
                    "    enrollment_no VARCHAR(20) PRIMARY KEY,  -- unique 14-digit enrollment no\n" +
                    "    username VARCHAR(100) NOT NULL,         -- student name\n" +
                    "    password VARCHAR(255) NOT NULL,         -- store password (later hashed)\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                    ");";
            st.execute(sql);





        }
        catch (Exception e)
        {
            System.out.println("connection failed ");
            System.out.println("reason : "+e);
        }


    }
}

// for creating the tasks table
// String sql = "create table tasks (id int primary key, enrollment_number varchar(20), title varchar(100), description text, deadline date, status varchar(20) default 'incomplete', created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";


// for creating the studentinfo table
//            String sql = "create table studentinfo ( enrollment_number varchar(20) primary key, name varchar(100), div varchar(10) )";
//            st.execute(sql);
//
//            System.out.println("successfully created ");

//            for creating the complainbox table in complaint database
//            String sql = "create table complainbox (cid varchar(5) primary key, complain varchar(100), status varchar(20) default 'incomplete') ";
//            st.execute(sql);
//
//            System.out.println("successful");

//            String sql = "create table submission (id int primary key, type varchar(20) not null , description text not null, status varchar(20) default 'pending', submitted_at DATETIME default CURRENT_TIMESTAMP) ";
//            st.execute(sql);
//            System.out.println("successful");
//
//String sql = "CREATE TABLE participants (\n" +
//        "  id INT PRIMARY KEY AUTO_INCREMENT,\n" +
//        "  enrollment_no VARCHAR(20) NOT NULL,\n" +
//        "  event_name VARCHAR(100),\n" +
//        "  community VARCHAR(50), -- e.g., 'binarybrains', 'lfa', 'ljsc'\n" +
//        "  points_earned INT DEFAULT 0,\n" +
//        "  FOREIGN KEY (enrollment_no) REFERENCES users(enrollment_no)\n" +
//        ");\n";
//           st.execute(sql);


//String sql = "INSERT INTO users (enrollment_no, name)\n" +
//                    "VALUES \n" +
//                    "('24002171310090', 'Yuvraj Parmar'),\n" +
//                    "('24002171310003', 'Anjali Dubey'),\n" +
//                    "('24002171710016', 'Munshi Sibtainhaidar ZahirAhmad'),\n" +
//                    "('24002171310014', 'Buch Manin JwalantBhai'),\n" +
//                    "('24002171410007', 'Bhavsar Daksh NarendraBhai');\n";
//            st.execute(sql);


//
//String sql = "INSERT INTO binarybrains (event_name, winner_enrollment_no, points)\n" +
//        "VALUES \n" +
//        "('Treasure Hunt', '24002171310090', 30),\n" +
//        "('Active Coding', '24002171310003', 25),\n" +
//        "('Battles of Ground VO', '24002171710016', 20);\n";
//              st.execute(sql);


//
//String sql = "INSERT INTO lfa (event_name, winner_enrollment_no, points)\n" +
//        "VALUES \n" +
//        "('Hackathon', '24002171410007', 35),\n" +
//        "('Seavenger Hunt', '24002171310014', 25),\n" +
//        "('Scramble Words', '24002171310090', 20);\n";
//            st.execute(sql);


//
//String sql = "INSERT INTO ljsc (event_name, winner_enrollment_no, points)\n" +
//        "VALUES \n" +
//        "('Lumina Dance', '24002171310014', 15),\n" +
//        "('Lumina Music', '24002171310003', 30),\n" +
//        "('Lumina Drama', '24002171410007', 25),\n" +
//        "('Lumina Mr & Ms LJ', '24002171310090', 40),\n" +
//        "('Lumina Carpadium - Cricket', '24002171710016', 30),\n" +
//        "('Lumina Carpadium - Chess', '24002171310003', 20);\n";
//            st.execute(sql);

//
//String sql="CREATE TABLE feed_back (\n" +
//        "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
//        "    initials VARCHAR(10),\n" +
//        "    name VARCHAR(100),\n" +
//        "    subject VARCHAR(100),\n" +
//        "    c1_overall_points INT CHECK (c1_overall_points BETWEEN 1 AND 10),\n" +
//        "    c2_abcd_language CHAR(1) CHECK (c2_abcd_language IN ('A', 'B', 'C', 'D')),\n" +
//        "    c3_class_control CHAR(1) CHECK (c3_class_control IN ('A', 'B', 'C', 'D')),\n" +
//        "    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
//        ");\n";
//            st.execute(sql);