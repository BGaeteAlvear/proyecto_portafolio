package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "roles")
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_ROLE")
    @SequenceGenerator(name = "SEQ_ROLE",allocationSize = 1,sequenceName = "SEQ_ROLE")
    private Long id;
    private String name;
    private String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private static final long serialVersionUID = -624120425068441184L;

}
