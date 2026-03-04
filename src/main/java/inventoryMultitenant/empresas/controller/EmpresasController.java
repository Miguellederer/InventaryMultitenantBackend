package inventoryMultitenant.empresas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import inventoryMultitenant.empresas.dto.EmpresasRequestDto;
import inventoryMultitenant.empresas.dto.EmpresasResponseDto;
import inventoryMultitenant.empresas.service.EmpresasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("api/v1/empresas")
@RestController
@RequiredArgsConstructor
public class EmpresasController {

    private final EmpresasService empresasService;

    @PostMapping
    public EmpresasResponseDto crearEmpresa(@RequestBody EmpresasRequestDto dto){
        return empresasService.crearEmpresa(dto);
    }

    
    
}
