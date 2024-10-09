package br.edu.fatecpg.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConsomeAPI {

    public static Set<String> coletarEmails() throws IOException, InterruptedException {
        // URL da API
        String url = "https://jsonplaceholder.typicode.com/comments";

        // criando instância de HttpClient , HttpRequest e HttpResponse
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // verificando statusCode ( 200 é OK!! )
        if (response.statusCode() == 200) {
            String result = response.body();

            // Transformando a String JSON em um array de objetos JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode emailArray = mapper.readTree(result);

            // Usando Stream e função lambda para coletar os e-mails
            return StreamSupport.stream(emailArray.spliterator(), false) // Converte Json -> stream
                    .map(campo -> campo.get("email").asText()) // Extrai o campo "email" de cada comentário
                    .collect(Collectors.toSet()); // Coleta em um Set para remover emails duplicados
        } else {
            throw new IOException( "erro ao acessar a API. Status code: " + response.statusCode());
        }
    }
}
