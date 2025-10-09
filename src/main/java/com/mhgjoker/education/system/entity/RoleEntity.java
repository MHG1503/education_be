package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
        this.users = new HashSet<>();
    }
}
