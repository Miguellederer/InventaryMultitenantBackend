package inventoryMultitenant.roles.model;


/**
* @author Miguel Lederer
* @author Oliver Cruz
* @version 1.0
* 20/02/2026
* */

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import inventoryMultitenant.config.AuditorialModel;
import inventoryMultitenant.users.model.UsersModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RolesModel extends AuditorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nombre", nullable = false , length = 50, unique = true)
    private String nombre;

    @Size(max = 250)
    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @OneToMany(mappedBy = "role")
    private List<UsersModel> users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolesPermisos> rolesPermisos = new ArrayList<>();


}
