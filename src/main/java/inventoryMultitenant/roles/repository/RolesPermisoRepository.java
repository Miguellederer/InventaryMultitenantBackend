package inventoryMultitenant.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.roles.model.RolesPermisos;

@Repository
public interface RolesPermisoRepository extends JpaRepository<RolesPermisos, String> {
    
}
