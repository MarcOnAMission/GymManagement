package GymManagement.GymManagement.Mappers;


import GymManagement.GymManagement.DTOs.CreateTrainerDTO;
import GymManagement.GymManagement.DTOs.TrainerDTO;
import GymManagement.GymManagement.Model.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "domainOfActivity", target = "domainOfActivity")
    })
    TrainerDTO toDTO(Trainer trainer);

    @Mappings({@Mapping(target = "id", ignore = true), @Mapping(source = "name", target = "name"), @Mapping(source = "domainOfActivity", target = "domainOfActivity"), @Mapping(target = "enrolledClasses", ignore = true)
    })
    Trainer toEntity(CreateTrainerDTO dto);
}
