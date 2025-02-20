package source;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
public class StudentManagementImpl extends UnicastRemoteObject implements StudentManagement {
    private static final long serialVersionUID = 1L;

    private static final String URL = "...";
    private static final String USER = "...";
    private static final String PASSWORD = "...";

    public StudentManagementImpl() throws RemoteException {
        super();
    }

    // Phương thức kết nối đến MySQL
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void addStudent(int id, String name, String birthday, String email) throws RemoteException {
        try (Connection conn = getConnection()) {
            String query = "INSERT INTO students (id, name, birthday, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, birthday); // Lưu ngày sinh ở dạng String (YYYY-MM-DD)
                stmt.setString(4, email);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RemoteException("Error adding student", e);
        }
    }

    @Override
    public List<Student> listStudents() throws RemoteException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM students";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String birthday = rs.getString("birthday");
                    String email = rs.getString("email");

                    students.add(new Student(id, name, birthday, email));
                }
            }
        } catch (SQLException e) {
            throw new RemoteException("Error listing students", e);
        }
        return students;
    }

    @Override
    public Student searchStudent(int id) throws RemoteException {
        Student student = null;
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM students WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String name = rs.getString("name");
                        String birthday = rs.getString("birthday");
                        String email = rs.getString("email");
                        student = new Student(id, name, birthday, email);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RemoteException("Error searching student", e);
        }
        return student;
    }

    @Override
    public void updateStudent(int id, String name, String birthday, String email) throws RemoteException {
        try (Connection conn = getConnection()) {
            String query = "UPDATE students SET name = ?, birthday = ?, email = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, birthday);  // Cập nhật ngày sinh ở dạng String
                stmt.setString(3, email);
                stmt.setInt(4, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RemoteException("Error updating student", e);
        }
    }

    @Override
    public void deleteStudent(int id) throws RemoteException {
        try (Connection conn = getConnection()) {
            String query = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RemoteException("Error deleting student", e);
        }
    }
}
