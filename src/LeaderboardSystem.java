//import java.sql.*;
//import java.util.*;
//
//class BSTNode {
//    String name;
//    String enrollmentNo;
//    int totalPoints;
//    BSTNode left, right;
//
//    BSTNode(String name, String enrollmentNo, int totalPoints) {
//        this.name = name;
//        this.enrollmentNo = enrollmentNo;
//        this.totalPoints = totalPoints;
//        left = right = null;
//    }
//}
//
//class BST {
//    BSTNode root;
//
//    void insert(String name, String enrollmentNo, int totalPoints) {
//        root = insertRec(root, name, enrollmentNo, totalPoints);
//    }
//
//    BSTNode insertRec(BSTNode root, String name, String enrollmentNo, int totalPoints) {
//        if (root == null) return new BSTNode(name, enrollmentNo, totalPoints);
//        if (totalPoints > root.totalPoints) root.right = insertRec(root.right, name, enrollmentNo, totalPoints);
//        else root.left = insertRec(root.left, name, enrollmentNo, totalPoints);
//        return root;
//    }
//
//    void printLeaderboard() {
//        System.out.println("\n===== Leaderboard (Top Performers) =====\n");
//        printReverseInOrder(root);
//    }
//
//    void printReverseInOrder(BSTNode root) {
//        if (root != null) {
//            printReverseInOrder(root.right);
//            System.out.printf("%s (%s) - %d points\n", root.name, root.enrollmentNo, root.totalPoints);
//            printReverseInOrder(root.left);
//        }
//    }
//}
//
//public class LeaderboardSystem {
//
//    static final String DB_URL = "jdbc:mysql://localhost:3306/events";
//    static final String USER = "root";
//    static final String PASS = ""; // Update if you have a password
//
//    public static void main(String[] args) {
//        Connection con = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        BST leaderboard = new BST();
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            // ✅ Using subquery instead of JOIN to fetch leaderboard data
//            String query = "SELECT name, enrollment_no, " +
//                    "(SELECT SUM(points_earned) FROM participants p WHERE p.enrollment_no = u.enrollment_no) AS total_points " +
//                    "FROM users u WHERE EXISTS (SELECT 1 FROM participants p WHERE p.enrollment_no = u.enrollment_no)";
//
//            pst = con.prepareStatement(query);
//            rs = pst.executeQuery();
//
//            // Insert each student into BST with their total points
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String enrollment = rs.getString("enrollment_no");
//                int points = rs.getInt("total_points");
//
//                leaderboard.insert(name, enrollment, points);
//            }
//
//            // Print sorted leaderboard using BST (highest points first)
//            leaderboard.printLeaderboard();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//            } catch (Exception ignored) {}
//            try {
//                if (pst != null) pst.close();
//            } catch (Exception ignored) {}
//            try {
//                if (con != null) con.close();
//            } catch (Exception ignored) {}
//        }
//    }
//}
