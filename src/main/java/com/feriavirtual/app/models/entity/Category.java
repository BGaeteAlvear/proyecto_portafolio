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
@Table(name = "categories")
public class Category implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_CATEGORY")
    @SequenceGenerator(name = "SEQ_CATEGORY",allocationSize = 1,sequenceName = "SEQ_CATEGORY")
    private Long id;
    private String name;
    private String description;

    private static final long serialVersionUID = 2625797175843733106L;


}
