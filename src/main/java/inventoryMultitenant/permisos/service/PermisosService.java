package inventoryMultitenant.permisos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import inventoryMultitenant.permisos.Interfaces.PermisosServicesImp;
import inventoryMultitenant.permisos.model.PermisosModel;
import inventoryMultitenant.permisos.repository.PermisosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermisosService implements PermisosServicesImp {

    private final PermisosRepository permisosRepository;

    @Override
    public Map<String, List<String>> obtenerPermisosAgrupados() {

        List<PermisosModel> permisos = permisosRepository.findAll();

        Map<String, List<String>> resultado = new HashMap<>();

        for (PermisosModel permiso : permisos) {

            String modulo = permiso.getModulo();
            String accion = permiso.getAccion().name();

            resultado
                    .computeIfAbsent(modulo, k -> new ArrayList<>())
                    .add(accion);
        }

        return resultado;
    }

}
