package orm;


import entities.Employee;
import entities.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GetEmployeesWithProject {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Scanner sc = new Scanner(System.in);
        System.out.print("Write employee id: ");
        String employeeId = sc.nextLine();

        Employee employee = em.createQuery(
                        "SELECT e FROM Employee e " +
                                "WHERE e.id = :id", Employee.class)
                .setParameter("id", employeeId)
                .getSingleResult();


        System.out.println("Employee: " + employee.getFirstName());
        List<Project> projects = employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());
        StringBuilder projectText = new StringBuilder();
        for (Project project : projects) {
            projectText.append("   ").append(project.getName()).append("\n");
        }

        System.out.printf("%s %s - %s\n%s", employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), projectText);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
