package DB_Introduction_Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        System.out.print("Write minions id: \n");
        String input = sc.nextLine();

        String[] parts = input.split(" "); // splits based on spaces
        ArrayList<Integer> ids = new ArrayList<>();

        for (String part : parts) {
            ids.add(Integer.parseInt(part));
        }

        for (Integer id : ids) {
            PreparedStatement stmt = connection.prepareStatement("""
                    UPDATE minions
                    SET age = age + 1, name = lower(name)
                    WHERE id = ?;""");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        PreparedStatement selectStmt = connection.prepareStatement("""
                SELECT name , age
                FROM minions
                ORDER BY id;""");

        ResultSet selectRes = selectStmt.executeQuery();
        while (selectRes.next()) {
            String name = selectRes.getString("name");
            int age = selectRes.getInt("age");

            System.out.printf("%s %d\n", name, age);
        }
    }
}
