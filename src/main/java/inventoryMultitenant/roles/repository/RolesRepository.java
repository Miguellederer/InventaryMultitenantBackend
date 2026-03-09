package inventoryMultitenant.roles.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.roles.model.RolesModel;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, String> {

    @Query("""
            SELECT DISTINCT r
            FROM RolesModel r
            LEFT JOIN FETCH r.rolesPermisos rp
            LEFT JOIN FETCH rp.permiso
            """)
    List<RolesModel> findAllWithPermisos();

    @Query("""
            SELECT r
            FROM RolesModel r
            """)
    Page<RolesModel> findRoles(Pageable pageable);

}
