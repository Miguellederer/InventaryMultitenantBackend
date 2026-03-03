package inventoryMultitenant.users.model;


/**
* @author Miguel Lederer
* @author Oliver Cruz
* @version 1.0
* 20/02/2026
* */

import com.fasterxml.jackson.annotation.JsonIgnore;
import inventoryMultitenant.centros.model.CentrosModel;
import inventoryMultitenant.config.AuditorialModel;
import inventoryMultitenant.roles.model.RolesModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class UsersModel extends AuditorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centro_id", nullable = false)
    private CentrosModel centro;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RolesModel role;

}
