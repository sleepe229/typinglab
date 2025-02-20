package repo;

import org.example.typinglab.entities.Role;
import org.example.typinglab.entities.enums.UserRoles;
import org.example.typinglab.repo.base.BaseRepo;

import java.util.Optional;

public interface UserRoleRepo extends BaseRepo<Role> {
    Optional<Role> findRoleByName(UserRoles role);
}
