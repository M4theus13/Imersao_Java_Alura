import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{
    public List<Conteudo> extraiConteudos(String json){
         // extrair dados (titulo, poster, classificação)
         JsonParser parser = new JsonParser();
         List<Map<String, String>> listaDeAtributos = parser.parse(json);

         List<Conteudo> conteudos = new ArrayList<>(); 
         
         //popular a lista de conteudos
         for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImage = atributos.get("url");
            Conteudo conteudo = new Conteudo(titulo, urlImage);

            conteudos.add(conteudo);
            
         }
         return conteudos;
    }
}
