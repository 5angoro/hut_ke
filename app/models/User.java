package models;



import io.ebean.Finder;
import io.ebean.Model;
import jakarta.persistence.*;
        import java.util.List;

@Entity
@Table (name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Model {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String profileImageUrl;
    private boolean isActive = true;
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

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public Object getPassword() {
        return null;
    }
}