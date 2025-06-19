package DB_Introduction_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt = connection.prepareStatement("""
                SELECT v.name as villain ,m.name as minion,age
                FROM villains as v
                JOIN minions_villains as mv on mv.villain_id = v.id
                JOIN minions as m on mv.minion_id = m.id
                WHERE v.id = ?;""");
        System.out.print("Write villain id: ");
        int id = Integer.parseInt(sc.nextLine());
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        boolean first = true;
        int count = 1;
        while (rs.next()) {
            if(first) {
                String villain = rs.getString("villain");

                System.out.printf("Villain: %s\n", villain);
                first = false;
            }
            String minion = rs.getString("minion");
            int age = rs.getInt("age");

            System.out.printf("%d. %s %d\n", count,minion,age);
            count++;
        }
    }
}
