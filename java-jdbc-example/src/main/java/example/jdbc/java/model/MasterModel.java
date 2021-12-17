package example.jdbc.java.model;

import example.jdbc.java.reflection.annotation.Column;
import example.jdbc.java.reflection.annotation.Id;
import example.jdbc.java.reflection.annotation.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MasterModel<T> {

    public List<T> findAll() {
        List<T> list = new ArrayList<>();

        return list;
    }

    public T findById(int id) {
        return null;
    }

    public boolean save(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            System.err.println("Class không được đánh dấu là table trong db. Bỏ qua.");
            return false;
        }

        String tableName = clazz.getSimpleName().toLowerCase() + "s"; // y -> ies
        // 1. check tableName
        Table annotationTable = clazz.getAnnotation(Table.class);
        if (annotationTable.name().length() > 0) {
            tableName = annotationTable.name();
        }

        // 2. danh sách fields.
        StringBuilder fieldNames = new StringBuilder();
        StringBuilder fieldValues = new StringBuilder(); // nhanh trí lấy gtri field trong vòng lặp
        fieldNames.append("(");
        fieldValues.append("(");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // tên field
            String fieldName = field.getName().toLowerCase(); // default name trong trường hợp không set name
            if (field.isAnnotationPresent(Id.class)) {
                Id annotationId = field.getAnnotation(Id.class);
                if (annotationId.autoIncrement()) {
                    continue;
                }
            }
            Column annotationColumn = null;
            if (field.isAnnotationPresent(Column.class)) {
                annotationColumn = field.getAnnotation(Column.class);
                if (annotationColumn.name().length() > 0) {
                    fieldName = annotationColumn.name();
                }
            }

            fieldNames.append(fieldName);
            fieldNames.append(", ");
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (annotationColumn != null &&
                        (annotationColumn.type().contains("varchar") || annotationColumn.type().contains("text"))
                    ) {
                    fieldValues.append("'");
                    fieldValues.append(value);
                    fieldValues.append("'");
                } else {
                    fieldValues.append(value);
                }
                fieldValues.append(", ");
            } catch (Exception e) {

            }

            // kiểu dữ liệu field: int -> giá trị ko nháy, String thì phải có ''
        }

        fieldNames.setLength(fieldNames.length() - 2);
        fieldValues.setLength(fieldValues.length() - 2);
        fieldNames.append(")");
        fieldValues.append(")");

        StringBuilder sttBuilder = new StringBuilder();
        sttBuilder.append("INSERT INTO");
        sttBuilder.append(" ");
        sttBuilder.append(tableName);
        sttBuilder.append(" ");
        sttBuilder.append(fieldNames.toString());
        sttBuilder.append(" ");
        sttBuilder.append("VALUES");
        sttBuilder.append(" ");
        sttBuilder.append(fieldValues.toString());
        System.out.println(sttBuilder.toString());
        // 3. danh sách giá trị của field

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jdbc-mysql-example", "root", "");
            Statement stt = conn.createStatement();
            stt.execute(sttBuilder.toString());
            System.out.println("Process success!");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    public boolean update(int id, T obj) {
        return false;
    }

    public boolean delete(int id) {
        return false;
    }
}
