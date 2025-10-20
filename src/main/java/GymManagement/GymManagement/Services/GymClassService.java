package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateGymClassDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Mappers.UserMapper;
import GymManagement.GymManagement.Model.GymClass;
import GymManagement.GymManagement.Model.User;
import GymManagement.GymManagement.Repositories.GymClassRepository;
import GymManagement.GymManagement.Mappers.GymClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymClassService {
    @Autowired
    private GymClassRepository classes;
    @Autowired
    private GymClassMapper mapper;
    @Autowired
    private UserMapper UserMapper;
    //CRUD
    public GymClassDTO createClass(CreateGymClassDTO dto){
        GymClass gymClass = mapper.toEntity(dto);
        classes.save(gymClass);
        return mapper.toDTO(gymClass);
    }
    public GymClass getGymClassEntityById(Long id) {
        return classes.findById(id)
                .orElseThrow(() -> new RuntimeException("GymClass not found"));
    }
    public GymClassDTO getClassById(Long id){
        GymClass class1 = classes.findById(id).orElseThrow(()->new RuntimeException("Class not found"));
        return mapper.toDTO(class1);
    }
    public GymClassDTO updateClass(CreateGymClassDTO dto,long id){
        GymClass gymClass1 = classes.findById(id).orElseThrow(()->new RuntimeException("Class not found"));
        gymClass1.setDate(dto.getDate());
        gymClass1.setName(dto.getName());
        return mapper.toDTO(classes.save(gymClass1));
    }
    public void deleteById(Long id){
        classes.deleteById(id);
    }
    //Methodology
    public Iterable<GymClassDTO> getAll(){
        return classes.findAll().stream().map(mapper::toDTO).toList();
    }
    public List<UserDTO> getAllUsersForClassId(Long id){
        GymClass g = classes.findById(id).orElseThrow(()->new RuntimeException("Class not found"));
        return g.getEnrolledUsers().stream().map(UserMapper::toDTO).toList();
    }
}
