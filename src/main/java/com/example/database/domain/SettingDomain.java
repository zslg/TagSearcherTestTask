package com.example.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "setting")
public class SettingDomain {

    @Id
    private Short id;

    @Column(name = "init_flag")
    private Boolean initFlag;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Boolean getInitFlag() {
        return initFlag;
    }

    public void setInitFlag(Boolean initFlag) {
        this.initFlag = initFlag;
    }
}
