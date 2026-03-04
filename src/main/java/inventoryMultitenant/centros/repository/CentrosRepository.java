package inventoryMultitenant.centros.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import inventoryMultitenant.centros.model.CentrosModel;


@Repository
public interface CentrosRepository extends JpaRepository<CentrosModel, String> {

    


    
}
