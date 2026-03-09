package inventoryMultitenant.roles.dto;

import java.util.List;
import lombok.Data;

@Data
public class RolesRequestDto {

    private String nombre;
    private String descripcion;
    private List<String> permisos;

}
