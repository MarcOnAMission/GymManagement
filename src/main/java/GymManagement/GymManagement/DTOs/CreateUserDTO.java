package GymManagement.GymManagement.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data required to create or update a user")
public class CreateUserDTO {
    @Schema(description = "Full name of the user", example = "Tyler Durden")
    private String name;
}
