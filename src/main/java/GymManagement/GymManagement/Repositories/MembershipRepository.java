package GymManagement.GymManagement.Repositories;

import GymManagement.GymManagement.Model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership,Long> {
}
