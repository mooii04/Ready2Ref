package com.salesianos.triana.DoradoMoises_Ready2Ref.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String username;

    private String email;
    private String telefono;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    @ManyToMany
    private List<Mensaje> mensajes = new ArrayList<>();

    @Builder.Default
    private boolean enabled = false;

    private String activationToken;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
