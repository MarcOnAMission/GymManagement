package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateUserDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.TrainerDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Mappers.GymClassMapper;
import GymManagement.GymManagement.Mappers.UserMapper;
import GymManagement.GymManagement.Model.GymClass;
import GymManagement.GymManagement.Model.Membership;
import GymManagement.GymManagement.Model.User;
import GymManagement.GymManagement.Repositories.GymClassRepository;
import GymManagement.GymManagement.Repositories.MembershipRepository;
import GymManagement.GymManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository users;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private GymClassMapper GymClassMapper;
    @Autowired
    private GymClassRepository GymClassRepo;
    @Autowired
    private GymClassService GymClassService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private MembershipRepository membershipRepository;
    //CRUD
    public UserDTO createUser(CreateUserDTO dto) {
        User u = new User();
        u.setName(dto.getName());
        users.save(u);
        return mapper.toDTO(u);
    }
    public User getUserEntityById(Long id) {
        return users.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public UserDTO getUserById(Long id){
        User u = users.findById(id).orElseThrow(()->new RuntimeException("User not found."));
        return mapper.toDTO(u);
    }
    public UserDTO updateUser(CreateUserDTO dto, Long id){
        User user1 = users.findById(id).orElseThrow(()->new RuntimeException("User not found."));
        user1.setName(dto.getName());
        return mapper.toDTO(users.save(user1));
    }
    public void deleteById(Long id){
        users.deleteById(id);
    }
    //Methodology
    public String enrollUser(Long userId,Long classId){
        User u = getUserEntityById(userId);
        GymClass g =GymClassService.getGymClassEntityById(classId);
        u.getEnrolledClasses().add(g);
        g.getEnrolledUsers().add(u);
        GymClassRepo.save(g);
        users.save(u);
        return "User enrolled successfully.";
    }
    public String unenrollUser(Long userId,Long classId){
        User u = getUserEntityById(userId);
        GymClass g =GymClassService.getGymClassEntityById(classId);
        u.getEnrolledClasses().remove(g);
        g.getEnrolledUsers().remove(u);
        GymClassRepo.save(g);
        users.save(u);
        return "User unenrolled successfully.";
    }
    public UserDTO subscribeUserToMembership(Long userId, Long membershipId) {
        User user = users.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(() -> new RuntimeException("Membership not found."));
        user.setMembership(membership);
        user.setSubscriptionStatus(true);
        membership.getSubscribedUsers().add(user);
        membershipRepository.save(membership);
        users.save(user);
        return mapper.toDTO(user);
    }
    public Iterable<UserDTO> getALl(){
        return users.findAll().stream().map(mapper::toDTO).toList();
    }
    public UserDTO findByName(String name){
        return mapper.toDTO(users.findByName(name).orElseThrow(()->new RuntimeException("User by Name not found.")));
    }
    public UserDTO findBySubscriptionStatus(boolean status){
        return mapper.toDTO(users.findBySubscriptionStatus(status).orElseThrow(()->new RuntimeException("User with subscription status not found")));
    }
    public List<GymClassDTO> getAllClassesForUserId(Long id){
        User u = users.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return u.getEnrolledClasses().stream().map(GymClassMapper::toDTO).toList();
    }
}
