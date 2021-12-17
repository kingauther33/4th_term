package example.jdbc.java.entity;

import example.jdbc.java.reflection.annotation.Column;
import example.jdbc.java.reflection.annotation.Id;
import example.jdbc.java.reflection.annotation.Table;

@Table
public class Student {
    @Id
    private int rollNumber;
    @Column(type = "varchar(100)")
    private String name;
    @Column(type = "varchar(100)")
    private String email;
    @Column(type = "int")
    private int status;

    public Student() {
    }

    public Student(int rollNumber, String name, String email, int status) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNumber=" + rollNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
