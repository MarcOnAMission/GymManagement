package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateGymClassDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Services.GymClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gym_management/classes")
@Tag(name = "Gym Class Controller", description = "Operations related to gym classes")
public class GymClassController {

    @Autowired
    private GymClassService gymClassService;

    @Operation(summary = "Create a gym class", description = "Adds a new class to the gym schedule.")
    @PostMapping
    public ResponseEntity<GymClassDTO> createClass(@RequestBody CreateGymClassDTO dto) {
        return ResponseEntity.ok(gymClassService.createClass(dto));
    }

    @Operation(summary = "Get class by ID", description = "Fetches class details using its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<GymClassDTO> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(gymClassService.getClassById(id));
    }

    @Operation(summary = "Update a class", description = "Updates details of an existing gym class.")
    @PutMapping("/{id}")
    public ResponseEntity<GymClassDTO> updateClass(@PathVariable Long id, @RequestBody CreateGymClassDTO dto) {
        return ResponseEntity.ok(gymClassService.updateClass(dto, id));
    }

    @Operation(summary = "Delete a class", description = "Removes a class from the schedule.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        gymClassService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all classes", description = "Retrieves all gym classes.")
    @GetMapping
    public ResponseEntity<Iterable<GymClassDTO>> getAllClasses() {
        return ResponseEntity.ok(gymClassService.getAll());
    }

    @Operation(summary = "Get all users in a class", description = "Lists all users enrolled in a specific class.")
    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserDTO>> getAllUsersForClass(@PathVariable Long id) {
        return ResponseEntity.ok(gymClassService.getAllUsersForClassId(id));
    }
}
