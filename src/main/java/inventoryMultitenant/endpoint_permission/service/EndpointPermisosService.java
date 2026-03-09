package inventoryMultitenant.endpoint_permission.service;

import java.util.List;
import org.springframework.stereotype.Service;
import inventoryMultitenant.endpoint_permission.model.EndpointPermisos;
import inventoryMultitenant.endpoint_permission.repository.EndpointPermisosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EndpointPermisosService {

    private final EndpointPermisosRepository repository;

    public List<EndpointPermisos> obtenerPermisosPorMetodo(String metodo) {
        return repository.findByMetodoAndActivoTrue(metodo);
    }

}
