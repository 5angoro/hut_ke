package models;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table (name="tenants")
public class Tenant extends User {
    private String idNumber;
    private String idType; // enum would be better
    private String idDocumentUrl;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String employmentStatus;
    private String employerName;
    private String employerContact;
    private double monthlyIncome;
    public static Finder<Long, User> find = new Finder<>(User.class);

    public static Finder<Long, User> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, User> find) {
        User.find = find;
    }
    public static User findUser(String email, String passwordHash) {
        return User.find.query().where().eq("email", email).eq("password_hash", passwordHash).setMaxRows(1).findOne();
    }

    @OneToMany(mappedBy = "tenant")
    private List<Lease> leases;

    // Getters and setters
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public String getIdType() { return idType; }
    public void setIdType(String idType) { this.idType = idType; }
    public String getIdDocumentUrl() { return idDocumentUrl; }
    public void setIdDocumentUrl(String idDocumentUrl) { this.idDocumentUrl = idDocumentUrl; }
    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }
    public String getEmploymentStatus() { return employmentStatus; }
    public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; }
    public String getEmployerName() { return employerName; }
    public void setEmployerName(String employerName) { this.employerName = employerName; }
    public String getEmployerContact() { return employerContact; }
    public void setEmployerContact(String employerContact) { this.employerContact = employerContact; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(double monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public List<Lease> getLeases() { return leases; }
    public void setLeases(List<Lease> leases) { this.leases = leases; }
    public String getPropertyId() {
        return null;
    }
}