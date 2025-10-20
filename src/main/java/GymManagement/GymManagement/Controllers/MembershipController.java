package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateMembershipDTO;
import GymManagement.GymManagement.DTOs.MembershipDTO;
import GymManagement.GymManagement.Services.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gym_management")
@Tag(name = "Membership Controller", description = "Operations related to memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @Operation(summary = "Create a membership", description = "Adds a new membership type to the system.")
    @PostMapping("/memberships")
    public ResponseEntity<MembershipDTO> createMembership(@RequestBody CreateMembershipDTO dto) {
        return ResponseEntity.ok(membershipService.createMembership(dto));
    }

    @Operation(summary = "List all memberships", description = "Retrieves all available membership types.")
    @GetMapping("/memberships")
    public ResponseEntity<Iterable<MembershipDTO>> getAllMemberships() {
        return ResponseEntity.ok(membershipService.getAll());
    }

    @Operation(summary = "Get membership by ID", description = "Fetches details of a specific membership by its ID.")
    @GetMapping("/memberships/{id}")
    public ResponseEntity<MembershipDTO> getMembershipById(@PathVariable Long id) {
        return ResponseEntity.ok(membershipService.getMembershipById(id));
    }

    @Operation(summary = "Update membership", description = "Modifies an existing membership’s details.")
    @PutMapping("/memberships/{id}")
    public ResponseEntity<MembershipDTO> updateMembership(@PathVariable Long id, @RequestBody CreateMembershipDTO dto) {
        return ResponseEntity.ok(membershipService.updateMembership(dto, id));
    }

    @Operation(summary = "Delete membership", description = "Deletes a membership type by ID.")
    @DeleteMapping("/memberships/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
