package GymManagement.GymManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean subscriptionStatus=false;

    @ManyToMany
    @JoinTable(
            name = "enrolled_in",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "classid")
    )
    private Set<GymClass> enrolledClasses = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;
}
