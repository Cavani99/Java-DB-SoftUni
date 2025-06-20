package DB_Introduction_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class AddMinion {

    private static String minionName;
    private static int minionAge;
    private static String town;
    private static String villainName;

    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String minionInput = sc.nextLine();
        String villainInput = sc.nextLine();

        String[] minionInfo = minionInput.split(" ");
        String[] villainInfo = villainInput.split(" ");

        minionName = minionInfo[1].trim();
        minionAge = Integer.parseInt(minionInfo[2].trim());
        town = minionInfo[3].trim();
        villainName = villainInfo[1].trim();

        int townId = getTownId();
        int villainId = getVillainId();
        int minionId = insertMinion(townId);
        insertMinionsVillains(minionId, villainId);
    }

    static int getTownId() {
        try {

            //get Town
            PreparedStatement selectStmt = connection.prepareStatement("""
                            SELECT id FROM towns WHERE name = ?;
                    """);
            selectStmt.setString(1, town);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                rs.close();
                selectStmt.close();
                return id;
            }
            rs.close();
            selectStmt.close();
            ////////////////////

            //Insert Town
            PreparedStatement insertStmt = connection.prepareStatement("""
                            INSERT INTO towns (name) VALUES (?);
                    """, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, town);
            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating town failed, no rows affected.");
            }

            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                generatedKeys.close();
                insertStmt.close();
                System.out.printf("Town %s was added to the database.\n", town);
                return newId;
            } else {
                insertStmt.close();
                throw new SQLException("Creating town failed, no ID obtained.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static int getVillainId() {
        try {

            //get Villain
            PreparedStatement selectStmt = connection.prepareStatement("""
                            SELECT id FROM villains WHERE name = ?;
                    """);
            selectStmt.setString(1, villainName);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                rs.close();
                selectStmt.close();
                return id;
            }
            rs.close();
            selectStmt.close();
            ////////////////////

            //Insert Villain
            PreparedStatement insertStmt = connection.prepareStatement("""
                            INSERT INTO villains (name, evilness_factor) VALUES (?, 'evil');
                    """, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, villainName);
            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating villain failed, no rows affected.");
            }

            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                generatedKeys.close();
                insertStmt.close();
                System.out.printf("Villain %s was added to the database.\n", villainName);
                return newId;
            } else {
                insertStmt.close();
                throw new SQLException("Creating villain failed, no ID obtained.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static int insertMinion(int townId) {
        try {
            //Insert Town
            PreparedStatement insertStmt = connection.prepareStatement("""
                            INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?);
                    """, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, minionName);
            insertStmt.setInt(2, minionAge);
            insertStmt.setInt(3, townId);
            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating minion failed, no rows affected.");
            }

            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                generatedKeys.close();
                insertStmt.close();
                return newId;
            } else {
                insertStmt.close();
                throw new SQLException("Creating minion failed, no ID obtained.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void insertMinionsVillains(int minionId, int villainId) {
        try {
            PreparedStatement insertStmt = connection.prepareStatement("""
                            INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);
                    """);
            insertStmt.setInt(1, minionId);
            insertStmt.setInt(2, villainId);
            int affectedRows = insertStmt.executeUpdate();

            insertStmt.close();

            if (affectedRows > 0) {
                System.out.printf("Successfully added %s to be minion of %s\n", minionName, villainName);
            } else {
                System.out.println("Insert failed.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
