package inventoryMultitenant.roles.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import inventoryMultitenant.permisos.model.PermisosModel;
import inventoryMultitenant.permisos.repository.PermisosRepository;
import inventoryMultitenant.roles.dto.AllRolesDto;
import inventoryMultitenant.roles.dto.RoleDto;
import inventoryMultitenant.roles.dto.RolesPermisosDto;
import inventoryMultitenant.roles.dto.RolesRequestDto;
import inventoryMultitenant.roles.interfaces.RolesServiceImp;
import inventoryMultitenant.roles.model.RolesModel;
import inventoryMultitenant.roles.model.RolesPermisos;
import inventoryMultitenant.roles.repository.RolesPermisoRepository;
import inventoryMultitenant.roles.repository.RolesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RolesService implements RolesServiceImp {

    private final RolesRepository rolesRepository;
    private final PermisosRepository permisosRepository;
    private final RolesPermisoRepository rolesPermisoRepository;

    // Crear Role y sus
    @Override
    @Transactional
    public RolesModel crearRole(RolesRequestDto dto) {

        RolesModel role = new RolesModel();
        role.setNombre(dto.getNombre());
        role.setDescripcion(dto.getDescripcion());

        role = rolesRepository.save(role);

        if (dto.getPermisos() != null) {

            for (String codigo : dto.getPermisos()) {

                PermisosModel permiso = permisosRepository.findByCodigo(codigo)
                        .orElseThrow(() -> new RuntimeException("Permiso no encontrado: " + codigo));
                RolesPermisos rp = new RolesPermisos();
                rp.setRole(role);
                rp.setPermiso(permiso);
                rolesPermisoRepository.save(rp);
            }
        }
        return role;
    }

    // Listar Roles y sus permisos
    @Override
    public Page<AllRolesDto> listarRoles(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<RolesModel> roles = rolesRepository.findRoles(pageable);

        return roles.map(role -> {
            AllRolesDto dto = new AllRolesDto();
            dto.setRoleId(role.getId());
            dto.setNombre(role.getNombre());
            dto.setDescripcion(role.getDescripcion());
            return dto;
        });
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
    public RolesPermisosDto obtenerPermisosAsignados(String id) {

        RolesModel role = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<RolesPermisos> relaciones = rolesPermisoRepository.findByRoleId(id);

        List<String> permisos = relaciones.stream()
                .map(rp -> rp.getPermiso().getCodigo())
                .toList();

        RolesPermisosDto response = new RolesPermisosDto();

        response.setRoleId(role.getId());
        response.setNombreRole(role.getNombre());
        response.setPermisos(permisos);

        return response;
    }

}
