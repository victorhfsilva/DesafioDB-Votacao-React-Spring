package com.db.dbpautasbackend.model;

import com.db.dbpautasbackend.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "pautas")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String resumo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
        name = "pautas_eleitores",
        joinColumns = @JoinColumn(name = "pauta_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> eleitores;

    @Column(nullable = false)
    @Builder.Default
    private boolean aberta = false;

    private LocalDateTime abertoAs;

    @Column(nullable = false)
    @Builder.Default
    private int votosSim = 0;

    @Column(nullable = false)
    @Builder.Default
    private int votosNao = 0;

    @Column(nullable = false)
    @Builder.Default
    private int tempoDeSessaoEmMinutos = 1;

}
