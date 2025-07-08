package com.javaproject.app;

import java.sql.*;
import java.util.*;
import java.io.*;

public class StudentManagement {

    private static final Scanner SCANNER = new Scanner(System.in);

    static class TipThread extends Thread {
        public void run() {
            String[] tips = {
                "Tip: Backup your database regularly!",
                "Tip: Press 4 to export student records to file.",
                "Tip: You can import data using option 5.",
                "Tip: Use correct ID to update student records.",
                "Tip: Keep your CSV files in project root for easy access."
            };
            int index = 0;
            while (true) {
                try {
                    Thread.sleep(600000); // 10 minutes
                    System.out.println("\n[Tip] " + tips[index % tips.length]);
                    index++;
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private static void ensureTable(String tblName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tblName + " (" +
                     "id SERIAL PRIMARY KEY, " +
                     "name VARCHAR(100) NOT NULL, " +
                     "age INT NOT NULL, " +
                     "course VARCHAR(100) NOT NULL, " +
                     "marks NUMERIC(5,2) NOT NULL)";
        try (Connection con = DBconnection.getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void insertStudent(String tbl, String name, int age, String course, double marks) {
        String sql = "INSERT INTO " + tbl + "(name, age, course, marks) VALUES (?,?,?,?)";
        try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setDouble(4, marks);
            ps.executeUpdate();
            System.out.println("Student inserted ✔");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void updateStudent(String tbl, int id, String name, int age, String course, double marks) {
        String sql = "UPDATE " + tbl + " SET name=?, age=?, course=?, marks=? WHERE id=?";
        try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setDouble(4, marks);
            ps.setInt(5, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Updated ✔" : "ID not found ✖");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void listStudents(String tbl) {
        String sql = "SELECT * FROM " + tbl + " ORDER BY id";
        try (Connection con = DBconnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.printf("%n%-4s | %-20s | %-3s | %-12s | %-5s%n","ID","NAME","AGE","COURSE","MARKS");
            System.out.println("--------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-4d | %-20s | %-3d | %-12s | %-5.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course"),
                        rs.getDouble("marks"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void exportToCSV(String tbl, String filePath) {
        try (Connection con = DBconnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM " + tbl);
             PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

            pw.println("id,name,age,course,marks");
            while (rs.next()) {
                pw.printf("%d,%s,%d,%s,%.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course"),
                        rs.getDouble("marks"));
            }
            System.out.println("Data exported to " + filePath);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static void importFromCSV(String tbl, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO " + tbl + "(name, age, course, marks) VALUES (?, ?, ?, ?)")) {

            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                ps.setString(1, p[1]);
                ps.setInt(2, Integer.parseInt(p[2]));
                ps.setString(3, p[3]);
                ps.setDouble(4, Double.parseDouble(p[4]));
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Data imported from " + filePath);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        final String TABLE = "studentdetails";
        ensureTable(TABLE);
        new TipThread().start();

        while (true) {
            System.out.println("\n==== STUDENT MANAGEMENT MENU ====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Show All Students");
            System.out.println("4. Export to File");
            System.out.println("5. Import from File");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = SCANNER.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Name   : "); String name   = SCANNER.nextLine();
                    System.out.print("Age    : "); int age = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("Course : "); String course = SCANNER.nextLine();
                    System.out.print("Marks  : "); double marks = Double.parseDouble(SCANNER.nextLine());
                    insertStudent(TABLE, name, age, course, marks);
                    break;

                case "2":
                    System.out.print("Student ID to update: "); int id = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("New Name   : "); name   = SCANNER.nextLine();
                    System.out.print("New Age    : "); age    = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("New Course : "); course = SCANNER.nextLine();
                    System.out.print("New Marks  : "); marks  = Double.parseDouble(SCANNER.nextLine());
                    updateStudent(TABLE, id, name, age, course, marks);
                    break;

                case "3":
                    listStudents(TABLE);
                    break;

                case "4":
                    System.out.print("Enter export file name (e.g., students.csv): ");
                    String file = SCANNER.nextLine();
                    exportToCSV(TABLE, file);
                    break;

                case "5":
                    System.out.print("Enter import file name (e.g., students.csv): ");
                    file = SCANNER.nextLine();
                    importFromCSV(TABLE, file);
                    break;

                case "0":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}