package GymManagement.GymManagement.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data transfer object for a gym class")
public class GymClassDTO {
    @Schema(description = "Unique identifier of the gym class", example = "1")
    private Long id;
    @Schema(description = "Name of the gym class",example = "Aerobics")
    private String name;
    @Schema(description = "Date when the class takes place",example = "30.05.2026 2:30")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime date;
}
