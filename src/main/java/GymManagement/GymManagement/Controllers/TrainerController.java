package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateTrainerDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.TrainerDTO;
import GymManagement.GymManagement.Services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gym_management")
@Tag(name = "Trainer Controller", description = "Operations related to trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Operation(summary = "Create a trainer", description = "Registers a new trainer in the system.")
    @PostMapping("/trainers")
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody CreateTrainerDTO dto) {
        return ResponseEntity.ok(trainerService.createTrainer(dto));
    }

    @Operation(summary = "Get trainer by ID", description = "Fetches trainer details using their unique ID.")
    @GetMapping("/trainers/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @Operation(summary = "Update a trainer", description = "Updates an existing trainer's information.")
    @PutMapping("/trainers/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Long id, @RequestBody CreateTrainerDTO dto) {
        return ResponseEntity.ok(trainerService.updateTrainer(dto, id));
    }

    @Operation(summary = "Delete a trainer", description = "Removes a trainer from the system by their ID.")
    @DeleteMapping("/trainers/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all trainers", description = "Retrieves all registered trainers.")
    @GetMapping("/trainers")
    public ResponseEntity<Iterable<TrainerDTO>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getALl());
    }

    @Operation(summary = "Get all classes for trainer", description = "Lists all gym classes managed by a specific trainer.")
    @GetMapping("/trainers/{id}/classes")
    public ResponseEntity<List<GymClassDTO>> getAllClassesForTrainer(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getAllClassesForTrainerId(id));
    }
}
