package relations;

import jakarta.persistence.*;

import java.security.PublicKey;

@Entity
@Table(name = "plate_numbers")
public class Plate_number {

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "number")
    private String number;

    public Plate_number() {
    }
}
