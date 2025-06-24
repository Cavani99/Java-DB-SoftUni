import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        MyConnector.createConnection("root", "", "test");

        Connection connection = MyConnector.getConnection();
        EntityManager<User> entityManager = new EntityManager<>(connection);

        User user = new User("Pesho", 40, LocalDate.of(2021, 6, 20));
        entityManager.persist(user);

        User found = entityManager.findFirst(User.class, "age > 30");
        //System.out.printf("Username: %s, Age: %d", found.getUsername(), found.getAge());

        ArrayList<User> fetchUsers = (ArrayList<User>) entityManager.find(User.class, "year(registration) >= 2020 AND age >= 18");
        for (User currentUser : fetchUsers) {
            System.out.printf("Username: %s, Password: %s\n", currentUser.getUsername(), currentUser.getPassword());
        }
    }
}
