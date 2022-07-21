import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    @Override
    public List<Content> extractContent(String json) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);
        List<Content> contentList = new ArrayList<>();
        for (Map<String, String> attribute : attributeList) {
            String title = attribute.get("title");
            String urlImage = attribute.get("url");
            Content content = new Content(title, urlImage);
            contentList.add(content);
        }
        return contentList;
    }
}
