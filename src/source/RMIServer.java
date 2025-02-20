package source;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;

public class RMIServer {
    private static final String URL = "...";
    private static final String USER = "...";
    private static final String PASSWORD = "...";

    public static void main(String[] args) {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // Kiểm tra kết nối cơ sở dữ liệu trước khi khởi động server
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Database connected successfully!");
            }

            // Khởi tạo registry trên cổng 1099
            LocateRegistry.createRegistry(1099);
            
            // Tạo instance của StudentManagementImpl
            StudentManagementImpl studentManagement = new StudentManagementImpl();
            
            // Bind object vào RMI Registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("StudentManagement", studentManagement);
            
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

