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
        
        // fazer uma conexão HTTP e buscar os tops filmes
        String urlTopMovies = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //String urlTopTVs = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        //String urlMostPopularMovies = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        //String urlMostPopularTVs = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        
        URI endereço = URI.create(urlTopMovies);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);
        
        // extrair dados (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());

        // exibir e manipular dados

        StickerGenerator geradora = new StickerGenerator();
        for (Map<String,String> filme : listaDeFilmes) {

            String titulo = filme.get("title");
            String URLimage = filme.get("image");
            InputStream inputStream = new URL(URLimage).openStream();

            String nomeArquivo = titulo + ".png";
            
            geradora.criar(inputStream, nomeArquivo);
           
            System.out.println(titulo);
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        
        }
    }
}
