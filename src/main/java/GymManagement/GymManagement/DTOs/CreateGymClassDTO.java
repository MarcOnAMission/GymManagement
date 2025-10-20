package GymManagement.GymManagement.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "Data required to create or update a gym class")
public class CreateGymClassDTO {
    @Schema(description = "Name of the gym class", example = "Aerobics")
    private String name;

    @Schema(description = "Date when the class takes place", example = "30.05.2026 14:30")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime date;
}
