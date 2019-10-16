package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(name = "transport_types")
public class TransportType implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_TRANSPORT_TYPE")
    @SequenceGenerator(name = "SEQ_TRANSPORT_TYPE", allocationSize = 1, sequenceName = "SEQ_TRANSPORT_TYPE")
    private Long id;
    private String name;
    private String description;

}
