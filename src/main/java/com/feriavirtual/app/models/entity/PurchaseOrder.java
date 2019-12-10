package com.feriavirtual.app.models.entity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private int status;  // 1 ACEPTADA, 2 EN LICITACION , 3 LICITACIONES ACEPTADAS , 4 RECEPCIONADO EN BODEGA, 5 EN TRANSITO , 6 RECEPCIONADO, 7 FINALIZADO

    // CLIENTE
    private Long customer_id;
    private String customer_type; //INTERNO /EXTERNO

    // PRODUCTO
    private Long product_id;

    private int quantity_order;

    private int unity_order;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @Lob
    private String description;

    //PRODUCTOR
    private Long prod_public_tender_id;

    //TRANSPORTISTA
    private Long trans_public_tender_id;

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
