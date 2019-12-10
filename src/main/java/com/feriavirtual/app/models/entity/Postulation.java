package com.feriavirtual.app.models.entity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="postulation")
public class Postulation implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_POSTULATION")
    @SequenceGenerator(name = "SEQ_POSTULATION",allocationSize = 1,sequenceName = "SEQ_POSTULATION")
    private Long id;

    @NotNull
    private int tenderId;

    @NotNull
    private int amount;

    @NotNull
    private int status; // 1 adjudicada /2 rechasada

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tenders tenders;
    
}
