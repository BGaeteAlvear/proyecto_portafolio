package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "authorities")
public class Authority implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_AUTHORITY")
    @SequenceGenerator(name = "SEQ_AUTHORITY",allocationSize = 1,sequenceName = "SEQ_AUTHORITY")
    private Long id;
    private String authority;

    private static final long serialVersionUID = 4107751503775513897L;

}
