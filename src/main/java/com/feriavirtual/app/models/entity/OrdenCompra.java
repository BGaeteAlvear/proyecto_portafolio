package com.feriavirtual.app.models.entity;


import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "ordenes_compra")
@Component
@Scope("session")
public class OrdenCompra implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_ORDEN_COMPRA")
    @SequenceGenerator(name = "SEQ_ORDEN_COMPRA",allocationSize = 1,sequenceName = "SEQ_ORDEN_COMPRA")
    private Long id;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha;

    @OneToMany(mappedBy = "ordenCompra" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> listProductos;

    private  int cantidad;
    private String estado;
    private int montoGanancia;
    private int montoSeguro;




    @Column(name = "fecha_despacho")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaDespacho;


}
