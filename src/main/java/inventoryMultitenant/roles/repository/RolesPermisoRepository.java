package inventoryMultitenant.roles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.roles.model.RolesPermisos;

@Repository
public interface RolesPermisoRepository extends JpaRepository<RolesPermisos, String> {

    void deleteByRoleId(String roleId);
    List<RolesPermisos> findByRoleId(String roleId);

}
