package com.example.sekkison.appoint;

import com.example.sekkison.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appoints")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appoint extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "pos_x")
    private Double pos_x;

    @Column(name = "pos_y")
    private Double pos_y;

    @Column(name = "address_detail")
    @ColumnDefault("''")
    private String address_detail;

    @Column(name = "head_cnt", nullable = false)
    private Integer head_cnt;

    @Column(name = "max_cnt", nullable = false)
    private Integer max_cnt;

    @Column(name = "d_day", nullable = false)
    private LocalDateTime d_day;

    @Column(name = "is_public")
    @ColumnDefault(value = "false")
    private Boolean is_public;
}
