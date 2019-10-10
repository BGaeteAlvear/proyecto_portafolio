package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "incidents")
public class Incident implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_INCIDENT")
    @SequenceGenerator(name = "SEQ_INCIDENT",allocationSize = 1,sequenceName = "SEQ_INCIDENT")
    private Long id;

    private String message;
    private Boolean status;
    private String emisor;
    private String receptor;

    public Incident(){
        this.status = true;
    }



}
