package inventoryMultitenant.centros.interfaces;

import inventoryMultitenant.centros.dto.CentrosRequestDto;
import inventoryMultitenant.centros.dto.CentrosResponseDto;


public interface CentrosServiceImp {

        CentrosResponseDto crearCentro(CentrosRequestDto dto);


    
}
