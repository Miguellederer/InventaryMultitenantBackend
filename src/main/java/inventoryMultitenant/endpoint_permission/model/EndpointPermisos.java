package inventoryMultitenant.endpoint_permission.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endpoint_permisos", indexes = {
        @Index(name = "idx_metodo_endpoint", columnList = "metodo,endpoint")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointPermisos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "metodo", nullable = false, length = 10)
    private String metodo;

    @Column(name = "endpoint", nullable = false, length = 255)
    private String endpoint;

    @Column(name = "permiso_codigo", nullable = false, length = 100)
    private String permisoCodigo;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

}
