package com.t1908e.WCD_assignment.data.migration;

import com.t1908e.WCD_assignment.data.seed.SeedCategory;
import com.t1908e.WCD_assignment.data.seed.SeedFood;
import com.t1908e.WCD_assignment.entity.Category;
import com.t1908e.WCD_assignment.entity.Food;
import com.t1908e.WCD_assignment.exception.EntityException;
import com.t1908e.WCD_assignment.modelAnnotation.Column;
import com.t1908e.WCD_assignment.modelAnnotation.Entity;
import com.t1908e.WCD_assignment.modelAnnotation.ForeignKey;
import com.t1908e.WCD_assignment.modelAnnotation.Id;
import com.t1908e.WCD_assignment.util.ConnectionHelper;
import com.t1908e.WCD_assignment.util.SQLConstant;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class InitDatabase {
    /**
     * create table mapping with entity and running seed methods.<br>
     * Note: CALL THIS METHOD WILL RESET ALL YOUR DATA IN YOUR DATABASE
     */
    public static void init() {
        System.out.println("Register tables to database...");
        registerTables();
        System.out.println("Running seed methods");
        //seed methods goe here
        new SeedCategory().seed();
        new SeedFood().seed();
    }

    private static void registerTables() {
        Reflections reflections = new Reflections("com.t1908e.WCD_assignment.entity");
        ArrayList<Class> dependenciesClass = new ArrayList<Class>();
        ArrayList<Class> parentsClass = new ArrayList<Class>();
        Set<Class<?>> allClasses =
                reflections.getTypesAnnotatedWith(Entity.class);
        for (Class<?> c : allClasses) {
            boolean flag = false;
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ForeignKey.class)) {
                    dependenciesClass.add(c);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                parentsClass.add(c);
            }

        }
        for (Class c : dependenciesClass) {
            dropTable(c);
        }
        for (Class c : parentsClass) {
            dropTable(c);
        }

        for (Class c : parentsClass) {
            createTable(c);
        }
        for (Class c : dependenciesClass) {
            createTable(c);
        }
    }

    private static void createTable(Class clazz) {
        try{

            if (!clazz.isAnnotationPresent(Entity.class)) {
                throw new EntityException("Not an entity class");
            }
            Entity currentEntity = (Entity) clazz.getAnnotation(Entity.class);
            //build sql cmd
            StringBuilder stringCmd = new StringBuilder();
            stringCmd.append(SQLConstant.CREATE_TABLE);
            stringCmd.append(SQLConstant.SPACE);
            stringCmd.append(currentEntity.tableName());
            stringCmd.append(SQLConstant.SPACE);
            stringCmd.append(SQLConstant.OPEN_PARENTHESES);
            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                Column currentColumn = field.getAnnotation(Column.class);
                stringCmd.append(currentColumn.columnName());
                stringCmd.append(SQLConstant.SPACE);
                stringCmd.append(currentColumn.columnType());
                //id checker
                if (field.isAnnotationPresent(Id.class)) {
                    Id currentId = (Id) field.getAnnotation(Id.class);
                    stringCmd.append(SQLConstant.SPACE);
                    stringCmd.append(SQLConstant.PRIMARY_KEY);
                    //auto icreament checker
                    if (currentId.AutoIncrement()) {
                        stringCmd.append(SQLConstant.SPACE);
                        stringCmd.append(SQLConstant.AUTO_INCREMENT);
                    }
                }
                stringCmd.append(SQLConstant.COMMA);
                stringCmd.append(SQLConstant.SPACE);
                if (field.isAnnotationPresent(ForeignKey.class)) {
                    ForeignKey foreignKeyInformation = (ForeignKey) field.getAnnotation(ForeignKey.class);
                    String refColumn = foreignKeyInformation.referenceColumn();
                    String refTable = foreignKeyInformation.referenceTable();
                    stringCmd.append(SQLConstant.FOREIGN_KEY);
                    stringCmd.append(SQLConstant.SPACE);
                    stringCmd.append(SQLConstant.OPEN_PARENTHESES);
                    stringCmd.append(currentColumn.columnName());
                    stringCmd.append(SQLConstant.CLOSE_PARENTHESES);
                    stringCmd.append(SQLConstant.SPACE);
                    stringCmd.append(SQLConstant.REFERENCES);
                    stringCmd.append(SQLConstant.SPACE);
                    stringCmd.append(refTable);
                    stringCmd.append(SQLConstant.OPEN_PARENTHESES);
                    stringCmd.append(refColumn);
                    stringCmd.append(SQLConstant.CLOSE_PARENTHESES);
                    stringCmd.append(SQLConstant.COMMA);
                    stringCmd.append(SQLConstant.SPACE);
                }

            }
            stringCmd.setLength(stringCmd.length() - 2);
            stringCmd.append(SQLConstant.CLOSE_PARENTHESES);

            Connection connection = ConnectionHelper.getConnection();
            if(connection == null) {
                throw new EntityException("Can not connect to db");
            }
            connection.createStatement().execute(stringCmd.toString());
        } catch (SQLException | EntityException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    private static void dropTable(Class clazz) {
        try {
            if (!clazz.isAnnotationPresent(Entity.class)) {
                throw new EntityException("Not an entity class");
            }
            Entity currentEntity = (Entity) clazz.getAnnotation(Entity.class);
            //drop cmd
            StringBuilder dropCmd = new StringBuilder();
            dropCmd.append(SQLConstant.DROP_TABLE);
            dropCmd.append(SQLConstant.SPACE);
            dropCmd.append(SQLConstant.IF_EXISTS);
            dropCmd.append(SQLConstant.SPACE);
            dropCmd.append(currentEntity.tableName());
            Connection connection = ConnectionHelper.getConnection();
            if(connection == null) {
                throw new EntityException("Can not connect to db");
            }
            connection.createStatement().execute(dropCmd.toString());
        } catch (EntityException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
