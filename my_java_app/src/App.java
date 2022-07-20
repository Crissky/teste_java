import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Requisição dos 250 melhores filmes
        // "https://api.mocki.io/v2/549a5d8b/Top250Movies"
        // "https://api.mocki.io/v2/549a5d8b/MostPopularMovies"
        // "https://api.mocki.io/v2/549a5d8b/MostPopularTVs"
        // "https://api.mocki.io/v2/549a5d8b/Top250TVs"
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair os dados dos filmes (Nome, Poster, Classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibindo Filmes
        StickerGenerator stickerGenerator = new StickerGenerator();
        for (Map<String, String> filme : listaDeFilmes) {
            String title = filme.get("fullTitle");
            String urlImage = filme.get("image");
            String rating = filme.get("imDbRating");
            String fileName = "saida/" + title + ".png";
            fileName = fileName.replaceAll("[\\:*?\"<>|]", "");

            System.out.println("Título: " + title);
            System.out.println("Poster: " + urlImage);
            System.out.println("Nota: " + rating);
            System.out.println("Salvo em: " + fileName);
            System.out.println();

            InputStream inputStream = new URL(urlImage).openStream();
            stickerGenerator.create(inputStream, fileName);
        }
        System.out.println("ACABOU!!!");
    }
}
