package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateUserDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Mappers.GymClassMapper;
import GymManagement.GymManagement.Mappers.UserMapper;
import GymManagement.GymManagement.Model.GymClass;
import GymManagement.GymManagement.Model.Membership;
import GymManagement.GymManagement.Model.User;
import GymManagement.GymManagement.Repositories.GymClassRepository;
import GymManagement.GymManagement.Repositories.MembershipRepository;
import GymManagement.GymManagement.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private UserMapper userMapper;
    @Mock private GymClassMapper gymClassMapper;
    @Mock private GymClassRepository gymClassRepository;
    @Mock private GymClassService gymClassService;
    @Mock private MembershipRepository membershipRepository;
    @Mock private MembershipService membershipService;

    @InjectMocks private UserService userService;

    private User user;
    private CreateUserDTO createUserDTO;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Tyler Durden", false, new HashSet<>(), null);
        createUserDTO = new CreateUserDTO();
        createUserDTO.setName("Tyler Durden");

        userDTO = new UserDTO(1L, "Tyler Durden");
    }

    @Test
    void createUser_ShouldSaveAndReturnUserDTO() {
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);

        UserDTO result = userService.createUser(createUserDTO);

        assertNotNull(result);
        assertEquals("Tyler Durden", result.getName());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getUserById_ShouldReturnMappedDTO_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1L);

        assertEquals("Tyler Durden", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void updateUser_ShouldUpdateNameAndReturnDTO() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(createUserDTO, 1L);

        assertEquals("Tyler Durden", result.getName());
        verify(userRepository).save(user);
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        userService.deleteById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void enrollUser_ShouldAddUserToClass() {
        GymClass gymClass = new GymClass();
        gymClass.setEnrolledUsers(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(gymClassService.getGymClassEntityById(2L)).thenReturn(gymClass);

        String result = userService.enrollUser(1L, 2L);

        assertEquals("User enrolled successfully.", result);
        verify(gymClassRepository).save(gymClass);
        verify(userRepository).save(user);
    }

    @Test
    void unenrollUser_ShouldRemoveUserFromClass() {
        GymClass gymClass = new GymClass();
        gymClass.setEnrolledUsers(new ArrayList<>());
        gymClass.getEnrolledUsers().add(user);
        user.getEnrolledClasses().add(gymClass);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(gymClassService.getGymClassEntityById(2L)).thenReturn(gymClass);

        String result = userService.unenrollUser(1L, 2L);

        assertEquals("User unenrolled successfully.", result);
        verify(gymClassRepository).save(gymClass);
        verify(userRepository).save(user);
    }

    @Test
    void subscribeUserToMembership_ShouldSetSubscriptionAndSave() {
        Membership membership = new Membership();
        membership.setSubscribedUsers(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(membershipRepository.findById(5L)).thenReturn(Optional.of(membership));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.subscribeUserToMembership(1L, 5L);

        assertTrue(user.isSubscriptionStatus());
        assertEquals("Tyler Durden", result.getName());
        verify(userRepository).save(user);
        verify(membershipRepository).save(membership);
    }

    @Test
    void findByName_ShouldReturnMappedDTO() {
        when(userRepository.findByName("Tyler Durden")).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.findByName("Tyler Durden");

        assertEquals("Tyler Durden", result.getName());
        verify(userRepository).findByName("Tyler Durden");
    }

}
