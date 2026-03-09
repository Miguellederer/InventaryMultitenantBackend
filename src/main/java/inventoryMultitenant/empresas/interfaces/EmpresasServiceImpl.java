package inventoryMultitenant.empresas.interfaces;

import org.springframework.data.domain.Page;
import inventoryMultitenant.empresas.dto.AllEmpresasDto;
import inventoryMultitenant.empresas.dto.EmpresasRequestDto;
import inventoryMultitenant.empresas.dto.EmpresasResponseDto;

public interface EmpresasServiceImpl {

    EmpresasResponseDto crearEmpresa(EmpresasRequestDto dto);

    Page<AllEmpresasDto> listarEmpresas(int page, int size);

}
