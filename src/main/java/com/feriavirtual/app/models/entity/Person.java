package com.feriavirtual.app.models.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
//@Data
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVeryLastName() {
        return veryLastName;
    }

    public void setVeryLastName(String veryLastName) {
        this.veryLastName = veryLastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumberType() {
        return idNumberType;
    }

    public void setIdNumberType(String idNumberType) {
        this.idNumberType = idNumberType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getPasswordRecovery() {
        return passwordRecovery;
    }

    public void setPasswordRecovery(int passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIdCompanyNumber() {
        return idCompanyNumber;
    }

    public void setIdCompanyNumber(String idCompanyNumber) {
        this.idCompanyNumber = idCompanyNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCommercialAddress() {
        return commercialAddress;
    }

    public void setCommercialAddress(String commercialAddress) {
        this.commercialAddress = commercialAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getEndContract() {
        return endContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public List<PurchaseOrder> getList() {
        return list;
    }

    public void setList(List<PurchaseOrder> list) {
        this.list = list;
    }

    public List<Postulation> getListPostulation() {
        return listPostulation;
    }

    public void setListPostulation(List<Postulation> listPostulation) {
        this.listPostulation = listPostulation;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 706443423338604396L;

}
