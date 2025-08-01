package br.com.alura.ScreenSoundMusicas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String album;
    @ManyToOne
    private Artista artista;

    public Musica() {
    }

    public Musica(String titulo, String album, Artista artista) {
        if (titulo.isBlank() || album.isBlank()) throw new IllegalArgumentException("Campos em branco, preencha todos os campos");

        this.titulo = titulo;
        this.album = album;
        this.artista = artista;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAlbum() {
        return album;
    }

    public Artista getArtista() {
        return artista;
    }

    @Override
    public String toString() {
        return "Música: '" + titulo + "', álbum: '" + album + "', artista: " + artista.getNome();
    }
}
