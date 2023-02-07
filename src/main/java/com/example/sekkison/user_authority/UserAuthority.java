package com.example.sekkison.user_authority;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_authorities")
@Data
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "authority")
    private Long authority;
}
