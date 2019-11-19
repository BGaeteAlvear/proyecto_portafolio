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
    @ManyToOne(fetch = FetchType.LAZY)
    private TransportType transportType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isRefrigeration() {
        return refrigeration;
    }

    public void setRefrigeration(boolean refrigeration) {
        this.refrigeration = refrigeration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
