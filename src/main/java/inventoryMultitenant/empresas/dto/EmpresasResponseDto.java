package inventoryMultitenant.empresas.dto;

import lombok.Data;

@Data
public class EmpresasResponseDto {

    private String id;
    private String nombre;
    private String direccion;
    private String correo;
    private String contacto;
}
