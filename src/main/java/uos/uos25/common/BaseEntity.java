package uos.uos25.common;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    @Column(
            name = "created_at",
            columnDefinition = "TIMESTAMP(6)",
            nullable = false,
            updatable = false)
    private LocalDateTime createdAt = null;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP(6)", nullable = false)
    private LocalDateTime updatedAt = null;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
