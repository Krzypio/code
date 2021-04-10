package com.krzysztof.kuznia.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date saveDate;

    @Temporal(TemporalType.DATE)
    private Date withdrawDate;

    public boolean isPersisted() {
        return id != null;
    }

    //Getters and setters
    public Long getId() {
        return id;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public Date getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(Date withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    //Hashcode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @PrePersist
    public void beforePersist(){
        this.saveDate = new Date(System.currentTimeMillis());
    }
}
