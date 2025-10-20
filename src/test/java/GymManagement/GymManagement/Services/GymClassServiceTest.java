package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateGymClassDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Mappers.GymClassMapper;
import GymManagement.GymManagement.Mappers.UserMapper;
import GymManagement.GymManagement.Model.GymClass;
import GymManagement.GymManagement.Model.User;
import GymManagement.GymManagement.Repositories.GymClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymClassServiceTest {

    @Mock private GymClassRepository gymClassRepository;
    @Mock private GymClassMapper gymClassMapper;
    @Mock private UserMapper userMapper;

    @InjectMocks private GymClassService gymClassService;

    private CreateGymClassDTO createGymClassDTO;
    private GymClass gymClass;
    private GymClassDTO gymClassDTO;

    @BeforeEach
    void setUp() {
        createGymClassDTO = new CreateGymClassDTO();
        createGymClassDTO.setName("Strength Training");
        createGymClassDTO.setDate(LocalDateTime.of(2026, 5, 30, 14, 30));

        gymClass = new GymClass(1L, "Strength Training", createGymClassDTO.getDate(), List.of(), List.of());
        gymClassDTO = new GymClassDTO(1L, "Strength Training", createGymClassDTO.getDate());
    }

    @Test
    void shouldCreateGymClass_whenValidDtoGiven() {
        // given
        when(gymClassMapper.toEntity(createGymClassDTO)).thenReturn(gymClass);
        when(gymClassRepository.save(gymClass)).thenReturn(gymClass);
        when(gymClassMapper.toDTO(gymClass)).thenReturn(gymClassDTO);

        // when
        GymClassDTO result = gymClassService.createClass(createGymClassDTO);

        // then
        assertThat(result).isEqualTo(gymClassDTO);
        verify(gymClassRepository).save(gymClass);
    }

    @Test
    void shouldReturnGymClassDTO_whenClassExists() {
        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));
        when(gymClassMapper.toDTO(gymClass)).thenReturn(gymClassDTO);

        GymClassDTO result = gymClassService.getClassById(1L);

        assertThat(result).isEqualTo(gymClassDTO);
        verify(gymClassRepository).findById(1L);
    }

    @Test
    void shouldUpdateGymClass_whenClassExists() {
        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));
        when(gymClassRepository.save(gymClass)).thenReturn(gymClass);
        when(gymClassMapper.toDTO(gymClass)).thenReturn(gymClassDTO);

        GymClassDTO result = gymClassService.updateClass(createGymClassDTO, 1L);

        assertThat(result).isEqualTo(gymClassDTO);
        verify(gymClassRepository).save(gymClass);
    }

    @Test
    void shouldDeleteGymClassById() {
        gymClassService.deleteById(1L);

        verify(gymClassRepository).deleteById(1L);
    }

    @Test
    void shouldReturnAllUsersForClassId_whenClassExists() {
        User user = new User();
        UserDTO userDTO = new UserDTO();
        gymClass.setEnrolledUsers(List.of(user));

        when(gymClassRepository.findById(1L)).thenReturn(Optional.of(gymClass));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        List<UserDTO> result = gymClassService.getAllUsersForClassId(1L);

        assertThat(result).hasSize(1);
        verify(userMapper).toDTO(user);
    }
}
