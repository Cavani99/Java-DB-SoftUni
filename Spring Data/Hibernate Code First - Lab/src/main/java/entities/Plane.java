package entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "plane")
public class Plane extends Vehicle {
    @Column(name = "passengerCapacity")
    private Integer passengerCapacity;

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Plane() {
    }

    public Plane(String model, BigDecimal price, String fuelType, Integer passengerCapacity) {
        super(model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }
}
