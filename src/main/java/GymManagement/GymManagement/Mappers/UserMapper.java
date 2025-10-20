package GymManagement.GymManagement.Mappers;

import GymManagement.GymManagement.DTOs.CreateUserDTO;
import GymManagement.GymManagement.DTOs.UserDTO;
import GymManagement.GymManagement.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    UserDTO toDTO(User user);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUserDTO dto);
}
