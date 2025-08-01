package br.com.alura.ScreenSoundMusicas.model;

public enum TipoArtista {
    SOLO,
    DUPLA,
    BANDA;

    public static TipoArtista converteTipoArtista(String texto) {
        for (TipoArtista tipoArtista : TipoArtista.values()) {
            if (tipoArtista.name().toLowerCase().contains(texto.toLowerCase())) return tipoArtista;
        }

        throw new IllegalArgumentException("Tipo de artista inválido.");
    }
}
