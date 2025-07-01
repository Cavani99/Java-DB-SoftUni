package entities.vehicles;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "truck")
public class Truck extends Vehicle {

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Column(name = "loadCapacity")
    private Double loadCapacity;

    public Truck() {
    }

    public Truck(String model, BigDecimal price, String fuelType, Double loadCapacity) {
        super(model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

}
