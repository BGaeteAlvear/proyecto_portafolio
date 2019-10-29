package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @SequenceGenerator(name = "SEQ_ADDRESS",allocationSize = 1,sequenceName = "SEQ_ADDRESS")
    private Long id;

        @Column(length = 30, unique = true)
        private String userName;
        @Column(length = 60)
        private String password;
        private Boolean enabled;

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name= "usuario_id")
        private List<Authority> authorities;
}
