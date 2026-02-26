package inventoryMultitenant.config;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  
public class AuditorialModel {

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by", nullable = true, length = 255)
    private String updateBy;
    
}
