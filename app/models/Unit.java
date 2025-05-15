package models;

import io.ebean.Finder;
import io.ebean.Model;
import jakarta.persistence.*;

import models.Lease;
import java.util.List;

@Entity
@Table (name="units")
public class Unit extends Model {
    @Id
    private Long id;

    @ManyToOne
    private Property property;

    private String unitNumber;
    private String unitName;
    private int bedrooms;
    private double bathrooms;
    private Integer sizeSqft;
    private double monthlyRent;
    private double depositAmount;
    private String features; // JSON
    private String status = "available"; // enum would be better
    private String images; // JSON

    @OneToMany(mappedBy = "unit")
    private List<Lease> leases;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Property getProperty() { return property; }
    public void setProperty(Property property) { this.property = property; }
    public String getUnitNumber() { return unitNumber; }
    public void setUnitNumber(String unitNumber) { this.unitNumber = unitNumber; }
    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }
    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }
    public double getBathrooms() { return bathrooms; }
    public void setBathrooms(double bathrooms) { this.bathrooms = bathrooms; }
    public Integer getSizeSqft() { return sizeSqft; }
    public void setSizeSqft(Integer sizeSqft) { this.sizeSqft = sizeSqft; }
    public double getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(double monthlyRent) { this.monthlyRent = monthlyRent; }
    public double getDepositAmount() { return depositAmount; }
    public void setDepositAmount(double depositAmount) { this.depositAmount = depositAmount; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public List<Lease> getLeases() { return leases; }
    public void setLeases(List<Lease> leases) { this.leases = leases; }
}
