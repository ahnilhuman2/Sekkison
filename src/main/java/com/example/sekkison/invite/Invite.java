package com.example.sekkison.invite;

import com.example.sekkison.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "invites")
@Data
public class Invite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id", nullable = false)
    private Long from_id;

    @Column(name = "to_id", nullable = false)
    private Long to_id;

    @Column(name = "appoint_id", nullable = false)
    private Long appoint_id;
}
