import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão HTTP e buscar os tops filmes


        String url = "https://api.nasa.gov/planetary/apod?api_key=6qba0qpfNBmBvhG5wmhtfi7kUVektjglQ1klKopG&start_date=2022-06-8&end_date=2022-06-10";
        ExtratorConteudo extrator = new ExtratorConteudoNasa();
        
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // ExtratorConteudo extrator = new ExtratorConteudoIMDB();


        ClientHttp http = new ClientHttp();
        String json = http.buscaDados(url);
        
       
        // exibir e manipular dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        StickerGenerator geradora = new StickerGenerator();
        
        for (int i = 0; i < 3; i++) {
             Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            
            geradora.criar(inputStream, nomeArquivo);
           
            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

       
    }
}
