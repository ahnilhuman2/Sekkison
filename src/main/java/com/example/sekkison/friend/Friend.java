package com.example.sekkison.friend;

import com.example.sekkison.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id", nullable = false)
    private Long from_id;

    @Column(name = "to_id", nullable = false)
    private Long to_id;

    @Column(name = "is_accepted")
    @ColumnDefault(value = "false")
    private Boolean is_accepted;

    @Column(name = "memo")
    @ColumnDefault("''")
    private String memo;
}
