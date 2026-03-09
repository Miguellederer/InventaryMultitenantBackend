package inventoryMultitenant.empresas.dto;

import lombok.Data;

@Data
public class EmpresasRequestDto {

    private String nombre;
    private String direccion;
    private String correo;
    private String contacto;

}
