package inventoryMultitenant.roles.model;

import java.util.ArrayList;
import java.util.List;
import inventoryMultitenant.config.AuditorialModel;
import inventoryMultitenant.users.model.UsersModel;
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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
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


}
