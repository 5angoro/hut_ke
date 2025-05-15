package models;




import io.ebean.Model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name="property_types")
public class PropertyType extends Model {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String typeName;

    private String description;

    @OneToMany(mappedBy = "type")
    private List<Property> properties;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Property> getProperties() { return properties; }
    public void setProperties(List<Property> properties) { this.properties = properties; }
}