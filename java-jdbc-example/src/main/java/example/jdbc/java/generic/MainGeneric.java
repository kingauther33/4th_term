package example.jdbc.java.generic;

import example.jdbc.java.entity.Student;
import example.jdbc.java.model.MasterModel;

public class MainGeneric {
    public static void main(String[] args) {
        MasterModel<Student> studentModel = new MasterModel<>();
        studentModel.findAll();

    }
}
