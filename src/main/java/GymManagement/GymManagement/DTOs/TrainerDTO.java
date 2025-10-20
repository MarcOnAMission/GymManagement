package GymManagement.GymManagement.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Trainer data transfer object representing a gym trainer")
public class TrainerDTO {
    @Schema(description = "Unique identifier of the trainer", example = "1")
    private Long id;
    @Schema(description = "Full name of the trainer",example = "Muten Roshi")
    private String name;
    @Schema(description = "Domain of activity for the trainer",example = "Calisthenics")
    private String domainOfActivity;

}
