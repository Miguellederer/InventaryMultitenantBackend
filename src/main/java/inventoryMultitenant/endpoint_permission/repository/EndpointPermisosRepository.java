package inventoryMultitenant.endpoint_permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.endpoint_permission.model.EndpointPermisos;

@Repository
public interface EndpointPermisosRepository extends JpaRepository<EndpointPermisos, String> {

    List<EndpointPermisos> findByMetodoAndActivoTrue(String metodo);

    boolean existsByMetodoAndEndpoint(String metodo, String endpoint);

}
