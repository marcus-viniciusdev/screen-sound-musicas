package br.com.alura.ScreenSoundMusicas.model;

import br.com.alura.ScreenSoundMusicas.service.ConsultaChatGPT;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;
    @Enumerated(value = EnumType.STRING)
    private TipoArtista tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;

    public Artista() {
    }

    public Artista(String nome, TipoArtista tipo) {
        if (nome.isBlank()) throw new IllegalArgumentException("Campos em branco, preencha todos os campos");

        this.nome = nome;
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
}
