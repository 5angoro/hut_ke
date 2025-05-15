package models;

import io.ebean.Model;
import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class PropertyManager extends User {
    private String companyName;
    private String licenseNumber;
    private boolean isVerified = false;
    private String verificationDocuments; // Could be JSON

    @OneToMany(mappedBy = "manager")
    private List<Property> properties;

    // Getters and setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
    public String getVerificationDocuments() { return verificationDocuments; }
    public void setVerificationDocuments(String verificationDocuments) { this.verificationDocuments = verificationDocuments; }
    public List<Property> getProperties() { return properties; }
    public void setProperties(List<Property> properties) { this.properties = properties; }
}