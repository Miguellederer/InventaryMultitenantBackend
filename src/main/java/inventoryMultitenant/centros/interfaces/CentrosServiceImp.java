package inventoryMultitenant.centros.interfaces;

import org.springframework.data.domain.Page;
import inventoryMultitenant.centros.dto.AllCentrosDto;
import inventoryMultitenant.centros.dto.CentrosRequestDto;
import inventoryMultitenant.centros.dto.CentrosResponseDto;

public interface CentrosServiceImp {

        CentrosResponseDto crearCentro(CentrosRequestDto dto);

        Page<AllCentrosDto> listarCentros(int page, int size);

}
