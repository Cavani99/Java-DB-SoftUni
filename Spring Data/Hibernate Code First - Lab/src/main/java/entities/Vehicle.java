package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Basic
    @Column(insertable = false, updatable = false)
    private String type;

    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "fuel_type")
    private String fuelType;

    protected Vehicle() {
    }

    protected Vehicle(String model, BigDecimal price, String fuelType) {
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
    }
}
