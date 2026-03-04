package inventoryMultitenant.roles.dto;

import java.util.List;
import lombok.Data;

@Data
public class RolePermisosDto {

    private String roleId;
    private String nombreRole;
    private List<String> permisos;
    
}
