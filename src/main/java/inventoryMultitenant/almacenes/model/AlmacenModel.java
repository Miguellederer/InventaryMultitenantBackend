package inventoryMultitenant.almacenes.model;


/**
* @author Miguel Lederer
* @author Oliver Cruz
* @version 1.0
* 20/02/2026
* */

import java.util.ArrayList;
import java.util.List;
import inventoryMultitenant.centros.model.CentrosModel;
import inventoryMultitenant.config.AuditorialModel;
import inventoryMultitenant.stock.model.StockModel;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Table(name = "almacenes")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AlmacenModel extends AuditorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    @Column(name = "contacto", nullable = false, length = 50)
    private String contacto;

    @NotBlank
    @Size(max = 50)
    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centro_id", nullable = false)
    private CentrosModel centro;

    @OneToMany(mappedBy = "almacen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockModel> stock = new ArrayList<>();

}
