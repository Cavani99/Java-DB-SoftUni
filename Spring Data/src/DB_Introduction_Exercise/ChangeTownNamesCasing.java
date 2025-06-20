package DB_Introduction_Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt = connection.prepareStatement("""
                UPDATE towns
                SET name = upper(name)
                WHERE country = ?;""");
        System.out.print("Write country: ");
        String country = sc.nextLine();
        stmt.setString(1, country);

        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            System.out.println("No town names were affected.");
        } else {
            PreparedStatement selectStmt = connection.prepareStatement("""
                    SELECT name FROM towns
                    WHERE country = ?;""");
            selectStmt.setString(1, country);

            ResultSet rs = selectStmt.executeQuery();
            int count = 0;
            ArrayList <String> cities = new ArrayList<>();
            while (rs.next()) {
                String city = rs.getString("name");

                cities.add(city);
                count++;
            }
            System.out.printf("%d town names were affected.\n", count);
            System.out.println(cities);
        }
    }
}
