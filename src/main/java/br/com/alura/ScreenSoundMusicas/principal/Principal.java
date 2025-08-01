package br.com.alura.ScreenSoundMusicas.principal;

import br.com.alura.ScreenSoundMusicas.model.Artista;
import br.com.alura.ScreenSoundMusicas.model.Musica;
import br.com.alura.ScreenSoundMusicas.model.TipoArtista;
import br.com.alura.ScreenSoundMusicas.repository.ArtistaRepository;
import br.com.alura.ScreenSoundMusicas.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final Scanner SCANNER = new Scanner(System.in);
    private final ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void menu() {
        int opcao = 0;

        while (opcao != 9) {
            System.out.println("""
                   *** Screen Sound Músicas ***
                   
                   1- Cadastrar artistas
                   2- Cadastrar músicas
                   3- Listar músicas
                   4- Buscar músicas por artistas
                   5- Pesquisar dados sobre um artista
                   \s
                   9 - Sair
                   \s""");

            opcao = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listaMusicas();
                    break;
                case 4:
                    listaMusicasPorArtista();
                    break;
                case 5:
                    procuraInformacoesaArtista();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void cadastrarArtista() {
        System.out.println("Informe o nome desse artista:");
        String nomeArtista = SCANNER.nextLine();
        System.out.println("Informe o tipo desse artista: (solo, dupla, banda)");
        String tipoArtista = SCANNER.nextLine();
        Artista artista = new Artista(nomeArtista, TipoArtista.converteTipoArtista(tipoArtista));
        artistaRepository.save(artista);

        System.out.println("Deseja cadastrar outro artista? (S/N)");
        String cadastrarOutroArtista = SCANNER.nextLine();
        if (cadastrarOutroArtista.equalsIgnoreCase("s")) cadastrarArtista();
    }

    private void cadastrarMusica() {
        System.out.println("Deseja cadastrar uma música de que artista?");
        String artistaNome = SCANNER.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(artistaNome);
        if (artista.isPresent()) {
            System.out.println("Informe o título da música para cadastro:");
            String titulo = SCANNER.nextLine();
            System.out.println("Informe o álbum dessa música:");
            String album = SCANNER.nextLine();

            Musica musica = new Musica(titulo, album, artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado");
        }

        System.out.println("Deseja cadastrar outra música? (S/N)");
        String cadastrarOutroArtista = SCANNER.nextLine();
        if (cadastrarOutroArtista.equalsIgnoreCase("s")) cadastrarMusica();
    }

    private void listaMusicas()  {
        List<Musica> musicas = artistaRepository.buscaMusicas();
        musicas.forEach(System.out::println);
    }

    private void listaMusicasPorArtista() {
        System.out.println("Deseja consultar as música de que artista?");
        String artistaNome = SCANNER.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(artistaNome);
        if (artista.isPresent()) {
            List<Musica> musicas = artistaRepository.buscaMusicasPorArtista(artista.get());
            musicas.forEach(System.out::println);
        } else {
            System.out.println("Artista não encontrado");
        }
    }

    private void procuraInformacoesaArtista() {
        System.out.println("Informe o artista que deseja buscar:");
        String nome = SCANNER.nextLine();
        System.out.println(ConsultaChatGPT.buscarArtistas(nome).trim());
    }
}
