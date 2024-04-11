package com.example.RaktSeva.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "blood_banks")
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be of 10 digits")
    private String contactNumber;

    @Email(regexp = "^[0-9a-zA-Z._-]+@[0-9a-zA-Z._-]+\\.[a-zA-Z._-]{2,}$", message = "Must be a well-formed email address")
    private String email;

    @Column(unique = true)
    private String license;
    private String role;

    private boolean statusEnabled; // true if account is active, false if account is disabled
    private String password;
    private String image;
    private String address;
    private String city;
    private String district;
    private String state;
    private String country;
    private String associatedWith; // Hospital Name

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatusEnabled() {
        return statusEnabled;
    }

    public void setStatusEnabled(boolean statusEnabled) {
        this.statusEnabled = statusEnabled;
    }

    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Blood> blood = new ArrayList<>();

    public BloodBank() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAssociatedWith() {
        return associatedWith;
    }

    public void setAssociatedWith(String associatedWith) {
        this.associatedWith = associatedWith;
    }

    public List<Blood> getBlood() {
        return blood;
    }

    public void setBlood(List<Blood> blood) {
        this.blood = blood;
    }

    @Override
    public String toString() {
        return "BloodBank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", license='" + license + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", associatedWith='" + associatedWith + '\'' +
                '}';
    }

    public Object getBank_contact_no() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBank_contact_no'");
    }

    public void setBank_contact_no(Object bank_contact_no) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBank_contact_no'");
    }
}
