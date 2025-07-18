package DB_Introduction_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        System.out.print("Write minion id: \n");
        int id = Integer.parseInt(sc.nextLine());

        PreparedStatement stmt = connection.prepareStatement("""
                call usp_get_older(?);""");
        stmt.setInt(1, id);
        stmt.executeUpdate();

        PreparedStatement selectStmt = connection.prepareStatement("""
                SELECT name , age
                FROM minions
                WHERE id = ?;""");
        selectStmt.setInt(1, id);

        ResultSet selectRes = selectStmt.executeQuery();
        while (selectRes.next()) {
            String name = selectRes.getString("name");
            int age = selectRes.getInt("age");

            System.out.printf("%s %d\n", name, age);
        }
    }
}
