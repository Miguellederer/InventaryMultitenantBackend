package inventoryMultitenant.roles.service;

import java.util.List;
import org.springframework.stereotype.Service;
import inventoryMultitenant.permisos.model.PermisosModel;
import inventoryMultitenant.permisos.repository.PermisosRepository;
import inventoryMultitenant.roles.dto.RoleDto;
import inventoryMultitenant.roles.dto.RolePermisosDto;
import inventoryMultitenant.roles.interfaces.RolesServiceImp;
import inventoryMultitenant.roles.model.RolesModel;
import inventoryMultitenant.roles.model.RolesPermisos;
import inventoryMultitenant.roles.repository.RolesPermisoRepository;
import inventoryMultitenant.roles.repository.RolesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolesService implements RolesServiceImp {

    private final RolesRepository rolesRepository;
    private final PermisosRepository permisosRepository;
    private final RolesPermisoRepository rolesPermisoRepository;

    @Override
    public void asignarPermisos(String id, List<String> codigosPermisos) {

        RolesModel role = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado: " + id));

        for (String codigo : codigosPermisos) {

            PermisosModel permiso = permisosRepository.findByCodigo(codigo)
                    .orElseThrow(() -> new RuntimeException("Permiso no encontrado: " + codigo));

            RolesPermisos rp = new RolesPermisos();
            rp.setRole(role);
            rp.setPermiso(permiso);

            rolesPermisoRepository.save(rp);
        }
    }

    @Override
    public RolesModel crearRole(RoleDto dto) {

        RolesModel role = new RolesModel();
        role.setNombre(dto.getNombre());
        role.setDescripcion(dto.getDescripcion());

        return rolesRepository.save(role);
    }

    @Override
    public List<RolesModel> listarRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public RolesModel obtenerRole(String id) {
        return rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado: " + id));
    }

    @Override
    public void eliminarRole(String id) {
        rolesPermisoRepository.deleteById(id);
    }

    @Override
    public RolesModel actualizarRole(String id, RoleDto dto) {
        RolesModel role = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado: " + id));

        role.setNombre(dto.getNombre());
        role.setDescripcion(dto.getDescripcion());

        return rolesRepository.save(role);
    }

    @Override
    public RolePermisosDto obtenerPermisosAsignados(String id) {

        RolesModel role = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<RolesPermisos> relaciones = rolesPermisoRepository.findByRoleId(id);

        List<String> permisos = relaciones.stream()
                .map(rp -> rp.getPermiso().getCodigo())
                .toList();

        RolePermisosDto response = new RolePermisosDto();

        response.setRoleId(role.getId());
        response.setNombreRole(role.getNombre());
        response.setPermisos(permisos);

        return response;
    }
}
