package com.example.sekkison.friend;

import com.example.sekkison.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@Data
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id")
    private Long from_id;

    @Column(name = "to_id")
    private Long to_id;

    @Column(name = "is_accepted")
    private Boolean is_accepted;

    @Column(name = "memo")
    private String memo;
}
