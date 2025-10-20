package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateGymClassDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Services.GymClassService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymClassControllerTest {

    @Mock private GymClassService gymClassService;
    @InjectMocks private GymClassController gymClassController;

    private CreateGymClassDTO createGymClassDTO;
    private GymClassDTO gymClassDTO;

    @BeforeEach
    void setUp() {
        createGymClassDTO = new CreateGymClassDTO();
        createGymClassDTO.setName("Cardio Blast");
        createGymClassDTO.setDate(LocalDateTime.of(2026, 5, 30, 14, 30));

        gymClassDTO = new GymClassDTO(1L, "Cardio Blast", createGymClassDTO.getDate());
    }

    @Test
    void shouldCreateGymClass() {
        when(gymClassService.createClass(createGymClassDTO)).thenReturn(gymClassDTO);

        ResponseEntity<GymClassDTO> response = gymClassController.createClass(createGymClassDTO);

        assertThat(response.getBody()).isEqualTo(gymClassDTO);
        verify(gymClassService).createClass(createGymClassDTO);
    }

    @Test
    void shouldReturnGymClassById() {
        when(gymClassService.getClassById(1L)).thenReturn(gymClassDTO);

        ResponseEntity<GymClassDTO> response = gymClassController.getClassById(1L);

        assertThat(response.getBody()).isEqualTo(gymClassDTO);
        verify(gymClassService).getClassById(1L);
    }

    @Test
    void shouldUpdateGymClass() {
        when(gymClassService.updateClass(createGymClassDTO, 1L)).thenReturn(gymClassDTO);

        ResponseEntity<GymClassDTO> response = gymClassController.updateClass(1L, createGymClassDTO);

        assertThat(response.getBody()).isEqualTo(gymClassDTO);
        verify(gymClassService).updateClass(createGymClassDTO, 1L);
    }

    @Test
    void shouldDeleteGymClass() {
        ResponseEntity<Void> response = gymClassController.deleteClass(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(gymClassService).deleteById(1L);
    }

    @Test
    void shouldReturnAllGymClasses() {
        when(gymClassService.getAll()).thenReturn(List.of(gymClassDTO));

        ResponseEntity<Iterable<GymClassDTO>> response = gymClassController.getAllClasses();

        assertThat(response.getBody()).containsExactly(gymClassDTO);
        verify(gymClassService).getAll();
    }

    @Test
    void shouldReturnAllUsersForClass() {
        UserDTO userDTO = new UserDTO();
        when(gymClassService.getAllUsersForClassId(1L)).thenReturn(List.of(userDTO));

        ResponseEntity<List<UserDTO>> response = gymClassController.getAllUsersForClass(1L);

        assertThat(response.getBody()).containsExactly(userDTO);
        verify(gymClassService).getAllUsersForClassId(1L);
    }
}
