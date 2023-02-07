package com.example.sekkison.message;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id")
    private Long from_id;

    @Column(name = "to_id")
    private Long to_id;

    @Column(name = "content")
    private String content;

    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
