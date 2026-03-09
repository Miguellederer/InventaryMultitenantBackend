package inventoryMultitenant.security.scanner;

import java.util.Map;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import inventoryMultitenant.endpoint_permission.model.EndpointPermisos;
import inventoryMultitenant.endpoint_permission.repository.EndpointPermisosRepository;
import inventoryMultitenant.permisos.model.PermisosModel;
import inventoryMultitenant.permisos.repository.PermisosRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EndpointPermissionScanner implements CommandLineRunner {

    private final RequestMappingHandlerMapping handlerMapping;
    private final PermisosRepository permisosRepository;
    private final EndpointPermisosRepository endpointRepository;

    @Override
    public void run(String... args) {

        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {

            RequestMappingInfo info = entry.getKey();

            Set<String> patterns = info.getPatternValues();
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();

            for (String pattern : patterns) {

                if (pattern.contains("/auth") ||
                        pattern.contains("/error") ||
                        pattern.contains("swagger") ||
                        pattern.contains("actuator")) {
                    continue;
                }

                for (RequestMethod method : methods) {

                    String permisoCodigo = generarPermiso(method.name(), pattern);

                    if (permisoCodigo == null) {
                        continue;
                    }
                    if (!permisosRepository.existsByCodigo(permisoCodigo)) {

                        PermisosModel permiso = new PermisosModel();
                        permiso.setCodigo(permisoCodigo);
                        permiso.setDescripcion("Permiso generado automáticamente");

                        permisosRepository.save(permiso);

                    }

                    if (!endpointRepository.existsByMetodoAndEndpoint(method.name(), pattern)) {

                        EndpointPermisos endpoint = new EndpointPermisos();
                        endpoint.setMetodo(method.name());
                        endpoint.setEndpoint(pattern);
                        endpoint.setPermisoCodigo(permisoCodigo);

                        endpointRepository.save(endpoint);

                    }
                }
            }
        }

    }

    private String generarPermiso(String metodo, String endpoint) {

        String[] partes = endpoint.split("/");

        if (partes.length > 4) {
            return null;
        }

        if (partes.length < 4) {
            return null;
        }

        String modulo = partes[3].toUpperCase();

        String accion = switch (metodo) {
            case "GET" -> "LEER";
            case "POST" -> "CREAR";
            case "PUT" -> "ACTUALIZAR";
            case "DELETE" -> "ELIMINAR";
            default -> "ACCESO";
        };

        return modulo + "_" + accion;
    }
}
