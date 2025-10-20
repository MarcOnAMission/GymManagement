package GymManagement.GymManagement.Services;

import GymManagement.GymManagement.DTOs.CreateMembershipDTO;
import GymManagement.GymManagement.DTOs.MembershipDTO;
import GymManagement.GymManagement.Model.Membership;
import GymManagement.GymManagement.Repositories.MembershipRepository;
import GymManagement.GymManagement.Mappers.MembershipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {
    @Autowired
    private MembershipRepository memberships;
    @Autowired
    private MembershipMapper mapper;
    //CRUD
    public MembershipDTO createMembership(CreateMembershipDTO dto){
        Membership m = mapper.toEntity(dto);
        memberships.save(m);
        return mapper.toDTO(m);
    }
    public Iterable<MembershipDTO> getAll(){
        return memberships.findAll().stream().map(mapper::toDTO).toList();
    }
    public MembershipDTO getMembershipById(Long id){
        Membership m = memberships.findById(id).orElseThrow(()->new RuntimeException("Membership not found."));
        return mapper.toDTO(m);
    }
    public MembershipDTO updateMembership(CreateMembershipDTO dto, Long id){
        Membership membership1 = memberships.findById(id).orElseThrow(()->new RuntimeException("Membership not found."));
        membership1.setCost(dto.getCost());
        membership1.setName(dto.getName());
        return mapper.toDTO(memberships.save(membership1));
    }
    public void deleteById(Long id){
        memberships.deleteById(id);
    }
}
