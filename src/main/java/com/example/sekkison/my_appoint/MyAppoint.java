package com.example.sekkison.my_appoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "my_appoints")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyAppoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "appoint_id", nullable = false)
    private Long appointId;

    @Column(name = "is_master")
    @ColumnDefault(value = "false")
    private Boolean isMaster;
}
