package com.feriavirtual.app.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name="confirmation_token")
public class ConfirmationToken implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_TOKEN")
    @SequenceGenerator(name = "SEQ_TOKEN",allocationSize = 1,sequenceName = "SEQ_TOKEN")
    private Long id;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name= "person_id")
    private Person person;

    public ConfirmationToken(Person person) {
        this.person = person;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
