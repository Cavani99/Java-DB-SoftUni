package orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;

    private String tableName;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
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
        Class<?> entityClass = entity.getClass();

        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .toList();

        String columns = fields.stream()
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(", "));

        String values = fields.stream()
                .map(f -> "?")
                .collect(Collectors.joining(", "));

        String tableName = this.getTableName(entity.getClass());
        String query = "INSERT INTO " + tableName + " (";
        query += columns + ") VALUES (" + values + ")";


        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setAccessible(true);
            Object value = fields.get(i).get(entity);
            stmt.setObject(i + 1, value);
        }

        int rows = stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            Object idValue = generatedKeys.getObject(1);
            primary.setAccessible(true);

            if (primary.getType().equals(long.class) || primary.getType().equals(Long.class)) {
                primary.set(entity, ((Number) idValue).longValue());
            } else {
                primary.set(entity, idValue);
            }
        }

        return rows > 0;
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        Class<?> entityClass = entity.getClass();

        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .toList();

        String setFields = fields.stream()
                .map(f -> f.getAnnotation(Column.class).name() + " = ?")
                .collect(Collectors.joining(", "));

        String primaryKeyColumn = primary.getAnnotation(Column.class).name();
        Object primaryKeyValue;
        primary.setAccessible(true);
        primaryKeyValue = primary.get(entity);

        String tableName = this.getTableName(entity.getClass());
        String query = "UPDATE " + tableName + " SET";
        query += setFields + " WHERE " + primaryKeyColumn + " = ?";

        PreparedStatement stmt = connection.prepareStatement(query);

        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setAccessible(true);
            Object value = fields.get(i).get(entity);
            stmt.setObject(i + 1, value);
        }

        stmt.setObject(fields.size() + 1, primaryKeyValue);

        return stmt.executeUpdate() > 0;
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
