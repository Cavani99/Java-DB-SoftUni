package entities.vehicles;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "bike")
public class Bike extends Vehicle{

    public Bike() {
    }

    public Bike(String model, BigDecimal price, String fuelType) {
        super(model, price, fuelType);
    }
}
