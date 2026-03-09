package inventoryMultitenant.stock.model;

/**
* @author Miguel Lederer
* @author Oliver Cruz
* @version 1.0
* 20/02/2026
* */

import com.fasterxml.jackson.annotation.JsonIgnore;
import inventoryMultitenant.almacenes.model.AlmacenModel;
import inventoryMultitenant.config.AuditorialModel;
import inventoryMultitenant.productos.model.ProductosModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
public class StockModel extends AuditorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "almacen_id", nullable = false)
    private AlmacenModel almacen;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductosModel producto;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}
