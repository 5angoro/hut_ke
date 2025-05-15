package models;

import io.ebean.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment extends Model {
    @Id
    private Long id;

    @ManyToOne
    private Lease lease;

    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String transactionReference;
    private int paymentPeriodMonth;
    private int paymentPeriodYear;
    private boolean isLate = false;
    private double lateFeeAmount = 0.0;
    private boolean receiptGenerated = false;
    private String receiptNumber;
    private LocalDateTime receiptGeneratedAt;
    private String notes;

    @OneToOne(mappedBy = "payment")
    private Receipt receipt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Lease getLease() { return lease; }
    public void setLease(Lease lease) { this.lease = lease; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }
    public int getPaymentPeriodMonth() { return paymentPeriodMonth; }
    public void setPaymentPeriodMonth(int paymentPeriodMonth) { this.paymentPeriodMonth = paymentPeriodMonth; }
    public int getPaymentPeriodYear() { return paymentPeriodYear; }
    public void setPaymentPeriodYear(int paymentPeriodYear) { this.paymentPeriodYear = paymentPeriodYear; }
    public boolean isLate() { return isLate; }
    public void setLate(boolean late) { isLate = late; }
    public double getLateFeeAmount() { return lateFeeAmount; }
    public void setLateFeeAmount(double lateFeeAmount) { this.lateFeeAmount = lateFeeAmount; }
    public boolean isReceiptGenerated() { return receiptGenerated; }
    public void setReceiptGenerated(boolean receiptGenerated) { this.receiptGenerated = receiptGenerated; }
    public String getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(String receiptNumber) { this.receiptNumber = receiptNumber; }
    public LocalDateTime getReceiptGeneratedAt() { return receiptGeneratedAt; }
    public void setReceiptGeneratedAt(LocalDateTime receiptGeneratedAt) { this.receiptGeneratedAt = receiptGeneratedAt; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Receipt getReceipt() { return receipt; }
    public void setReceipt(Receipt receipt) { this.receipt = receipt; }
}