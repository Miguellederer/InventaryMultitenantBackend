package inventoryMultitenant.roles.dto;

import java.util.List;
import lombok.Data;

@Data
public class RolesPermisosDto {

    private String roleId;
    private String nombreRole;
    private List<String> permisos;
    
}
