package inventoryMultitenant.empresas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import inventoryMultitenant.empresas.dto.AllEmpresasDto;
import inventoryMultitenant.empresas.dto.EmpresasRequestDto;
import inventoryMultitenant.empresas.dto.EmpresasResponseDto;
import inventoryMultitenant.empresas.service.EmpresasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public Page<AllEmpresasDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return empresasService.listarEmpresas(page, size);
    }

    
    
}
