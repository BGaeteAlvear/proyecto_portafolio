package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "incidents_types")
public class IncidentType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PERSON")
    @SequenceGenerator(name = "SEQ_PERSON",allocationSize = 1,sequenceName = "SEQ_PERSON")
    private Long id;
    private String name;
    private String description;

}
