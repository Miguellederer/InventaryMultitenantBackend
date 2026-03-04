package inventoryMultitenant.permisos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import inventoryMultitenant.permisos.service.PermisosService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/permisos")
@RequiredArgsConstructor
public class PermisosController {

    private final PermisosService permisosService;

    @GetMapping
    public Map<String, List<String>> obtenerPermisos(){
        return permisosService.obtenerPermisosAgrupados();
    }
    


    
}
