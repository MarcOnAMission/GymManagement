package GymManagement.GymManagement.Mappers;

import GymManagement.GymManagement.DTOs.CreateGymClassDTO;
import GymManagement.GymManagement.DTOs.GymClassDTO;
import GymManagement.GymManagement.Model.GymClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GymClassMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "date", target = "date"),
            @Mapping(target = "enrolledUsers", ignore = true),
            @Mapping(target = "enrolledTrainer", ignore = true)
    })
    GymClass toEntity(CreateGymClassDTO dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "date", target = "date")
    })
    GymClassDTO toDTO(GymClass gymClass);
}