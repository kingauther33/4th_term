package example.jdbc.java.reflection;

import example.jdbc.java.reflection.annotation.Column;
import example.jdbc.java.reflection.annotation.Id;
import example.jdbc.java.reflection.annotation.Table;

@Table(name = "con_cho")
public class Dog {

    @Id(autoIncrement = true)
    private int id;
    @Column(name = "ten_con_cho", type = "varchar(113)")
    private String name;

    public Dog() {
    }

    public Dog(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override // comment -> compiler
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
