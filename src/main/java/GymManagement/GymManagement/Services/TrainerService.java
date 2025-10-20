package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateTrainerDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.DTOs.TrainerDTO;
import GymManagement.GymManagement.Mappers.GymClassMapper;
import GymManagement.GymManagement.Model.Trainer;
import GymManagement.GymManagement.Repositories.TrainerRepository;
import GymManagement.GymManagement.Mappers.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainers;
    @Autowired
    private TrainerMapper mapper;
    @Autowired
    private GymClassMapper GymClassMapper;
    //CRUD
    public TrainerDTO createTrainer(CreateTrainerDTO dto){
        Trainer t = mapper.toEntity(dto);
        trainers.save(t);
        return mapper.toDTO(t);
    }
    public TrainerDTO getTrainerById(Long id){
        Trainer t = trainers.findById(id).orElseThrow(()->new RuntimeException("Trainer not found."));
        return mapper.toDTO(t);
    }
    public TrainerDTO updateTrainer(CreateTrainerDTO dto, Long id){
        Trainer trainer1 = trainers.findById(id).orElseThrow(()->new RuntimeException("Trainer not found."));
        String name = dto.getName();
        trainer1.setName(name);
        trainer1.setDomainOfActivity(dto.getDomainOfActivity());
        return mapper.toDTO(trainers.save(trainer1));
    }
    public void deleteById(Long id){
        trainers.deleteById(id);
    }
    //Methodology
    public Iterable<TrainerDTO> getALl(){
        return trainers.findAll().stream().map(mapper::toDTO).toList();
    }
    public List<GymClassDTO> getAllClassesForTrainerId(Long id){
        Trainer t = trainers.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return t.getEnrolledClasses().stream().map(GymClassMapper::toDTO).toList();
    }
}
