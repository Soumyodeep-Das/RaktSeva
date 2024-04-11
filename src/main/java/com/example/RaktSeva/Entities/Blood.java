package com.example.RaktSeva.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "blood")
public class Blood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id", nullable = false)
    private BloodBank bloodBank;

    @Column(name = "blood_group", nullable = false)
    private String bloodGroup;

    @Column(name = "blood_amount")
    private String bloodAmount;

    @Column(name = "blood_donation_date")
    private String bloodDonationDate;

    @Column(name = "blood_expire_date")
    private String bloodExpireDate;

    @Column(name = "blood_price")
    private String bloodPrice;

    // Constructors, Getters, and Setters

    public Blood() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodAmount() {
        return bloodAmount;
    }

    public void setBloodAmount(String bloodAmount) {
        this.bloodAmount = bloodAmount;
    }

    public String getBloodDonationDate() {
        return bloodDonationDate;
    }

    public void setBloodDonationDate(String bloodDonationDate) {
        this.bloodDonationDate = bloodDonationDate;
    }

    public String getBloodExpireDate() {
        return bloodExpireDate;
    }

    public void setBloodExpireDate(String bloodExpireDate) {
        this.bloodExpireDate = bloodExpireDate;
    }

    public String getBloodPrice() {
        return bloodPrice;
    }

    public void setBloodPrice(String bloodPrice) {
        this.bloodPrice = bloodPrice;
    }

    @Override
    public String toString() {
        return "Blood{" +
                "id=" + id +
                ", bloodBank=" + bloodBank +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", bloodAmount='" + bloodAmount + '\'' +
                ", bloodDonationDate=" + bloodDonationDate +
                ", bloodExpireDate=" + bloodExpireDate +
                ", bloodPrice='" + bloodPrice + '\'' +
                '}';
    }
}
