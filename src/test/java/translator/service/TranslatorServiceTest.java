package translator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import translator.Application;
import translator.domain.TranslatedText;

import static org.junit.Assert.assertEquals;

import java.io.Console;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TranslatorServiceTest {

  @Autowired
  TranslatorService translatorService;

  @Test
  public void translateTest() {
    TranslatedText translatedText = translatorService.translate("en", "es", "This is a test of translation service");
    assertEquals("Esto es una prueba de servicio de traducción", translatedText.getTranslation());
  }

  /**
   * Test to ensure de coherence between translations into different languages.
   */
  @Test
  public void translateTestCoherence() {
    TranslatedText translatedText = translatorService.translate("en", "es", "GitHub is free and simple");
    String spanish = translatedText.getTranslation();
    assertEquals("GitHub es gratis y sencillo", spanish);
    TranslatedText translatedText2 = translatorService.translate("es", "en", spanish);
    assertEquals("GitHub is free and simple", translatedText2.getTranslation());
  }

}
