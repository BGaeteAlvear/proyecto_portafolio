package com.feriavirtual.app.models.entity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="products_Available")
public class ProductAvailable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PRODUCTS_AVAILABLE")
    @SequenceGenerator(name = "SEQ_PRODUCTS_AVAILABLE",allocationSize = 1,sequenceName = "SEQ_PRODUCTS_AVAILABLE")
    private Long id;

    @NotNull
    private int stock;

    @NotNull
    private String stock_unity;

    private String saleType;

    @NotNull
    private int price;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;

    @Column(name = "date_expire")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date_expire;

    @NotNull
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    public Product product;

    @OneToOne(fetch = FetchType.LAZY)
    public Person person;

    public ProductAvailable(){
        this.status = true;
    }

}
