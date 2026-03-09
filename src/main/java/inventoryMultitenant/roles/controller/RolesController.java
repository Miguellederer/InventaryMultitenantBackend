package inventoryMultitenant.roles.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inventoryMultitenant.roles.dto.AllRolesDto;
import inventoryMultitenant.roles.dto.RoleDto;
import inventoryMultitenant.roles.dto.RolesPermisosDto;
import inventoryMultitenant.roles.dto.RolesRequestDto;
import inventoryMultitenant.roles.model.RolesModel;
import inventoryMultitenant.roles.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
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
    public RolesModel crearRole(@RequestBody RolesRequestDto dto) {
        return rolesService.crearRole(dto);
    }

    @GetMapping
    public Page<AllRolesDto> listarRoles(
            @RequestParam int page,
            @RequestParam int size) {
        return rolesService.listarRoles(page, size);
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

    @GetMapping("/{id}/allPermisos")
    public RolesPermisosDto listarPermisosPorRole(@PathVariable String id) {
        return rolesService.obtenerPermisosAsignados(id);
    }

}
