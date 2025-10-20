package GymManagement.GymManagement.Mappers;

import GymManagement.GymManagement.DTOs.CreateManagerDTO;
import GymManagement.GymManagement.DTOs.ManagerDTO;
import GymManagement.GymManagement.Model.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    @Mappings({@Mapping(target = "id",ignore = true),@Mapping(target = "name",source = "name"),@Mapping(target="email",source = "email")})
    Manager toEntity(CreateManagerDTO dto);
    @Mappings({@Mapping(target = "id",source = "id"),@Mapping(target = "name",source = "name"),@Mapping(target="email",source = "email")})
    ManagerDTO toDTO(Manager manager);

}
