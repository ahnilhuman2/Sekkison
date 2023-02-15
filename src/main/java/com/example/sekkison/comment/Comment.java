package com.example.sekkison.comment;

import com.example.sekkison.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = " comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "appoint_id", nullable = false)
    private Long appoint_id;

    @Column(name = "content", nullable = false)
    private String content;
}
