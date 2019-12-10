package com.feriavirtual.app.models.entity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="tenders")
public class Tenders implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_TENDERS")
    @SequenceGenerator(name = "SEQ_TENDERS",allocationSize = 1,sequenceName = "SEQ_TENDERS")
    private Long id;

    @NotNull
    private int status; // 1 active / 2 finished

    @NotNull
    private int purchaseOrderId;

    @NotNull
    private int postulationId;

    @NotNull
    private int tendersType;  // 1 productor  / 2 transportista

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @Column(name = "expirate_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date expirateDate;

    @OneToMany(mappedBy = "tenders" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Postulation> listPostulations;

    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseOrder purchaseOrder;

}
