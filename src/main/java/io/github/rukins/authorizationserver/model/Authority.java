package io.github.rukins.authorizationserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @Column(unique = true, updatable = false, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> user;

    public Authority(String name) {
        this.name = name;
    }
}
