package GymManagement.GymManagement.Repositories;

import GymManagement.GymManagement.Model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
}
