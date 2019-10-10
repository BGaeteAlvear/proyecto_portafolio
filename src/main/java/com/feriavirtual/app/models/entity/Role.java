package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "authorities")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @SequenceGenerator(name = "SEQ_ADDRESS",allocationSize = 1,sequenceName = "SEQ_ADDRESS")
    private Long id;
    private String authority;

}
