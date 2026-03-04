package inventoryMultitenant.users.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.users.model.UsersModel;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, String> {

    Optional<UsersModel> findByUsername(String username);

}
