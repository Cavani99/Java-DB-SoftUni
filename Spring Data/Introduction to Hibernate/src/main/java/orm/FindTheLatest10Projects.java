package orm;

import entities.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class FindTheLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(
                "SELECT p " +
                        "FROM Project p " +
                        "ORDER BY year(startDate) DESC, p.name ASC", Project.class
        );
        query.setMaxResults(10);


        List<Project> projects = query.getResultList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        for (Project row : projects) {
            System.out.printf("Project name: %s\n  Project Description: %s\n  " +
                    "Project Start Date: %s\n  Project End Date: %s\n", row.getName(), row.getDescription(), row.getStartDate().format(formatter), row.getEndDate());
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
