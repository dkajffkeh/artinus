package com.artinus.userapp.domain.entity.base;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false, columnDefinition = "datetime")
    protected LocalDateTime createdAt = now();

    @LastModifiedDate
    @Column(name = "MODIFIED_AT", columnDefinition = "datetime")
    protected LocalDateTime modifiedAt = now();

    public BaseEntity() {

    }

    public BaseEntity(LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
