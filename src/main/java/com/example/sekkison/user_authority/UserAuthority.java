package com.example.sekkison.user_authority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_authorities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "authority", nullable = false)
    private Long authority;
}
