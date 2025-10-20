package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateUserDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Services.GymClassService;
import GymManagement.GymManagement.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private GymClassService gymClassService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;
    private CreateUserDTO createUserDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(1L, "Tyler Durden");
        createUserDTO = new CreateUserDTO();
        createUserDTO.setName("Tyler Durden");
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        when(userService.createUser(any(CreateUserDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser(createUserDTO);

        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).createUser(createUserDTO);
    }

    @Test
    void getUserById_ShouldReturnUser_WhenExists() {
        when(userService.getUserById(1L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).getUserById(1L);
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        when(userService.updateUser(any(CreateUserDTO.class), eq(1L))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, createUserDTO);

        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).updateUser(createUserDTO, 1L);
    }

    @Test
    void deleteUser_ShouldCallService() {
        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(userService).deleteById(1L);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        when(userService.getALl()).thenReturn(List.of(userDTO));

        ResponseEntity<Iterable<UserDTO>> response = userController.getAllUsers();

        assertThat(response.getBody()).containsExactly(userDTO);
        verify(userService).getALl();
    }

    @Test
    void getByName_ShouldReturnUserByName() {
        when(userService.findByName("Tyler Durden")).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getByName("Tyler Durden");

        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).findByName("Tyler Durden");
    }

    @Test
    void getBySubscription_ShouldReturnUserByStatus() {
        when(userService.findBySubscriptionStatus(true)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getBySubscription(true);

        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).findBySubscriptionStatus(true);
    }

    @Test
    void getAllClassesForUser_ShouldReturnListOfGymClasses() {
        GymClassDTO classDTO = new GymClassDTO(1L, "Powerlifting", LocalDateTime.now());
        when(userService.getAllClassesForUserId(1L)).thenReturn(List.of(classDTO));

        ResponseEntity<List<GymClassDTO>> response = userController.getAllClassesForUser(1L);

        assertThat(response.getBody()).containsExactly(classDTO);
        verify(userService).getAllClassesForUserId(1L);
    }

    @Test
    void enrollUser_ShouldReturnSuccessMessage() {
        when(userService.enrollUser(1L, 2L)).thenReturn("User enrolled successfully.");

        ResponseEntity<String> response = userController.enrollUser(1L, 2L);

        assertThat(response.getBody()).isEqualTo("User enrolled successfully.");
        verify(userService).enrollUser(1L, 2L);
    }

    @Test
    void unenrollUser_ShouldReturnSuccessMessage() {
        when(userService.unenrollUser(1L, 2L)).thenReturn("User unenrolled successfully.");

        ResponseEntity<String> response = userController.unenrollUser(1L, 2L);

        assertThat(response.getBody()).isEqualTo("User unenrolled successfully.");
        verify(userService).unenrollUser(1L, 2L);
    }

    @Test
    void subscribeUserToMembership_ShouldReturnUpdatedUser() {
        when(userService.subscribeUserToMembership(1L, 5L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.subscribeUserToMembership(1L, 5L);

        assertThat(response.getBody()).isEqualTo(userDTO);
        verify(userService).subscribeUserToMembership(1L, 5L);
    }
}
