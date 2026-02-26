package inventoryMultitenant.productos.model;

/**
* @author Miguel Lederer
* @author Oliver Cruz
* @author Luis García
* @version 1.0
* 20/02/2026
* */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import inventoryMultitenant.config.AuditorialModel;
import inventoryMultitenant.stock.model.StockModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class ProductosModel extends AuditorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "sku", nullable = false, length = 50, unique = true)
    private String sku;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 15, scale = 2)
    private BigDecimal precio;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockModel> stocks = new ArrayList<>();

}
