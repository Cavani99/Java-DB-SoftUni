package orm;

import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        // JPQL query to get all towns
        List<Town> towns = em.createQuery("SELECT t FROM Town t", Town.class)
                .getResultList();

        for (Town town : towns) {
            if (town.getName().length() <= 5) {
                em.createQuery("UPDATE Town t SET t.name = UPPER(t.name) WHERE t.id = :id")
                        .setParameter("id", town.getId())
                        .executeUpdate();
            } else {
                em.detach(town);
            }
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
