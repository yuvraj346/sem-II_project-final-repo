// Java Console-Based Feedback Form for Faculties

import java.sql.*;
import java.util.*;

public class FacultyFeedbackJ{
    static final String DB_URL = "jdbc:mysql://localhost:3306/feedback";
    static final String USER = "root";
    static final String PASS = ""; // Update if password is set

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Predefined faculty list
        List<Map<String, String>> facultyList = new ArrayList<>();

        facultyList.add(Map.of("initials", "JAS", "name", "Jinal Zala", "subject", "DS"));
        facultyList.add(Map.of("initials", "ARP", "name", "Ankur Patel", "subject", "JAVA"));
        facultyList.add(Map.of("initials", "KGB", "name", "Kamaldeep Bhatia", "subject", "FEE"));
        facultyList.add(Map.of("initials", "RMP", "name", "Anuj Bhat", "subject", "Maths"));
        facultyList.add(Map.of("initials", "KPP", "name", "Kishan Pala", "subject", "DBMS"));

        // Feedback Instructions - Display once
        System.out.println("✨ Feedback Form for Faculties");
        System.out.println("Please read carefully before rating:\n");

        System.out.println("C1: Overall Quality (1 to 10)");
        System.out.println("    → 1 = Very Poor, 10 = Excellent");
        System.out.println("    → Based on: Teaching Quality, Subject Knowledge, Punctuality, Syllabus Coverage, Doubt Solving");

        System.out.println("\nC2: Language Used During Lectures (A to D)");
        System.out.println("    → A = Always English");
        System.out.println("    → B = Mostly English");
        System.out.println("    → C = Mix of Hindi and English");
        System.out.println("    → D = Mostly Hindi / Casual");

        System.out.println("\nC3: Class Control (A to D)");
        System.out.println("    → A = Excellent control (silent class)");
        System.out.println("    → B = Good (minor noise)");
        System.out.println("    → C = Often noisy");
        System.out.println("    → D = No control (always noisy)");

        System.out.println("\n------------------------------------------------------");

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            for (Map<String, String> faculty : facultyList) {
                System.out.println("\n----------------------------------------");
                System.out.println("Faculty: " + faculty.get("initials") + " - " + faculty.get("name"));
                System.out.println("Subject: " + faculty.get("subject"));

                System.out.print("C1 (1-10): ");
                int c1 = sc.nextInt();
                sc.nextLine();

                System.out.print("C2 (A-D): ");
                String c2 = sc.next().toUpperCase();
                sc.nextLine();

                System.out.print("C3 (A-D): ");
                String c3 = sc.next().toUpperCase();
                sc.nextLine();

                String insertQuery = "INSERT INTO feed_back (initials, name, subject, c1_overall_points, c2_abcd_language, c3_class_control) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pst = con.prepareStatement(insertQuery)) {
                    pst.setString(1, faculty.get("initials"));
                    pst.setString(2, faculty.get("name"));
                    pst.setString(3, faculty.get("subject"));
                    pst.setInt(4, c1);
                    pst.setString(5, c2);
                    pst.setString(6, c3);
                    pst.executeUpdate();
                }
            }

            System.out.println("\n✅ Feedback submitted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
