package example.jdbc.java.reflection;

import example.jdbc.java.reflection.annotation.Column;
import example.jdbc.java.reflection.annotation.Id;
import example.jdbc.java.reflection.annotation.Table;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class MainReflection {
    public static void main(String[] args) {
        // Quét toàn bộ project xem class nào đc đánh dấu là
        Reflections reflections = new Reflections("example.jdbc.java");
        // @Table, trả về 1 set tập hợp các class đc đánh dấu
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Table.class);
        for (Class<?> clazz : annotated) {
            // thực hiện migrate cho class đó
            doMigrate(clazz);
        }

    }
    static void showInformation(Object obj) {
        System.out.println("====================");
        System.out.println("Show thông tin.");
        Class clazz = obj.getClass();
        if (clazz.getSimpleName().equals("Dog")) {
            System.out.println("Con chó");
        } else if (clazz.getSimpleName().equals("Prduct")) {
            System.out.println("Sản phẩm");
        }

        System.out.println(clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("Danh sách Fields");
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            System.out.println(fields[i].getType().getSimpleName());
            try {
                fields[i].setAccessible(true);
                System.out.println(fields[i].get(obj)); // lấy giá trị của trường theo object truyền vào
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // trả về danh sách các phương thức
        System.out.println("Danh sách phương thức");
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getName());
            System.out.println(methods[i].getReturnType().getName());
           /* Class[] clazzParametersType = methods[i].getParameterTypes();
            for (int j = 0; j < clazzParametersType.length; j++) {
                System.out.println(clazzParametersType[j].getName());
            }*/
        }

        clazz.getDeclaredConstructors();
    }

    static void doMigrate(Class clazz) {
        System.out.println("====================");
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("Migrating class: " + clazz.getName());
        if (!clazz.isAnnotationPresent(Table.class)) {
            System.err.println("Class không đc xác định là table. Bỏ qua migration!");
            return;
        }

        // chắc chắn class đã đc đánh dấu annotation là @Table
        // @Table
        // Lấy thông tin annotation ra
        Table annotationTable = (Table) clazz.getAnnotation(Table.class);

        String annotationTableName = annotationTable.name();
        String tableName = clazz.getSimpleName().toLowerCase() + "s";
        if (annotationTableName != null && annotationTableName.length() > 0) {
            tableName = annotationTableName;
        }
        System.out.println("Table name: " + annotationTableName);

        String name = clazz.getSimpleName();

        stringBuilder.append("create table");
        stringBuilder.append(" ");
        stringBuilder.append(tableName);
        stringBuilder.append(" ");
        stringBuilder.append("(");

        // trả về danh sách các thuộc tính.
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String fieldType = fields[i].getType().getSimpleName();
            fields[i].setAccessible(true);
            if (fields[i].isAnnotationPresent(Column.class)) {
                Column annotationColumn = fields[i].getAnnotation(Column.class);
                if (annotationColumn.name().length() > 0) {
                    fieldName = annotationColumn.name();
                }
                if (annotationColumn.type().length() > 0) {
                    fieldType = annotationColumn.type();
                }
            }

            stringBuilder.append(fieldName);
            stringBuilder.append(" ");
            stringBuilder.append(fieldType);
//            if (fieldType.contains("int")) {
//                stringBuilder.append("int");
//            } else if (fieldType.contains("String")) {
//                stringBuilder.append("varchar(250)");
//            } else if (fieldType.contains("double")) {
//                stringBuilder.append("double");
//            }

            // Check xem trường có phải là khóa chính hay không
            if (fields[i].isAnnotationPresent(Id.class)) {
                stringBuilder.append(" ");
                stringBuilder.append("primary key");
                // lấy ra thông tin để check ttinh auto increment
                Id annotationId = fields[i].getAnnotation(Id.class);
                if (annotationId.autoIncrement()) {
                    stringBuilder.append(" ");
                    stringBuilder.append("AUTO_INCREMENT");
                }
            }
            stringBuilder.append(",");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append(")");

        // trả về danh sách các phương thức
//        System.out.println("Danh sách phương thức");
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
//            System.out.println(methods[i].getName());
//            System.out.println(methods[i].getReturnType().getName());
           /* Class[] clazzParametersType = methods[i].getParameterTypes();
            for (int j = 0; j < clazzParametersType.length; j++) {
                System.out.println(clazzParametersType[j].getName());
            }*/
        }

        clazz.getDeclaredConstructors();
        Connection cnn = null;
        try {
            cnn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jdbc-mysql-example", "root", "");
            Statement stt = cnn.createStatement();
            try {
                System.out.println("Try to drop table: '" + tableName + "' before create");
                stt.execute("drop table " + tableName);
                System.out.println("Drop table '" + tableName +  "' successfully");
            } catch (Exception ex) {
                System.out.println("Drop table failed, errors: " + ex.getMessage());
            }
            System.out.println("Try to execute statement: '" + stringBuilder.toString() + "'");
            stt.execute(stringBuilder.toString());
            System.out.println("Create table success!");
        } catch (SQLException e) {
            System.err.println("Create table failed, errors: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
