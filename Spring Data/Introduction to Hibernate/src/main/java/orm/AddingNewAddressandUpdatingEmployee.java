package orm;

import entities.Address;
import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;


public class AddingNewAddressandUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Scanner sc = new Scanner(System.in);
        String employeeLastName = sc.nextLine();

        Address address = new Address();
        address.setText("Vitoshka 15");
        em.persist(address);

        Address getAddress = em.createQuery(
                        "SELECT a FROM Address a " +
                                "WHERE a.text = :text", Address.class)
                .setParameter("text", address.getText())
                .getSingleResult();

        TypedQuery<Employee> query = em.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", employeeLastName);

        if (!query.getResultList().isEmpty()) {
            List<Employee> employeeList = query.getResultList();
            for (Employee employee : employeeList) {
                employee.setAddress(getAddress);
                em.persist(employee);
            }
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
