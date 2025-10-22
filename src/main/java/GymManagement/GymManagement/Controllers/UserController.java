package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateUserDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Model.User;
import GymManagement.GymManagement.Services.GymClassService;
import GymManagement.GymManagement.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gym_management")
@Tag(name = "User Controller", description = "Operations related to gym users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GymClassService gymClassService;

    @Operation(summary = "Create a new user", description = "Registers a new gym user in the system.")
    @ApiResponse(responseCode = "200", description = "User successfully created")
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @Operation(summary = "Get user by ID", description = "Fetch user details using their unique ID.")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@Parameter(description = "User ID") @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Update a user", description = "Updates the user’s details by ID.")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody CreateUserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(dto, id));
    }

    @Operation(summary = "Delete a user", description = "Deletes a user account by ID.")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all users", description = "Fetches a list of all registered users.")
    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getALl());
    }

    @Operation(summary = "Get user by name", description = "Finds a user by their full name.")
    @GetMapping("/users/by-name")
    public ResponseEntity<UserDTO> getByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @Operation(summary = "Get user by subscription status", description = "Finds users by their subscription status.")
    @GetMapping("/users/by-subscription")
    public ResponseEntity<List<UserDTO>> getBySubscription(@RequestParam boolean status) {
        return ResponseEntity.ok(userService.findBySubscriptionStatus(status));
    }

    @Operation(summary = "Get all classes for user", description = "Fetches all classes a user is enrolled in.")
    @GetMapping("/users/{id}/classes")
    public ResponseEntity<List<GymClassDTO>> getAllClassesForUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAllClassesForUserId(id));
    }

    @Operation(summary = "Enroll user into class", description = "Enrolls a user into a specific gym class.")
    @PostMapping("/users/{userId}/class/{classId}")
    public ResponseEntity<String> enrollUser(@PathVariable Long userId, @PathVariable Long classId) {
        return ResponseEntity.ok(userService.enrollUser(userId, classId));
    }

    @Operation(summary = "Unenroll user from class", description = "Removes a user from a class they are enrolled in.")
    @DeleteMapping("/{userId}/unenroll/{classId}")
    public ResponseEntity<String> unenrollUser(@PathVariable Long userId, @PathVariable Long classId) {
        return ResponseEntity.ok(userService.unenrollUser(userId, classId));
    }

    @Operation(summary = "Subscribe user to membership", description = "Subscribes a user to a gym membership plan.")
    @PostMapping("/users/{userId}/subscribe/{membershipId}")
    public ResponseEntity<UserDTO> subscribeUserToMembership(@PathVariable Long userId, @PathVariable Long membershipId) {
        return ResponseEntity.ok(userService.subscribeUserToMembership(userId, membershipId));
    }
}
