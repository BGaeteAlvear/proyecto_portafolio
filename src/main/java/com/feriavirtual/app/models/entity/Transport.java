package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "transports")
public class Transport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSPORT")
    @SequenceGenerator(name = "SEQ_TRANSPORT", allocationSize = 1, sequenceName = "SEQ_TRANSPORT")
    private Long id;
    private boolean refrigeration;
    private int capacity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_type_id", referencedColumnName = "id")
    private TransportType transportType;

}