package com.krzysztof.kuznia.backend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date withdrawDate = new Date(Long.MIN_VALUE);

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Date getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(Date withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @PrePersist
    public void beforePersist(){
        setSaveDate(new Date(System.currentTimeMillis()));
    }
}
