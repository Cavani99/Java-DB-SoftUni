package relations;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "drivers")
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "full_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "drivers_trucks",
            joinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"))
    private Set<Trucks> trucks;

    public Set<Trucks> getTrucks() {
        return trucks;
    }

    public void setTrucks(Set<Trucks> trucks) {
        this.trucks = trucks;
    }

    public Drivers() {

    }
}
