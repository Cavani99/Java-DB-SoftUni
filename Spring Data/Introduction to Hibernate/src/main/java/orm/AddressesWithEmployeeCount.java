package orm;


import jakarta.persistence.*;

import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();


        Query query = em.createQuery(
                "SELECT a.text, t.name, COUNT(e) " +
                        "FROM Employee e " +
                        "JOIN e.address a " +
                        "JOIN a.town t " +
                        "GROUP BY a.id, a.text, t.name " +
                        "ORDER BY COUNT(e) DESC"
        );
        query.setMaxResults(10);

        List<Object[]> results = query.getResultList();
        for (Object[] row : results) {
            String addressText = (String) row[0];
            String townName = (String) row[1];
            Long employeeCount = (Long) row[2];

            System.out.printf("%s, %s - %d employees\n", addressText, townName, employeeCount);
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
