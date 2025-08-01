package br.com.alura.ScreenSoundMusicas.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String buscarArtistas(String artistaNome) {
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_KEY"));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("Me fale sobre o artista " + artistaNome + " em um linha seguindo exemplo como '" + artistaNome + " é... ' ou '" + artistaNome + " foi... ' , se o artista não existir diga 'Artista não encontrado.'.")
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}