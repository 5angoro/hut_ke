package models;



import io.ebean.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name="receipts")
public class Receipt extends Model {
    @Id
    private Long id;

    @OneToOne
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "amount_id")
    private Payment amount;

    @Column(unique = true, nullable = false)
    private String receiptNumber;

    private LocalDateTime issuedAt;

    @ManyToOne
    private PropertyManager issuedBy;

    private String downloadUrl;

    public void setAmount(Payment amount) {
        this.amount = amount;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
    public Payment getAmount() { return amount; }
    public String getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(String receiptNumber) { this.receiptNumber = receiptNumber; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
    public PropertyManager getIssuedBy() { return issuedBy; }
    public void setIssuedBy(PropertyManager issuedBy) { this.issuedBy = issuedBy; }
    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }
}
