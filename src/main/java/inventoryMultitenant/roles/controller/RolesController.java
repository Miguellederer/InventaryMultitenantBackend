package inventoryMultitenant.roles.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inventoryMultitenant.permisos.dto.AsignarPermisoDto;
import inventoryMultitenant.roles.dto.RoleDto;
import inventoryMultitenant.roles.dto.RolePermisosDto;
import inventoryMultitenant.roles.model.RolesModel;
import inventoryMultitenant.roles.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @PostMapping
    public RolesModel crearRole(@RequestBody RoleDto dto) {
        return rolesService.crearRole(dto);
    }

    @GetMapping
    public List<RolesModel> listarRoles() {
        return rolesService.listarRoles();
    }

    @GetMapping("/{id}")
    public RolesModel obtenerRole(@PathVariable String id) {
        return rolesService.obtenerRole(id);
    }

    @PutMapping("/{id}")
    public RolesModel actualizarRole(@PathVariable String id, @RequestBody RoleDto dto) {
        return rolesService.actualizarRole(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarRole(@PathVariable String id) {
        rolesService.eliminarRole(id);
    }

    @PostMapping("/{id}/permisos")
    public ResponseEntity<?> asignarPermisos(@PathVariable String id, @RequestBody AsignarPermisoDto dto) {
       rolesService.asignarPermisos(id, dto.getPermisos());
       return ResponseEntity.ok().body("Permisos asignados correctamente");
    }

    @GetMapping("/{id}/allPermisos")
    public RolePermisosDto listarPermisosPorRole(@PathVariable String id) {
        return rolesService.obtenerPermisosAsignados(id);
    }
    

}
