import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;


public class GuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DictionarySource.class).to(Dictionary.class);
    bind(Extractor.class).to(WordExtractor.class);
    bind(SourceFetcher.class).to(URLDocumentSource.class);
  }

  @Provides
  @Named("DictionaryFilePath")
  public String dictionFilePath() { return "src/main/dict.txt";}

  @Provides
  @Named("UrlToCheck")
  public String urlToCheck() { return "https://pastebin.com/raw/t6AZ5kx3";}
}