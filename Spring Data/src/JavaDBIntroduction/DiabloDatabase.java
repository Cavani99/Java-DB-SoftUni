package JavaDBIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloDatabase {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt = connection.prepareStatement("""
                SELECT first_name,last_name, count(game_id) as games
                FROM users as u
                JOIN users_games as g on u.id = g.user_id
                WHERE user_name = ?""");
        System.out.print("Write username: ");
        String user = sc.nextLine();
        stmt.setString(1, user);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("first_name");
            int count_game = rs.getInt("games");

            if (first_name == null) {
                System.out.println("No such user exists");
                break;
            }
            System.out.printf("User: %s\n", user);
            System.out.printf("%s %s has played %d games\n", first_name, last_name, count_game);
        }
    }
}
