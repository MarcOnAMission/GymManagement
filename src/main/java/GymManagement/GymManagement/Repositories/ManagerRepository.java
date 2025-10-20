package GymManagement.GymManagement.Repositories;

import GymManagement.GymManagement.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
