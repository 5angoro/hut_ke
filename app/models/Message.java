package models;


import io.ebean.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name="messagas")
public class Message extends Model {
    @Id
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private String subject;
    private String body;
    private boolean isRead = false;
    private LocalDateTime sentAt = LocalDateTime.now();
    private LocalDateTime readAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    public LocalDateTime getReadAt() { return readAt; }
    public void setReadAt(LocalDateTime readAt) { this.readAt = readAt; }
}