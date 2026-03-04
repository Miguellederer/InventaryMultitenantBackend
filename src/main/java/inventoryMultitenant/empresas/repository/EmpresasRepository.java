package inventoryMultitenant.empresas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.empresas.model.EmpresasModel;

@Repository
public interface EmpresasRepository extends JpaRepository<EmpresasModel, String> {

    


    
}
