package com.feriavirtual.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @SequenceGenerator(name = "SEQ_ADDRESS",allocationSize = 1,sequenceName = "SEQ_ADDRESS")
    private Long id;
    private String country;
    private String city;
    private String address;
    private String addressNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    private static final long serialVersionUID = -6837339908077521409L;


}
