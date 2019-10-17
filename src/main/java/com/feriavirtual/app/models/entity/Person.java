package com.feriavirtual.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "persons")
public class Person  implements Serializable {

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
    @Column(name = "role_id")
    private int roleId;

    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;



    public Person(){
        this.roleId = 1;
        this.passwordRecovery = 1;
        this.status = true;
    }

}
