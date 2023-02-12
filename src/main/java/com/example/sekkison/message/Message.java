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

    @Column(name = "from_id")
    private Long from_id;

    @Column(name = "to_id")
    private Long to_id;

    @Column(name = "content")
    private String content;
}
