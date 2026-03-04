package inventoryMultitenant.empresas.interfaces;

import inventoryMultitenant.empresas.dto.EmpresasRequestDto;
import inventoryMultitenant.empresas.dto.EmpresasResponseDto;

public interface EmpresasServiceImpl {

    EmpresasResponseDto crearEmpresa(EmpresasRequestDto dto);

}
