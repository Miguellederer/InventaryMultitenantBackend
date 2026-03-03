package inventoryMultitenant.permisos.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import inventoryMultitenant.enums.Accion;
import inventoryMultitenant.roles.model.RolesPermisos;
import inventoryMultitenant.stock.model.StockModel;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Miguel Lederer
 * @author Oliver Cruz
 * @version 1.0
 *          20/02/2026
 */

@Entity
@Table(name = "permisos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermisosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "codigo", nullable = false, unique = true, length = 100)
    private String codigo;

    @Column(name = "modulo", nullable = false, length = 50)
    private String modulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion", nullable = false, length = 20)
    private Accion accion;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "permiso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolesPermisos> rolesPermisos = new ArrayList<>();


}
