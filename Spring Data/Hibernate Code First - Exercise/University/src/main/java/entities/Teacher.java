package entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    @Column(name = "email")
    private String email;
    @Column(name = "salary_per_hour")
    private Double salary_per_hour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary_per_hour() {
        return salary_per_hour;
    }

    public void setSalary_per_hour(Double salary_per_hour) {
        this.salary_per_hour = salary_per_hour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Teacher() {

    }
}
