package GymManagement.GymManagement.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for a Membership")
public class CreateMembershipDTO {
    @Schema(description = "Unique identifier of the membership", example = "1")
    private Long id;
    @Schema(description = "How much a type of membership costs",example = "200.50")
    private double cost;
    @Schema(description = "The name of the subscription",example = "30-Day Membership")
    private String name;
}
