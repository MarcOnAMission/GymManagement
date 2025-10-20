package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateMembershipDTO;
import GymManagement.GymManagement.DTOs.MembershipDTO;
import GymManagement.GymManagement.Mappers.MembershipMapper;
import GymManagement.GymManagement.Model.Membership;
import GymManagement.GymManagement.Repositories.MembershipRepository;
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
class MembershipServiceTest {

    @Mock
    private MembershipRepository membershipRepository;

    @Mock
    private MembershipMapper membershipMapper;

    @InjectMocks
    private MembershipService membershipService;

    private Membership membership;
    private MembershipDTO membershipDTO;
    private CreateMembershipDTO createMembershipDTO;

    @BeforeEach
    void setUp() {
        membership = new Membership(1L, "30-Day Membership", 200.50, List.of());
        membershipDTO = new MembershipDTO(1L, 200.50, "30-Day Membership");
        createMembershipDTO = new CreateMembershipDTO(null, 200.50, "30-Day Membership");
    }

    @Test
    void createMembership_ShouldReturnCreatedMembershipDTO() {
        when(membershipMapper.toEntity(any(CreateMembershipDTO.class))).thenReturn(membership);
        when(membershipMapper.toDTO(any(Membership.class))).thenReturn(membershipDTO);

        MembershipDTO result = membershipService.createMembership(createMembershipDTO);

        assertThat(result).isEqualTo(membershipDTO);
        verify(membershipRepository).save(membership);
    }

    @Test
    void getAll_ShouldReturnAllMembershipsMappedToDTO() {
        when(membershipRepository.findAll()).thenReturn(List.of(membership));
        when(membershipMapper.toDTO(membership)).thenReturn(membershipDTO);

        Iterable<MembershipDTO> result = membershipService.getAll();

        assertThat(result).containsExactly(membershipDTO);
        verify(membershipRepository).findAll();
    }

    @Test
    void getMembershipById_ShouldReturnMembershipDTO_WhenFound() {
        when(membershipRepository.findById(1L)).thenReturn(Optional.of(membership));
        when(membershipMapper.toDTO(membership)).thenReturn(membershipDTO);

        MembershipDTO result = membershipService.getMembershipById(1L);

        assertThat(result).isEqualTo(membershipDTO);
        verify(membershipRepository).findById(1L);
    }

    @Test
    void updateMembership_ShouldUpdateAndReturnMembershipDTO() {
        when(membershipRepository.findById(1L)).thenReturn(Optional.of(membership));
        when(membershipRepository.save(any(Membership.class))).thenReturn(membership);
        when(membershipMapper.toDTO(membership)).thenReturn(membershipDTO);

        MembershipDTO result = membershipService.updateMembership(createMembershipDTO, 1L);

        assertThat(result).isEqualTo(membershipDTO);
        verify(membershipRepository).save(membership);
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        membershipService.deleteById(1L);

        verify(membershipRepository).deleteById(1L);
    }
}
