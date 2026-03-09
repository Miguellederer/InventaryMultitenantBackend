package inventoryMultitenant.roles.interfaces;

import org.springframework.data.domain.Page;
import inventoryMultitenant.roles.dto.AllRolesDto;
import inventoryMultitenant.roles.dto.RoleDto;
import inventoryMultitenant.roles.dto.RolesPermisosDto;
import inventoryMultitenant.roles.dto.RolesRequestDto;
import inventoryMultitenant.roles.model.RolesModel;

public interface RolesServiceImp {

    RolesModel crearRole(RolesRequestDto dto);

    Page<AllRolesDto> listarRoles(int page, int size);

    RolesModel obtenerRole(String id);

    RolesModel actualizarRole(String id, RoleDto dto);

    void eliminarRole(String id);

    RolesPermisosDto obtenerPermisosAsignados(String id);


}
