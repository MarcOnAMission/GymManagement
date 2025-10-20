package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateTrainerDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.TrainerDTO;
import GymManagement.GymManagement.Services.TrainerService;
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
class TrainerControllerTest {

    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    private TrainerDTO trainerDTO;
    private CreateTrainerDTO createTrainerDTO;

    @BeforeEach
    void setUp() {
        trainerDTO = new TrainerDTO(1L, "Muten Roshi", "Calisthenics");
        createTrainerDTO = new CreateTrainerDTO(null, "Muten Roshi", "Calisthenics");
    }

    @Test
    void createTrainer_ShouldReturnCreatedTrainer() {
        when(trainerService.createTrainer(any(CreateTrainerDTO.class))).thenReturn(trainerDTO);

        ResponseEntity<TrainerDTO> response = trainerController.createTrainer(createTrainerDTO);

        assertThat(response.getBody()).isEqualTo(trainerDTO);
        verify(trainerService).createTrainer(createTrainerDTO);
    }

    @Test
    void getTrainerById_ShouldReturnTrainer_WhenExists() {
        when(trainerService.getTrainerById(1L)).thenReturn(trainerDTO);

        ResponseEntity<TrainerDTO> response = trainerController.getTrainerById(1L);

        assertThat(response.getBody()).isEqualTo(trainerDTO);
        verify(trainerService).getTrainerById(1L);
    }

    @Test
    void updateTrainer_ShouldReturnUpdatedTrainer() {
        when(trainerService.updateTrainer(any(CreateTrainerDTO.class), eq(1L))).thenReturn(trainerDTO);

        ResponseEntity<TrainerDTO> response = trainerController.updateTrainer(1L, createTrainerDTO);

        assertThat(response.getBody()).isEqualTo(trainerDTO);
        verify(trainerService).updateTrainer(createTrainerDTO, 1L);
    }

    @Test
    void deleteTrainer_ShouldCallService() {
        ResponseEntity<Void> response = trainerController.deleteTrainer(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(trainerService).deleteById(1L);
    }

    @Test
    void getAllTrainers_ShouldReturnListOfTrainers() {
        when(trainerService.getALl()).thenReturn(List.of(trainerDTO));

        ResponseEntity<Iterable<TrainerDTO>> response = trainerController.getAllTrainers();

        assertThat(response.getBody()).containsExactly(trainerDTO);
        verify(trainerService).getALl();
    }

    @Test
    void getAllClassesForTrainer_ShouldReturnListOfGymClasses() {
        GymClassDTO classDTO = new GymClassDTO(1L, "Powerlifting", LocalDateTime.now());
        when(trainerService.getAllClassesForTrainerId(1L)).thenReturn(List.of(classDTO));

        ResponseEntity<List<GymClassDTO>> response = trainerController.getAllClassesForTrainer(1L);

        assertThat(response.getBody()).containsExactly(classDTO);
        verify(trainerService).getAllClassesForTrainerId(1L);
    }
}
