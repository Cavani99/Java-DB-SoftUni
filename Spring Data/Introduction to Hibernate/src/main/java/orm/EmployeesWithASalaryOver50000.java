package orm;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeesWithASalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> employees = em.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE salary > 50000", Employee.class)
                .getResultList();


        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
