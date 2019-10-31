package com.feriavirtual.app.models.entity;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name="products_Available")
public class ProductAvailable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PRODUCTS_AVAILABLE")
    @SequenceGenerator(name = "SEQ_PRODUCTS_AVAILABLE",allocationSize = 1,sequenceName = "SEQ_PRODUCTS_AVAILABLE")

    private Long id;
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private int stock;

    @NotNull
    private int price;

    private String image;
    private Boolean status;



    @ManyToOne(fetch = FetchType.LAZY)
    public Category category;

    public ProductAvailable(){
        this.status = true;
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
}
