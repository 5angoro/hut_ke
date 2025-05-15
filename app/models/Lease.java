package models;



import io.ebean.Model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name="leases")
public class Lease extends Model {
    @Id
    private Long id;

    @ManyToOne
    private Unit unit;

    @ManyToOne
    private Tenant tenant;

    @ManyToOne
    private PropertyManager manager;

    private String leaseNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyRent;
    private double depositAmount;
    private int rentDueDay;
    private int paymentGracePeriod = 5;
    private double lateFeePercentage = 5.0;
    private String terms;
    private String status = "pending"; // enum would be better

    @OneToMany(mappedBy = "lease")
    private List<Payment> payments;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Unit getUnit() { return unit; }
    public void setUnit(Unit unit) { this.unit = unit; }
    public Tenant getTenant() { return tenant; }
    public void setTenant(Tenant tenant) { this.tenant = tenant; }
    public PropertyManager getManager() { return manager; }
    public void setManager(PropertyManager manager) { this.manager = manager; }
    public String getLeaseNumber() { return leaseNumber; }
    public void setLeaseNumber(String leaseNumber) { this.leaseNumber = leaseNumber; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public double getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(double monthlyRent) { this.monthlyRent = monthlyRent; }
    public double getDepositAmount() { return depositAmount; }
    public void setDepositAmount(double depositAmount) { this.depositAmount = depositAmount; }
    public int getRentDueDay() { return rentDueDay; }
    public void setRentDueDay(int rentDueDay) { this.rentDueDay = rentDueDay; }
    public int getPaymentGracePeriod() { return paymentGracePeriod; }
    public void setPaymentGracePeriod(int paymentGracePeriod) { this.paymentGracePeriod = paymentGracePeriod; }
    public double getLateFeePercentage() { return lateFeePercentage; }
    public void setLateFeePercentage(double lateFeePercentage) { this.lateFeePercentage = lateFeePercentage; }
    public String getTerms() { return terms; }
    public void setTerms(String terms) { this.terms = terms; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }
}