package models;

import io.ebean.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SystemSetting extends Model {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String settingKey;

    @Column(nullable = false)
    private String settingValue;

    private String description;
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    private User updatedBy;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSettingKey() { return settingKey; }
    public void setSettingKey(String settingKey) { this.settingKey = settingKey; }
    public String getSettingValue() { return settingValue; }
    public void setSettingValue(String settingValue) { this.settingValue = settingValue; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public User getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(User updatedBy) { this.updatedBy = updatedBy; }
}