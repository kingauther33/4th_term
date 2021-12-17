package example.jdbc.java.entity;

import example.jdbc.java.reflection.annotation.Column;
import example.jdbc.java.reflection.annotation.Id;
import example.jdbc.java.reflection.annotation.Table;

@Table(name = "customers")
public class Customer {
    @Id(autoIncrement = true)
    private int id;
    @Column(type = "varchar(100)")
    private String name;
    @Column(type = "varchar(100)")
    private String email;
    @Column(type = "varchar(250)")
    private String address;
    @Column(type = "varchar(100)")
    private String phone;
    @Column(type = "int")
    private int status;

    public Customer() {
    }

    public Customer(int id, String name, String email, String address, String phone, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
