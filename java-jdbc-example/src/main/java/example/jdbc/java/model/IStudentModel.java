package example.jdbc.java.model;

import example.jdbc.java.entity.Student;

import java.util.ArrayList;

public interface IStudentModel {
    ArrayList<Student> findAll();
    Student findById(int rollNumber);
    boolean save(Student student);
    boolean update(int rollNumber, Student student);
    boolean delete(int rollNumber);
}
