package inventoryMultitenant.permisos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.permisos.model.PermisosModel;

@Repository
public interface PermisosRepository extends JpaRepository<PermisosModel, String> {

    

  


}
