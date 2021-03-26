import java.io.IOException;

public interface SourceFetcher {
    String getContent() throws IOException;
}
