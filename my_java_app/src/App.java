import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Requisição dos 250 melhores filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // Extrair os dados dos filmes (Nome, Poster, Classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibindo Filmes
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: " + filme.get("fullTitle"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("Nota: " + filme.get("imDbRating"));
            System.out.println();
        }
    }
}
