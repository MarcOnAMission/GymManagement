package GymManagement.GymManagement.Mappers;


import GymManagement.GymManagement.DTOs.CreateMembershipDTO;
import GymManagement.GymManagement.DTOs.MembershipDTO;
import GymManagement.GymManagement.Model.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MembershipMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "cost", source = "cost")
    @Mapping(target = "subscribedUsers",ignore = true)
    Membership toEntity(CreateMembershipDTO dto);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "cost", source = "cost")
    MembershipDTO toDTO(Membership membership);
}
