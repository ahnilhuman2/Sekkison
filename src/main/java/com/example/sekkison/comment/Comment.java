package com.example.sekkison.comment;

import com.example.sekkison.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = " comments")
@Data
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "appoint_id")
    private Long appoint_id;

    @Column(name = "content")
    private String content;
}
