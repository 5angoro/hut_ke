package models;




import io.ebean.Model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name="payment_reminders")
public class PaymentReminder extends Model {
    @Id
    private Long id;

    @ManyToOne
    private Lease lease;

    @ManyToOne
    private PropertyManager sentBy;

    private LocalDate reminderDate;
    private String reminderType; // enum would be better
    private String message;
    private LocalDateTime sentAt = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Lease getLease() { return lease; }
    public void setLease(Lease lease) { this.lease = lease; }
    public PropertyManager getSentBy() { return sentBy; }
    public void setSentBy(PropertyManager sentBy) { this.sentBy = sentBy; }
    public LocalDate getReminderDate() { return reminderDate; }
    public void setReminderDate(LocalDate reminderDate) { this.reminderDate = reminderDate; }
    public String getReminderType() { return reminderType; }
    public void setReminderType(String reminderType) { this.reminderType = reminderType; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
