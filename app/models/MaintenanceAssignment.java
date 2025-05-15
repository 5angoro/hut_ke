package models;


import io.ebean.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table   (name="maintenance_assignments")
public class MaintenanceAssignment extends Model {
    @Id
    private Long id;

    @ManyToOne
    private MaintenanceRequest request;

    private Long assignedTo; // Could be staff ID or vendor ID
    private String assignedToName;

    @ManyToOne
    private PropertyManager assignedBy;

    private LocalDateTime assignedAt = LocalDateTime.now();
    private LocalDateTime estimatedCompletionDate;
    private LocalDateTime actualCompletionDate;
    private Double costEstimate;
    private Double actualCost;
    private String notes;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public MaintenanceRequest getRequest() { return request; }
    public void setRequest(MaintenanceRequest request) { this.request = request; }
    public Long getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }
    public String getAssignedToName() { return assignedToName; }
    public void setAssignedToName(String assignedToName) { this.assignedToName = assignedToName; }
    public PropertyManager getAssignedBy() { return assignedBy; }
    public void setAssignedBy(PropertyManager assignedBy) { this.assignedBy = assignedBy; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
    public LocalDateTime getEstimatedCompletionDate() { return estimatedCompletionDate; }
    public void setEstimatedCompletionDate(LocalDateTime estimatedCompletionDate) { this.estimatedCompletionDate = estimatedCompletionDate; }
    public LocalDateTime getActualCompletionDate() { return actualCompletionDate; }
    public void setActualCompletionDate(LocalDateTime actualCompletionDate) { this.actualCompletionDate = actualCompletionDate; }
    public Double getCostEstimate() { return costEstimate; }
    public void setCostEstimate(Double costEstimate) { this.costEstimate = costEstimate; }
    public Double getActualCost() { return actualCost; }
    public void setActualCost(Double actualCost) { this.actualCost = actualCost; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}