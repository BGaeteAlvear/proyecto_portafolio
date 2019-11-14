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
    @ManyToOne(fetch = FetchType.LAZY)
    private Person transmitter;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person receiver;
    @ManyToOne(fetch = FetchType.LAZY)
    public IncidentType incidentType;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Person getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(Person transmitter) {
        this.transmitter = transmitter;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }
}
