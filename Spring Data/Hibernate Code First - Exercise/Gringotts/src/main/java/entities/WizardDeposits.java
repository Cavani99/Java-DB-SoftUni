package entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "first_name", length = 50)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 60)
    private String last_name;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magic_wand_creator;

    @Column(name = "magic_wand_size")
    private int magic_wand_size;

    @Column(name = "deposit_group", length = 20)
    private int deposit_group;

    @Column(name = "deposit_start_date")
    private LocalDateTime deposit_start_date;

    @Column(name = "deposit_amount")
    private float deposit_amount;

    @Column(name = "deposit_interest")
    private float deposit_interest;

    @Column(name = "deposit_charge")
    private float deposit_charge;

    @Column(name = "deposit_expiration_date")
    private LocalDateTime deposit_expiration_date;

    @Column(name = "is_deposit_expired")
    private boolean is_deposit_expired;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMagic_wand_creator() {
        return magic_wand_creator;
    }

    public void setMagic_wand_creator(String magic_wand_creator) {
        this.magic_wand_creator = magic_wand_creator;
    }

    public int getMagic_wand_size() {
        return magic_wand_size;
    }

    public void setMagic_wand_size(int magic_wand_size) {
        this.magic_wand_size = magic_wand_size;
    }

    public int getDeposit_group() {
        return deposit_group;
    }

    public void setDeposit_group(int deposit_group) {
        this.deposit_group = deposit_group;
    }

    public LocalDateTime getDeposit_start_date() {
        return deposit_start_date;
    }

    public void setDeposit_start_date(LocalDateTime deposit_start_date) {
        this.deposit_start_date = deposit_start_date;
    }

    public float getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(float deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public float getDeposit_interest() {
        return deposit_interest;
    }

    public void setDeposit_interest(float deposit_interest) {
        this.deposit_interest = deposit_interest;
    }

    public float getDeposit_charge() {
        return deposit_charge;
    }

    public void setDeposit_charge(float deposit_charge) {
        this.deposit_charge = deposit_charge;
    }

    public LocalDateTime getDeposit_expiration_date() {
        return deposit_expiration_date;
    }

    public void setDeposit_expiration_date(LocalDateTime deposit_expiration_date) {
        this.deposit_expiration_date = deposit_expiration_date;
    }

    public boolean isIs_deposit_expired() {
        return is_deposit_expired;
    }

    public void setIs_deposit_expired(boolean is_deposit_expired) {
        this.is_deposit_expired = is_deposit_expired;
    }

    public WizardDeposits() {

    }
}
