package DB_Introduction_Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        connection.setAutoCommit(false);

        PreparedStatement stmtASC = connection.prepareStatement("""
                SELECT name
                FROM minions
                ORDER BY id ASC;""");

        ResultSet rsASC = stmtASC.executeQuery();

        PreparedStatement stmtDESC = connection.prepareStatement("""
                SELECT name
                FROM minions
                ORDER BY id DESC;""");

        ResultSet rsDESC = stmtDESC.executeQuery();

        ArrayList<String> ascendingNames = new ArrayList<>();
        while(rsASC.next()){
            String name = rsASC.getString("name");
            ascendingNames.add(name);
        }
        int index = 0;
        while(index < ascendingNames.size() / 2){
            System.out.println(ascendingNames.get(index));
            if(rsDESC.next()){
                System.out.println(rsDESC.getString("name"));
            }
            index++;
        }
    }
}
