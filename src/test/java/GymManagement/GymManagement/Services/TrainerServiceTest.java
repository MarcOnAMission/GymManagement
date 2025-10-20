package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateTrainerDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.TrainerDTO;
import GymManagement.GymManagement.Mappers.GymClassMapper;
import GymManagement.GymManagement.Mappers.TrainerMapper;
import GymManagement.GymManagement.Model.GymClass;
import GymManagement.GymManagement.Model.Trainer;
import GymManagement.GymManagement.Repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerMapper trainerMapper;

    @Mock
    private GymClassMapper gymClassMapper;

    @InjectMocks
    private TrainerService trainerService;

    private Trainer trainer;
    private TrainerDTO trainerDTO;
    private CreateTrainerDTO createTrainerDTO;

    @BeforeEach
    void setUp() {
        trainer = new Trainer(1L, "Muten Roshi", "Calisthenics", Set.of());
        trainerDTO = new TrainerDTO(1L, "Muten Roshi", "Calisthenics");
        createTrainerDTO = new CreateTrainerDTO(null, "Muten Roshi", "Calisthenics");
    }

    @Test
    void createTrainer_ShouldReturnCreatedTrainerDTO() {
        when(trainerMapper.toEntity(any(CreateTrainerDTO.class))).thenReturn(trainer);
        when(trainerMapper.toDTO(any(Trainer.class))).thenReturn(trainerDTO);

        TrainerDTO result = trainerService.createTrainer(createTrainerDTO);

        assertThat(result).isEqualTo(trainerDTO);
        verify(trainerRepository).save(trainer);
    }

    @Test
    void getTrainerById_ShouldReturnTrainerDTO_WhenTrainerExists() {
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));
        when(trainerMapper.toDTO(trainer)).thenReturn(trainerDTO);

        TrainerDTO result = trainerService.getTrainerById(1L);

        assertThat(result).isEqualTo(trainerDTO);
        verify(trainerRepository).findById(1L);
    }

    @Test
    void updateTrainer_ShouldUpdateAndReturnTrainerDTO() {
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));
        when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);
        when(trainerMapper.toDTO(any(Trainer.class))).thenReturn(trainerDTO);

        TrainerDTO result = trainerService.updateTrainer(createTrainerDTO, 1L);

        assertThat(result).isEqualTo(trainerDTO);
        verify(trainerRepository).save(trainer);
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        trainerService.deleteById(1L);

        verify(trainerRepository).deleteById(1L);
    }

    @Test
    void getAll_ShouldReturnAllTrainersMappedToDTO() {
        when(trainerRepository.findAll()).thenReturn(List.of(trainer));
        when(trainerMapper.toDTO(trainer)).thenReturn(trainerDTO);

        Iterable<TrainerDTO> result = trainerService.getALl();

        assertThat(result).containsExactly(trainerDTO);
    }

    @Test
    void getAllClassesForTrainerId_ShouldReturnMappedGymClasses() {
        GymClass gymClass = new GymClass();
        gymClass.setId(1L);
        gymClass.setName("Yoga Basics");

        trainer.setEnrolledClasses(Set.of(gymClass));
        GymClassDTO gymClassDTO = new GymClassDTO(1L, "Yoga Basics", null);

        when(trainerRepository.findById(1L)).thenReturn(Optional.of(trainer));
        when(gymClassMapper.toDTO(gymClass)).thenReturn(gymClassDTO);

        List<GymClassDTO> result = trainerService.getAllClassesForTrainerId(1L);

        assertThat(result).containsExactly(gymClassDTO);
        verify(trainerRepository).findById(1L);
    }
}
