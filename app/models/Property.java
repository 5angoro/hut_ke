package models;


import io.ebean.Model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name="properties")
public class Property extends Model {
    @Id
    private String id;

    @ManyToOne
    private PropertyManager manager;

    @ManyToOne
    private PropertyType type;

    private String propertyName;
    private String description;
    private String address;
    private String city;
    private String county;
    private String postalCode;
    private Double latitude;
    private Double longitude;
    private int totalUnits;
    private int availableUnits;
    private String amenities; // JSON
    private String images; // JSON
    private boolean isActive = true;

    @OneToMany(mappedBy = "property")
    private List<Unit> units;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public PropertyManager getManager() { return manager; }
    public void setManager(PropertyManager manager) { this.manager = manager; }
    public PropertyType getType() { return type; }
    public void setType(PropertyType type) { this.type = type; }
    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCounty() { return county; }
    public void setCounty(String county) { this.county = county; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public int getTotalUnits() { return totalUnits; }
    public void setTotalUnits(int totalUnits) { this.totalUnits = totalUnits; }
    public int getAvailableUnits() { return availableUnits; }
    public void setAvailableUnits(int availableUnits) { this.availableUnits = availableUnits; }
    public String getAmenities() { return amenities; }
    public void setAmenities(String amenities) { this.amenities = amenities; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public List<Unit> getUnits() { return units; }
    public void setUnits(List<Unit> units) { this.units = units; }
}