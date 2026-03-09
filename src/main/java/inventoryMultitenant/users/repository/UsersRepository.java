package inventoryMultitenant.users.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.users.model.UsersModel;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, String> {

    @Query("SELECT u FROM UsersModel u WHERE u.username = :username")
    Optional<UsersModel> findByUsername(String username);

    @Query("""
            SELECT u FROM UsersModel u
            JOIN FETCH u.role r
            JOIN FETCH r.rolesPermisos rp
            JOIN FETCH rp.permiso
            WHERE u.username = :username
            """)
    Optional<UsersModel> findUserWithPermisos(@Param("username") String username);

    Page<UsersModel> findAll(Pageable pageable);

}
