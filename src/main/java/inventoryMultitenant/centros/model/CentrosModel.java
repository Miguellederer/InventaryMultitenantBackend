package inventoryMultitenant.centros.model;


/**
* @author Miguel Lederer
* @author Oliver Cruz
* @version 1.0
* 20/02/2026
* */


import java.util.ArrayList;
import java.util.List;

import inventoryMultitenant.config.AuditorialModel;
import inventoryMultitenant.empresas.model.EmpresasModel;
import inventoryMultitenant.users.model.UsersModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Table(name = "centros")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CentrosModel extends AuditorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    @Column(name = "contacto", nullable = false, length = 20)
    private String contacto;

    @Column(name = "direccion")
    private String direccion;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresasModel empresas;

    @OneToMany(mappedBy = "centro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsersModel> users = new ArrayList<>();

}
