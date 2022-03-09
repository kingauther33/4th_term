package com.demo;

import com.demo.entity.Student;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class DemoWebservice {

    @WebMethod
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @WebMethod
    public String sayGoodbye(String name) {
        return "Goodbye " + name;
    }

    @WebMethod
    public Student getStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("An");
        student.setAge(10);
        return student;
    }
}
