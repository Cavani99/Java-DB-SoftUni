package orm;

import entities.Department;
import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Department> departments = em.createQuery(
                        "SELECT d FROM Department d " +
                                "WHERE name = 'Research and Development'", Department.class)
                .getResultList();

        for (Department department : departments) {

            List<Employee> employees = em.createQuery(
                            "SELECT e FROM Employee e " +
                                    "WHERE e.department = :department_id " +
                                    "ORDER BY e.salary ASC, e.id ASC", Employee.class)
                    .setParameter("department_id", department)
                    .getResultList();

            for (Employee employee : employees) {
                System.out.printf("%s %s from %s - $%.2f\n",
                        employee.getFirstName(), employee.getLastName(), department.getName(), employee.getSalary());
            }
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
