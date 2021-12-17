package example.jdbc.java;

import example.jdbc.java.entity.Customer;
import example.jdbc.java.entity.Student;
import example.jdbc.java.model.MasterModel;

public class MainThread {
    public static void main(String[] args) {
        MasterModel<Student> studentMasterModel = new MasterModel<>();
        studentMasterModel.save(new Student(1, "an", "anawef", 2));
//        MasterModel<Dog> dogMasterModel = new MasterModel<>();
//        dogMasterModel.save(new Dog(1, "con cho"));
        MasterModel<Customer> customerMasterModel = new MasterModel<>();
        customerMasterModel.save(new Customer(1, "Hung", "hung@gmail.com", "address1", "0912", 1));
    }
}
