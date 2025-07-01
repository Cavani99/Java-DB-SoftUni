package relations;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "fuel_type")
    private String fuelType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Plate_number getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(Plate_number plate_number) {
        this.plate_number = plate_number;
    }

    @Column(name = "seats", length = 11)
    private Integer seats;

    @OneToOne(optional = false)
    @JoinColumn(name = "plateNumber_id",
            referencedColumnName = "id")
    private Plate_number plate_number;

    public Cars() {
    }
}
