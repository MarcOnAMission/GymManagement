package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateManagerDTO;
import GymManagement.GymManagement.DTOs.ManagerDTO;
import GymManagement.GymManagement.Services.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gym_management/managers")
@Tag(name = "Manager Controller", description = "Operations related to managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Operation(summary = "Create a manager", description = "Registers a new gym manager.")
    @PostMapping
    public ResponseEntity<ManagerDTO> createManager(@RequestBody CreateManagerDTO dto) {
        return ResponseEntity.ok(managerService.createManager(dto));
    }

    @Operation(summary = "Get manager by ID", description = "Fetches details of a manager by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ManagerDTO> getManagerById(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.getManagerById(id));
    }

    @Operation(summary = "Update manager", description = "Updates the details of an existing manager.")
    @PutMapping("/{id}")
    public ResponseEntity<ManagerDTO> updateManager(@PathVariable Long id, @RequestBody CreateManagerDTO dto) {
        return ResponseEntity.ok(managerService.updateManager(dto, id));
    }

    @Operation(summary = "Delete manager", description = "Deletes a manager by their ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManagerById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all managers", description = "Retrieves all registered managers.")
    @GetMapping
    public ResponseEntity<Iterable<ManagerDTO>> getAllManagers() {
        return ResponseEntity.ok(managerService.getAll());
    }
}
