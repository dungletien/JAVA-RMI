package source;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface StudentManagement extends Remote {
    void addStudent(int id, String name, String birthday, String email) throws RemoteException;

    List<Student> listStudents() throws RemoteException;

    Student searchStudent(int id) throws RemoteException;

    void updateStudent(int id, String name, String birthday, String email) throws RemoteException;

    void deleteStudent(int id) throws RemoteException;
}
