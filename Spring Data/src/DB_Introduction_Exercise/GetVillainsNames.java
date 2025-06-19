package DB_Introduction_Exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt = connection.prepareStatement("""
                SELECT name, count(minion_id) as count
                                   FROM villains as v
                                   JOIN minions_villains as mv on mv.villain_id = v.id
                                   GROUP BY villain_id
                                   HAVING count(minion_id) > 15
                                   ORDER BY count(minion_id) DESC;""");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int count = rs.getInt("count");

            System.out.printf("%s %d\n", name,count);
        }
    }
}
