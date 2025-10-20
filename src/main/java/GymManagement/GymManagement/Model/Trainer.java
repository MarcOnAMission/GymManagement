package GymManagement.GymManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String domainOfActivity;
    @ManyToMany
    @JoinTable(
            name = "enrolled_in",
            joinColumns = @JoinColumn(name = "trainerid"),
            inverseJoinColumns = @JoinColumn(name = "classid")
    )
    private Set<GymClass> enrolledClasses = new HashSet<>();
}
