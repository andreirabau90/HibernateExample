package com.andersen.entity;

import com.andersen.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private UserRole userRole;
    @ManyToMany(
            mappedBy = "roleSet",
            fetch = FetchType.LAZY)
    Set<Person> personSet = new HashSet<>();

    public Roles(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", userRole=" + userRole +
                '}';
    }
}
