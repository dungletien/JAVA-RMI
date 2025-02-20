package source;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Kết nối đến registry và tìm dịch vụ "StudentManagement"
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            StudentManagement stub = (StudentManagement) registry.lookup("StudentManagement");

            System.out.println("Connected to RMI server.");

            while (true) {
                // Hiển thị menu
                System.out.println("\n<< Welcome to Student Management System >>");
                System.out.println("1. Add Student");
                System.out.println("2. List Students");
                System.out.println("3. Search Student");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");

                System.out.print("Enter Your Choice: ");
                int choice = -1;

                // Kiểm tra lựa chọn nhập vào
                try {
                    choice = Integer.parseInt(scanner.nextLine()); // Đọc và chuyển đổi thành int
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please enter a number between 1 and 6.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        // Thêm sinh viên mới
                        try {
                            System.out.print("Enter Student ID: ");
                            int id = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Student Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Student Birthday (YYYY-MM-DD): ");
                            String birthday = scanner.nextLine();
                            System.out.print("Enter Student Email: ");
                            String email = scanner.nextLine();

                            stub.addStudent(id, name, birthday, email);
                            System.out.println("Student added successfully!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for ID. Please enter a valid integer.");
                        } catch (Exception e) {
                            System.out.println("Error adding student: " + e.getMessage());
                        }
                        break;

                    case 2:
                        // Liệt kê tất cả sinh viên
                        try {
                            List<Student> students = stub.listStudents();
                            System.out.println("Student List:");
                            if (students.isEmpty()) {
                                System.out.println("No students found.");
                            } else {
                                for (Student s : students) {
                                    System.out.println("ID: " + s.getId() + ", Name: " + s.getName());
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error fetching student list: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Tìm kiếm sinh viên theo ID
                        try {
                            System.out.print("Enter Student ID to search: ");
                            int searchId = Integer.parseInt(scanner.nextLine());
                            Student student = stub.searchStudent(searchId);
                            if (student != null) {
                                System.out.println("Found student: " + student.getName() + ", Birthday: " + student.getBirthday() + ", Email: " + student.getEmail());
                            } else {
                                System.out.println("Student not found with ID " + searchId);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for ID. Please enter a valid integer.");
                        } catch (Exception e) {
                            System.out.println("Error searching student: " + e.getMessage());
                        }
                        break;

                    case 4:
                        // Cập nhật thông tin sinh viên
                        try {
                            System.out.print("Enter Student ID to update: ");
                            int updateId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter new Name: ");
                            String newName = scanner.nextLine();
                            System.out.print("Enter new Birthday (YYYY-MM-DD): ");
                            String newBirthday = scanner.nextLine();
                            System.out.print("Enter new Email: ");
                            String newEmail = scanner.nextLine();

                            stub.updateStudent(updateId, newName, newBirthday, newEmail);
                            System.out.println("Student updated successfully!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for ID. Please enter a valid integer.");
                        } catch (Exception e) {
                            System.out.println("Error updating student: " + e.getMessage());
                        }
                        break;

                    case 5:
                        // Xóa sinh viên
                        try {
                            System.out.print("Enter Student ID to delete: ");
                            int deleteId = Integer.parseInt(scanner.nextLine());
                            stub.deleteStudent(deleteId);
                            System.out.println("Student deleted successfully!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for ID. Please enter a valid integer.");
                        } catch (Exception e) {
                            System.out.println("Error deleting student: " + e.getMessage());
                        }
                        break;

                    case 6:
                        // Thoát
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error connecting to the server: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
