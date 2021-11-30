package com.softtech.app.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Created by N.Bimeri on 24/08/2021.
 */
@Data
@MappedSuperclass
public class BaseEntity {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected String updatedBy = "";

    @PrePersist
    public void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createdAt = localDateTime;
        this.updatedAt = localDateTime;
        // addUpdatedByUser();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        // addUpdatedByUser();
    }

//    void addUpdatedByUser() {
//        try {
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            UserDetailsImpl user = (UserDetailsImpl) principal;
//            this.updatedBy = user.getFullNameDto() + "<" + user.getUsername() + ">";
//        } catch (Exception ex) {
//            this.updatedBy = "Default";
//        }
//    }
}
