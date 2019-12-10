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
@Table(name = "persons")
@Component
@Scope("session")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PERSON")
    @SequenceGenerator(name = "SEQ_PERSON",allocationSize = 1,sequenceName = "SEQ_PERSON")
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "very_last_name")
    private String veryLastName;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "id_number_type")
    private String idNumberType;
    private String phone;
    private String email;
    private String password;
    private String username;
    private Boolean status;
    @Column(name = "password_recovery")
    private int passwordRecovery;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "id_company_number")
    private String idCompanyNumber;
    private String position;
    @Column(name = "commercial_address")
    private String commercialAddress;
    @Column(name = "company_phone")
    private String companyPhone;
    @Column(name = "company_email")
    private String companyEmail;

    private String address;

    private String city;

    private String country;

    @Column(name = "end_contract")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endContract;

    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Authority authority;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseOrder> list;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Postulation> listPostulation;

    public Person(){
        this.enabled = true;
        this.passwordRecovery = 1;
        this.status = true;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private static final long serialVersionUID = 706443423338604396L;

}
