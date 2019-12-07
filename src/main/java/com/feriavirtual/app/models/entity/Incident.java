package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "incidents")
public class Incident implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_INCIDENT")
    @SequenceGenerator(name = "SEQ_INCIDENT",allocationSize = 1,sequenceName = "SEQ_INCIDENT")
    private Long id;
    private String message;
    @Column(nullable = true)
    private String answer;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person transmitter;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Person receiver;
    @ManyToOne(fetch = FetchType.LAZY)
    public IncidentType incidentType;
    private Integer orderNumber;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
