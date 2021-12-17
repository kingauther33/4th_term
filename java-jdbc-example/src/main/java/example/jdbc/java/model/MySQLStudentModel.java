package example.jdbc.java.model;

import example.jdbc.java.entity.Student;

import java.sql.*;
import java.util.ArrayList;

public class MySQLStudentModel implements IStudentModel {

    @Override
    public ArrayList<Student> findAll() {

        try {
            //
            ArrayList<Student> listStudents = new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jdbc-mysql-example", "root", "");
            Statement stt = conn.createStatement();
//            stt.execute("insert into students (name, email) values ('An Dinh', 'kingauther33@gmail.com')");
            ResultSet resultSet = stt.executeQuery("select * from students where status != -1");
            while (resultSet.next()) {
                Student student = new Student();

                student.setRollNumber(resultSet.getInt("rollNumber"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setStatus(resultSet.getInt("status"));

                listStudents.add(student);
            }

            System.out.println("Process success!");
            return listStudents;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Student findById(int rollNumber) {
        try {
            //
            ArrayList<Student> listStudents = new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jdbc-mysql-example", "root", "");
            Statement stt = conn.createStatement();
//            stt.execute("insert into students (name, email) values ('An Dinh', 'kingauther33@gmail.com')");
            ResultSet resultSet = stt.executeQuery("select * from students where rollNumber = " + rollNumber + " and status != -1");
            if (resultSet.next()) {
                Student student = new Student();

                student.setRollNumber(resultSet.getInt("rollNumber"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setStatus(resultSet.getInt("status"));

                listStudents.add(student);

                return student;
            }

            System.out.println("Process success!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Student student) {
        try {
            //
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jdbc-mysql-example", "root", "");
            Statement stt = conn.createStatement();
//            String studentName = student.getName();
//            String studentEmail = student.getEmail();
//            String sql = "insert into students (name, email) values (?, ?)";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, studentName);
//            stmt.setString(2, studentEmail);
//            stmt.executeUpdate();
            stt.execute("insert into students (name, email) values ('" + student.getName() + "', '" + student.getEmail() + "')");
//            ResultSet resultSet = stt.executeQuery("select * from students");
//            while (resultSet.next()) {
//                resultSet.getInt("id");
//                resultSet.getString("name");
//                resultSet.getString("email");
//                resultSet.getInt("status");
//            }
            System.out.println("Process success!");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(int rollNumber, Student student) {
        try {
            //
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jdbc-mysql-example", "root", "");
            Statement stt = conn.createStatement();
            stt.execute("update students set name = '" + student.getName() + "', email = '" + student.getEmail() + "' where rollNumber = " + rollNumber);
            System.out.println("Process success!");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int rollNumber) {
        try {
            //
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jdbc-mysql-example", "root", "");
            Statement stt = conn.createStatement();
            stt.execute("update students set status = -1 where rollNumber = " + rollNumber);
            System.out.println("Delete success!");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
