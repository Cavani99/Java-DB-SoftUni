import entities.vehicles.Bike;
import entities.vehicles.Car;
import entities.vehicles.Plane;
import entities.vehicles.Truck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        getTaskTwo();
    }

    public static void getTaskOne() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vehicle_hierarchy");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Car car = new Car("Toyota", new BigDecimal("10000.24"), "diesel", 4);
        em.persist(car);

        Truck truck = new Truck("MAN", new BigDecimal("230000.24"), "95", 125.00);
        em.persist(truck);

        Bike bike = new Bike("City", new BigDecimal("200.0"), null);
        em.persist(bike);

        Plane plane = new Plane("RyanAir", new BigDecimal("2000000.0"), "light", 60);
        em.persist(plane);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void getTaskTwo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("relations");
        EntityManager em = emf.createEntityManager();

        em.close();
        emf.close();
    }
}
