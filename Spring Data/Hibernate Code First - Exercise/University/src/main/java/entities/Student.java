package entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {
    @Column(name = "average_grade")
    private Double average_grade;
    @Column(name = "attendance")
    private int attendance;

    public Double getAverage_grade() {
        return average_grade;
    }

    public void setAverage_grade(Double average_grade) {
        this.average_grade = average_grade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public Student() {

    }
}
