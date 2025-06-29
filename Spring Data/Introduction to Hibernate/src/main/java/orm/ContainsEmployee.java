package orm;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.Scanner;


public class ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Scanner sc = new Scanner(System.in);
        String[] employeeName = sc.nextLine().split("\\s+");

        String firstName = employeeName[0];
        String lastName = employeeName[1];

        TypedQuery<Employee> query = em.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);


        if (!query.getResultList().isEmpty()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
