package source;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String birthday;  // Giữ ở dạng String để đảm bảo tuần tự hóa
    private String email;

    public Student(int id, String name, String birthday, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday; // Định dạng: YYYY-MM-DD
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBirthday() { return birthday; }  // Trả về String
    public String getEmail() { return email; }
}


