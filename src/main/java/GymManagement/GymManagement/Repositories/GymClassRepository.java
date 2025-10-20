package GymManagement.GymManagement.Repositories;

import GymManagement.GymManagement.Model.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GymClassRepository extends JpaRepository<GymClass,Long> {
    List<GymClass> findAllByDate(LocalDateTime dateTime);
}
