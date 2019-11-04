package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "incident_types")
public class IncidentType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_INCIDENT_TYPE")
    @SequenceGenerator(name = "SEQ_INCIDENT_TYPE",allocationSize = 1,sequenceName = "SEQ_INCIDENT_TYPE")
    private Long id;
    private String name;
    private String description;

}
