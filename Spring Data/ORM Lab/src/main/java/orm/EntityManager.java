package orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    private String tableName;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Id {

    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Entity {
        String name();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Column {
        String name();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Table {
        String name();
    }

    private String getTableName(Class<?> entity) {
        if (entity.isAnnotationPresent(Table.class)) {
            Table table = entity.getAnnotation(Table.class);
            return table.name();
        }
        return "";
    }

    public String getTableNameFromEntity(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            return table.name();
        }
        return "";
    }

    private Field getId(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);

        if (value == null || (long) value <= 0) {
            return doInsert(entity, primaryKey);
        }

        return doUpdate(entity, primaryKey);
    }

    private boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        String query = "UPDATE " + this.getTableName(entity.getClass()) + " SET";

        return connection.prepareStatement(query).execute();
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String query = "INSERT INTO " + this.getTableName(entity.getClass()) + " (";

        return connection.prepareStatement(query).execute();
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Statement statement = connection.createStatement();
        String tableName = getTableNameFromEntity(table);

        String query = String.format("SELECT * FROM %s", tableName);

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<E> entities = new ArrayList<>();
        while (resultSet.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            fillEntity(table, resultSet, entity);  // populate the current entity
            entities.add(entity);
        }

        return entities;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Statement statement = connection.createStatement();
        String tableName = getTableNameFromEntity(table);

        String query = String.format("SELECT * FROM %s %s", tableName,
                where != null ? " WHERE " + where : "");

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<E> entities = new ArrayList<>();
        while (resultSet.next()) {
            E entity = table.getDeclaredConstructor().newInstance();
            fillEntity(table, resultSet, entity);  // populate the current entity
            entities.add(entity);
        }

        return entities;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Statement statement = connection.createStatement();
        String tableName = getTableNameFromEntity(table);

        String query = String.format("SELECT * FROM %s LIMIT 1;", tableName);

        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.getDeclaredConstructor().newInstance();

        resultSet.next();
        fillEntity(table, resultSet, entity);

        return entity;
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Statement statement = connection.createStatement();
        String tableName = getTableNameFromEntity(table);

        String query = String.format("SELECT * FROM %s %s LIMIT 1;", tableName,
                where != null ? " WHERE " + where : "");

        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.getDeclaredConstructor().newInstance();

        resultSet.next();
        fillEntity(table, resultSet, entity);

        return entity;
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = Arrays.stream(table.getDeclaredFields())
                .toArray(Field[]::new);

        for (Field field : declaredFields) {
            field.setAccessible(true);
            fillField(field, resultSet, entity);
        }
    }

    private void fillField(Field field, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        field.setAccessible(true);

        if (field.getType() == int.class || field.getType() == long.class) {
            field.set(entity, resultSet.getInt(field.getName()));
        } else if (field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(resultSet.getString(field.getName())));
        } else {
            field.set(entity, resultSet.getString(field.getName()));
        }
    }
}
