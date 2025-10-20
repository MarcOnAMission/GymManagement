package GymManagement.GymManagement.Repositories;

import GymManagement.GymManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByName(String name);
    public Optional<User> findBySubscriptionStatus(boolean status);
}
