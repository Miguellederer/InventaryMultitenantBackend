package inventoryMultitenant.empresas.model;



/**
* @author Miguel Lederer
* @author Oliver Cruz
* @author Luis García
* @version 1.0
* 20/02/2026
* */


import java.util.List;

import inventoryMultitenant.centros.model.CentrosModel;
import inventoryMultitenant.config.AuditorialModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresas")
public class EmpresasModel extends AuditorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre", nullable = false, length = 100 )
    private String nombre;

    @NotBlank
    @Size(max = 100)
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @NotBlank
    @Size(max = 100)
    @Column(name = "contacto", nullable = false, length = 100)
    private String contacto;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @OneToMany(mappedBy = "empresas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CentrosModel> centros;
  

}
