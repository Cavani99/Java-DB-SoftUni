package entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "car")
public class Car extends Vehicle {

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Column(name = "seats")
    private Integer seats;

    public Car() {
    }

    public Car(String model, BigDecimal price, String fuelType, Integer seats) {
        super(model, price, fuelType);
        this.seats = seats;
    }

}
