import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Requisição dos 250 melhores filmes
        // "https://api.mocki.io/v2/549a5d8b/Top250Movies"
        // "https://api.mocki.io/v2/549a5d8b/MostPopularMovies"
        // "https://api.mocki.io/v2/549a5d8b/MostPopularTVs"
        // "https://api.mocki.io/v2/549a5d8b/Top250TVs"
        // "https://api.mocki.io/v2/549a5d8b/NASA-APOD"

        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        ContentExtractor contentExtractor = new ImdbContentExtractor();

        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        // ContentExtractor contentExtractor = new NasaContentExtractor();

        MyHttpClient myHttpClient = new MyHttpClient();
        String json = myHttpClient.searchData(url);
        List<Content> contentList = contentExtractor.extractContent(json);

        // Exibindo Filmes
        StickerGenerator stickerGenerator = new StickerGenerator();
        for (Content content : contentList) {
            String title = content.getTitle();
            String urlImage = content.getUrlImage();
            String fileName = "saida/" + title + ".png";
            fileName = fileName.replaceAll("[\\:*?\"<>|]", "");

            System.out.println("Título: " + title);
            System.out.println("Poster: " + urlImage);
            System.out.println("Salvo em: " + fileName);
            System.out.println();

            InputStream inputStream = new URL(urlImage).openStream();
            stickerGenerator.create(inputStream, fileName);
        }
        System.out.println("ACABOU!!!");
    }
}
