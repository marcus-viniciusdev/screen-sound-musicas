package br.com.alura.ScreenSoundMusicas;

import br.com.alura.ScreenSoundMusicas.principal.Principal;
import br.com.alura.ScreenSoundMusicas.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSoundMusicasApplication implements CommandLineRunner {
	@Autowired
	ArtistaRepository artistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenSoundMusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository);
		principal.menu();
	}
}
