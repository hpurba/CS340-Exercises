import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.mockito.Mockito;

import javax.inject.Named;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class TestModule extends AbstractModule {
  @Override
  protected void configure() {
    // Mock Dictionary
    Dictionary dictionary = Mockito.mock(Dictionary.class);
    when(dictionary.isValidWord("Hello")).thenReturn(true);     // Valid Word
    when(dictionary.isValidWord("Worldx")).thenReturn(false);   // InValid Word
    when(dictionary.isValidWord("funtion")).thenReturn(false);  // InValid Word

    // Mock Word Extractor
    List<String> words = new ArrayList<String>();
    String word1 = "Hello";
    String word2 = "Worldx";
    String word3 = "funtion";
    words.add(word1);
    words.add(word2);
    words.add(word3);
    WordExtractor wordExtractor = Mockito.mock(WordExtractor.class);
    when(wordExtractor.extract("Hello Worldx funtion")).thenReturn(words);

    // Mock URLDocumentSource
    URLDocumentSource urlDocumentSource = Mockito.mock(URLDocumentSource.class);
    try {
      when(urlDocumentSource.getContent()).thenReturn("Hello Worldx funtion");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // BIND
    bind(DictionarySource.class).toInstance(dictionary);
    bind(Extractor.class).toInstance(wordExtractor);
    bind(SourceFetcher.class).toInstance(urlDocumentSource);
  }


  @Provides
  @Named("DictionaryFilePath")
  public String dictionFilePath() { return "src/main/dict.txt";}

  @Provides
  @Named("UrlToCheck")
  public String urlToCheck() { return "https://pastebin.com/raw/t6AZ5kx3";}
}