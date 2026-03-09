package inventoryMultitenant.centros.dto;

import lombok.Data;

@Data
public class CentrosRequestDto {

    private String nombre;
    private String contacto;
    private String direccion;
    private String correo;
    private String empresaId;
    
}
