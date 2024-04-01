package com.db.dbpautasbackend.model;

import com.db.dbpautasbackend.enums.Papel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    private boolean ativo;
    private String criadoPor;
    private LocalDateTime criadoAs;
    private String desativadoPor;
    private LocalDateTime desativadoAs;
    private LocalDateTime atualizadoAs;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String sobrenome;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = false, length = 30)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Papel papel;
}
