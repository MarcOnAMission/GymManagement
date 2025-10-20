package GymManagement.GymManagement.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data transfer object for the manager")
public class CreateManagerDTO {
    @Schema(description = "Unique identifier of the manager", example = "1")
    private Long id;
    @Schema(description = "Full name of the manager",example = "Son Goku")
    private String name;
    @Schema(description = "Email of the manager",example = "myemail@manager.gym")
    private String email;
}