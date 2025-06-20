package DB_Introduction_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int countMinions = 0;

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        connection.setAutoCommit(false);

        PreparedStatement stmt = connection.prepareStatement("""
                SELECT count(villain_id) as count
                FROM minions_villains
                WHERE villain_id = ?;""");
        System.out.print("Write villain id: ");
        int id = Integer.parseInt(sc.nextLine());
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            countMinions = rs.getInt("count");
        }

        PreparedStatement deleteStmt = connection.prepareStatement("""
                DELETE FROM minions_villains
                WHERE villain_id = ?;""");
        deleteStmt.setInt(1, id);

        int affectedRows = deleteStmt.executeUpdate();

        if (affectedRows == 0) {
            System.out.println("No such villain was found");
            connection.rollback();
        } else {
            PreparedStatement selectStmt = connection.prepareStatement("""
                    SELECT name
                    FROM villains
                    WHERE id = ?;""");
            selectStmt.setInt(1, id);

            ResultSet selectRes = selectStmt.executeQuery();
            String name;
            if (selectRes.next()) {
                name = selectRes.getString("name");
            } else {
                System.out.println("No such villain was found");
                return;
            }

            PreparedStatement deleteVillain = connection.prepareStatement("""
                    DELETE FROM villains
                    WHERE id = ?;""");
            deleteVillain.setInt(1, id);

            int deleteRow = deleteVillain.executeUpdate();
            if (deleteRow == 0) {
                System.out.println("No such villain was found");
                connection.rollback();
            } else {
                connection.commit();
                System.out.printf("%s was deleted\n", name);
                System.out.printf("%d minions released", countMinions);
            }
        }
    }
}
