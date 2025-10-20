package GymManagement.GymManagement.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Gym Classes")
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime date;

    @ManyToMany(mappedBy = "enrolledClasses")
    private List<User> enrolledUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "enrolledClasses")
    private List<Trainer> enrolledTrainer = new ArrayList<>();
}
