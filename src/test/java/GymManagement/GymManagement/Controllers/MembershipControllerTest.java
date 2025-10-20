package GymManagement.GymManagement.Controllers;

import GymManagement.GymManagement.DTOs.CreateMembershipDTO;
import GymManagement.GymManagement.DTOs.MembershipDTO;
import GymManagement.GymManagement.Services.MembershipService;
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
class MembershipControllerTest {

    @Mock
    private MembershipService membershipService;

    @InjectMocks
    private MembershipController membershipController;

    private MembershipDTO membershipDTO;
    private CreateMembershipDTO createMembershipDTO;

    @BeforeEach
    void setUp() {
        membershipDTO = new MembershipDTO(1L, 200.50, "30-Day Membership");
        createMembershipDTO = new CreateMembershipDTO(null, 200.50, "30-Day Membership");
    }

    @Test
    void createMembership_ShouldReturnCreatedMembership() {
        when(membershipService.createMembership(any(CreateMembershipDTO.class))).thenReturn(membershipDTO);

        ResponseEntity<MembershipDTO> response = membershipController.createMembership(createMembershipDTO);

        assertThat(response.getBody()).isEqualTo(membershipDTO);
        verify(membershipService).createMembership(createMembershipDTO);
    }

    @Test
    void getAllMemberships_ShouldReturnListOfMemberships() {
        when(membershipService.getAll()).thenReturn(List.of(membershipDTO));

        ResponseEntity<Iterable<MembershipDTO>> response = membershipController.getAllMemberships();

        assertThat(response.getBody()).containsExactly(membershipDTO);
        verify(membershipService).getAll();
    }

    @Test
    void getMembershipById_ShouldReturnMembership() {
        when(membershipService.getMembershipById(1L)).thenReturn(membershipDTO);

        ResponseEntity<MembershipDTO> response = membershipController.getMembershipById(1L);

        assertThat(response.getBody()).isEqualTo(membershipDTO);
        verify(membershipService).getMembershipById(1L);
    }

    @Test
    void updateMembership_ShouldReturnUpdatedMembership() {
        when(membershipService.updateMembership(any(CreateMembershipDTO.class), eq(1L))).thenReturn(membershipDTO);

        ResponseEntity<MembershipDTO> response = membershipController.updateMembership(1L, createMembershipDTO);

        assertThat(response.getBody()).isEqualTo(membershipDTO);
        verify(membershipService).updateMembership(createMembershipDTO, 1L);
    }

    @Test
    void deleteMembership_ShouldReturnNoContentResponse() {
        ResponseEntity<Void> response = membershipController.deleteMembership(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(membershipService).deleteById(1L);
    }
}
