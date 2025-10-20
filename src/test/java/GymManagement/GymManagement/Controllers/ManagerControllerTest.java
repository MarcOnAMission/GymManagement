package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateManagerDTO;
import GymManagement.GymManagement.DTOs.ManagerDTO;
import GymManagement.GymManagement.Services.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerControllerTest {

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private ManagerController managerController;

    private ManagerDTO managerDTO;
    private CreateManagerDTO createManagerDTO;

    @BeforeEach
    void setUp() {
        managerDTO = new ManagerDTO(1L, "Son Goku", "goku@manager.gym");
        createManagerDTO = new CreateManagerDTO(null, "Son Goku", "goku@manager.gym");
    }

    @Test
    void createManager_ShouldReturnCreatedManager() {
        when(managerService.createManager(any(CreateManagerDTO.class))).thenReturn(managerDTO);

        ResponseEntity<ManagerDTO> response = managerController.createManager(createManagerDTO);

        assertThat(response.getBody()).isEqualTo(managerDTO);
        verify(managerService).createManager(createManagerDTO);
    }

    @Test
    void getManagerById_ShouldReturnManager() {
        when(managerService.getManagerById(1L)).thenReturn(managerDTO);

        ResponseEntity<ManagerDTO> response = managerController.getManagerById(1L);

        assertThat(response.getBody()).isEqualTo(managerDTO);
        verify(managerService).getManagerById(1L);
    }

    @Test
    void updateManager_ShouldReturnUpdatedManager() {
        when(managerService.updateManager(any(CreateManagerDTO.class), eq(1L))).thenReturn(managerDTO);

        ResponseEntity<ManagerDTO> response = managerController.updateManager(1L, createManagerDTO);

        assertThat(response.getBody()).isEqualTo(managerDTO);
        verify(managerService).updateManager(createManagerDTO, 1L);
    }

    @Test
    void deleteManager_ShouldReturnNoContentResponse() {
        ResponseEntity<Void> response = managerController.deleteManager(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(managerService).deleteManagerById(1L);
    }

    @Test
    void getAllManagers_ShouldReturnListOfManagers() {
        when(managerService.getAll()).thenReturn(List.of(managerDTO));

        ResponseEntity<Iterable<ManagerDTO>> response = managerController.getAllManagers();

        assertThat(response.getBody()).containsExactly(managerDTO);
        verify(managerService).getAll();
    }
}
