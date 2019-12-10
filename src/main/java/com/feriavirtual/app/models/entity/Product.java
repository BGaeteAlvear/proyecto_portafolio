package com.feriavirtual.app.models.entity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.xml.soap.Text;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name="products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PRODUCTS")
    @SequenceGenerator(name = "SEQ_PRODUCTS",allocationSize = 1,sequenceName = "SEQ_PRODUCTS")

    private Long id;
    @NotEmpty
    private String name;

    @NotEmpty
    @Lob
    private String description;

    private String image;
    private Boolean status;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    public Category category;

    public Product(){
       this.status = true;
     }

    @PrePersist
    public void createdAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void updatedAt() {
        this.updatedAt = new Date();
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "product" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseOrder> listPurchaseOrders;

}
