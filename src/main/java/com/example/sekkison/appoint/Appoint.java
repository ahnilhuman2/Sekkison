package com.example.sekkison.appoint;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appoints")
@Data
public class Appoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "pos_x")
    private Double pos_x;

    @Column(name = "pos_y")
    private Double pos_y;

    @Column(name = "address_detail")
    private String address_detail;

    @Column(name = "head_cnt")
    private Integer head_cnt;

    @Column(name = "max_cnt")
    private Integer max_cnt;

    @Column(name = "d_day")
    private LocalDateTime d_day;

    @Column(name = "is_public")
    private Boolean is_public;

    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;


}
