package inventoryMultitenant.endpoint_permission.filter;

import java.io.IOException;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import inventoryMultitenant.endpoint_permission.model.EndpointPermisos;
import inventoryMultitenant.endpoint_permission.service.EndpointPermisosService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EndpointPermisosFilter extends OncePerRequestFilter {

    private final EndpointPermisosService endpointPermisosService;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String metodo = request.getMethod();
        String endpoint = request.getRequestURI();

        System.out.println("Metodo recibido: " + metodo);
        System.out.println("Endpoint recibido: " + endpoint);

        List<EndpointPermisos> permisos = endpointPermisosService.obtenerPermisosPorMetodo(metodo);

        for (EndpointPermisos permiso : permisos) {

            System.out.println("Comparando con: " + permiso.getEndpoint());

            if (pathMatcher.match(permiso.getEndpoint(), endpoint)) {

                System.out.println("MATCH encontrado");

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                System.out.println("Permiso requerido: " + permiso.getPermisoCodigo());
                System.out.println("Authentication object: " + auth);
                System.out.println("Cantidad authorities: " + auth.getAuthorities().size());

                auth.getAuthorities().forEach(a -> System.out.println("Authority usuario: " + a.getAuthority()));

                auth.getAuthorities().forEach(a -> System.out.println("Authority usuario: " + a.getAuthority()));

                boolean autorizado = auth.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals(permiso.getPermisoCodigo()));

                if (!autorizado) {
                    System.out.println("USUARIO NO AUTORIZADO");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }

                System.out.println("USUARIO AUTORIZADO");
                break;
            }
        }

        filterChain.doFilter(request, response);
    }
}
