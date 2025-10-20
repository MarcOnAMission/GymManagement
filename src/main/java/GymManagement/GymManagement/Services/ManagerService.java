package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateManagerDTO;
import GymManagement.GymManagement.DTOs.ManagerDTO;
import GymManagement.GymManagement.Model.Manager;
import GymManagement.GymManagement.Repositories.ManagerRepository;
import GymManagement.GymManagement.Mappers.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managers;
    @Autowired
    private ManagerMapper mapper;
    //CRUD
    public ManagerDTO createManager(CreateManagerDTO dto){
        Manager m = mapper.toEntity(dto);
        managers.save(m);
        return mapper.toDTO(m);
    }

    public ManagerDTO getManagerById(Long id){
        Manager m = managers.findById(id).orElseThrow(()->new RuntimeException("Manager not found."));
        return mapper.toDTO(m);
    }
    public ManagerDTO updateManager(CreateManagerDTO dto, Long id){
        Manager manager1 = managers.findById(id).orElseThrow(()->new RuntimeException("Manager not found."));
        manager1.setEmail(dto.getEmail());
        manager1.setName(dto.getName());
        return mapper.toDTO(managers.save(manager1));
    }
    public void deleteManagerById(Long id){
        managers.deleteById(id);
    }
    //Methodology
    public Iterable<ManagerDTO> getAll(){
        return managers.findAll().stream().map(mapper::toDTO).toList();
    }
}
