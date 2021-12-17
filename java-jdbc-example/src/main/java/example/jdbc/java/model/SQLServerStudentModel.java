package example.jdbc.java.model;

import example.jdbc.java.entity.Student;

import java.util.ArrayList;

public class SQLServerStudentModel implements IStudentModel{
    @Override
    public ArrayList<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(int rollNumber) {
        return null;
    }

    @Override
    public boolean save(Student student) {
        return false;
    }

    @Override
    public boolean update(int rollNumber, Student student) {
        return false;
    }

    @Override
    public boolean delete(int rollNumber) {
        return false;
    }
}
