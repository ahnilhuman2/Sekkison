package com.example.sekkison.appoint;

import com.example.sekkison.common.BaseEntity;
import com.example.sekkison.common.C;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "type")
    @ColumnDefault("0")
    private C.appointType type;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "pos_x")
    private Double posX;

    @Column(name = "pos_y")
    private Double posY;

    @Column(name = "address_detail")
    @ColumnDefault("''")
    private String addressDetail;

    @Column(name = "head_cnt", nullable = false)
    private Integer headCnt;

    @Column(name = "max_cnt", nullable = false)
    private Integer maxCnt;

    @Column(name = "d_day", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime dDay;

    @Column(name = "is_public")
    @ColumnDefault(value = "false")
    private Boolean isPublic;

    @Column(name = "is_recruit")
    @ColumnDefault(value = "true")
    private Boolean isRecruit;

    @Transient
    private String memo;
}
