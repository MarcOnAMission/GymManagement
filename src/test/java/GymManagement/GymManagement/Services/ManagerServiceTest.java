package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateManagerDTO;
import GymManagement.GymManagement.DTOs.ManagerDTO;
import GymManagement.GymManagement.Mappers.ManagerMapper;
import GymManagement.GymManagement.Model.Manager;
import GymManagement.GymManagement.Repositories.ManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private ManagerMapper managerMapper;

    @InjectMocks
    private ManagerService managerService;

    private Manager manager;
    private ManagerDTO managerDTO;
    private CreateManagerDTO createManagerDTO;

    @BeforeEach
    void setUp() {
        manager = new Manager(1L, "Son Goku", "goku@manager.gym");
        managerDTO = new ManagerDTO(1L, "Son Goku", "goku@manager.gym");
        createManagerDTO = new CreateManagerDTO(null, "Son Goku", "goku@manager.gym");
    }

    @Test
    void createManager_ShouldReturnCreatedManagerDTO() {
        when(managerMapper.toEntity(any(CreateManagerDTO.class))).thenReturn(manager);
        when(managerMapper.toDTO(any(Manager.class))).thenReturn(managerDTO);

        ManagerDTO result = managerService.createManager(createManagerDTO);

        assertThat(result).isEqualTo(managerDTO);
        verify(managerRepository).save(manager);
    }

    @Test
    void getManagerById_ShouldReturnManagerDTO_WhenManagerExists() {
        when(managerRepository.findById(1L)).thenReturn(Optional.of(manager));
        when(managerMapper.toDTO(manager)).thenReturn(managerDTO);

        ManagerDTO result = managerService.getManagerById(1L);

        assertThat(result).isEqualTo(managerDTO);
        verify(managerRepository).findById(1L);
    }

    @Test
    void updateManager_ShouldReturnUpdatedManagerDTO() {
        when(managerRepository.findById(1L)).thenReturn(Optional.of(manager));
        when(managerRepository.save(any(Manager.class))).thenReturn(manager);
        when(managerMapper.toDTO(manager)).thenReturn(managerDTO);

        ManagerDTO result = managerService.updateManager(createManagerDTO, 1L);

        assertThat(result).isEqualTo(managerDTO);
        verify(managerRepository).save(manager);
    }

    @Test
    void deleteManagerById_ShouldInvokeRepositoryDelete() {
        managerService.deleteManagerById(1L);

        verify(managerRepository).deleteById(1L);
    }

    @Test
    void getAll_ShouldReturnAllManagersMappedToDTO() {
        when(managerRepository.findAll()).thenReturn(List.of(manager));
        when(managerMapper.toDTO(manager)).thenReturn(managerDTO);

        Iterable<ManagerDTO> result = managerService.getAll();

        assertThat(result).containsExactly(managerDTO);
        verify(managerRepository).findAll();
    }
}
