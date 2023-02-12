package com.example.sekkison.user;

import com.example.sekkison.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "content")
    private String content;
}
