package com.example.sekkison.my_appoint;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "my_appoint")
@Data
public class MyAppoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "appoint_id")
    private Long appoint_id;

    @Column(name = "is_master")
    private Boolean is_master;
}
