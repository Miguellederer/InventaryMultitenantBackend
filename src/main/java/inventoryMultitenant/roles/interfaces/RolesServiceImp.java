package inventoryMultitenant.roles.interfaces;

import java.util.List;
import inventoryMultitenant.roles.dto.RoleDto;
import inventoryMultitenant.roles.dto.RolePermisosDto;
import inventoryMultitenant.roles.model.RolesModel;

public interface RolesServiceImp {

    void asignarPermisos(String id, List<String> codigoPermisos);

    RolesModel crearRole(RoleDto dto);

    List<RolesModel> listarRoles();

    RolesModel obtenerRole(String id);

    RolesModel actualizarRole(String id, RoleDto dto);

    void eliminarRole(String id);

    RolePermisosDto obtenerPermisosAsignados(String id);

    

}
