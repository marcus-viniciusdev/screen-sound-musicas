package br.com.alura.ScreenSoundMusicas.repository;

import br.com.alura.ScreenSoundMusicas.model.Artista;
import br.com.alura.ScreenSoundMusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m ORDER BY m.titulo")
    List<Musica> buscaMusicas();

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE m.artista = :artista")
    List<Musica> buscaMusicasPorArtista(Artista artista);
}