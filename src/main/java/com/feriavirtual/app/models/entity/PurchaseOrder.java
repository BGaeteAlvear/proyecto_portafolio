package com.feriavirtual.app.models.entity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="purchase_order")
public class PurchaseOrder implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PURCHASE_ORDER")
    @SequenceGenerator(name = "SEQ_PURCHASE_ORDER",allocationSize = 1,sequenceName = "SEQ_PURCHASE_ORDER")

    //PURCHASE_ORDER
    private Long id;
    @NotNull
    private int status;  // 1 ACEPTADA, 2 EN LICITACION , 3 LICITACIONES ACEPTADAS , 4 RECEPCIONADO EN BODEGA, 5 EN TRANSITO , 6 RECEPCIONADO, 7 FINALIZADO


    // CLIENTE
    @NotNull
    private int customer_id;
    @NotNull
    private String customer_type; //INTERNO /EXTERNO

    // PRODUCTO
    @NotNull
    private int product_id;
    @NotNull
    private int quantity_order;
    @NotNull
    private int unity_order;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @NotEmpty
    @Lob
    private String description;

    //PRODUCTOR
    private int prod_public_tender_id;

    //TRANSPORTISTA
    private int trans_public_tender_id;

    //DATOS ADUANA
    private double aduana;
    private double insurance;

    //GANANCIAS
    private double gain;

    @OneToMany(mappedBy = "purchaseOrder" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tenders> listTenders;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public PurchaseOrder() {
        this.status = 1;
    }


}
