import com.google.inject.Inject;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class URLDocumentSource implements SourceFetcher {

  private String uri;

  @Inject
  public URLDocumentSource(@Named("UrlToCheck") String uri) {
    this.uri = uri;
  }

  public String getContent() throws IOException {

    URL url=new URL(uri);
    URLConnection connection=url.openConnection();

    StringBuilder contentBuffer=new StringBuilder();

    try (InputStream input=connection.getInputStream()) {
      int c;
      while ((c=input.read()) >= 0) {
        contentBuffer.append((char) c);
      }
    }

    return contentBuffer.toString();
  }

}
