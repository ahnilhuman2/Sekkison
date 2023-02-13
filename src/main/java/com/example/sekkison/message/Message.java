package com.example.sekkison.message;

import com.example.sekkison.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id", nullable = false)
    private Long from_id;

    @Column(name = "to_id", nullable = false)
    private Long to_id;

    @Column(name = "content", nullable = false)
    private String content;
}
