package devPilot.backend.repository;
import devPilot.backend.entity.Users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,UUID> {


    Optional<Users> findByGithubId(Long githubId);
    
}