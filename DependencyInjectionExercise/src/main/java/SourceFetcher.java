import java.io.IOException;

public interface SourceFetcher {
    String getContent(String uri) throws IOException;
}
