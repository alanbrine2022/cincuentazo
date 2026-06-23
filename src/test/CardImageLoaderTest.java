package test;

import javafx.scene.image.Image;
import model.card.Card;
import org.testng.annotations.Test;
import utils.CardImageLoader;

import static org.junit.jupiter.api.Assertions.*;

class CardImageLoaderTest {
    Card card = new Card(1, 1);

    @org.junit.jupiter.api.Test
    void testGetCardImage() {
        Card card = new Card(10, 1);

        Image image = CardImageLoader.getCardImage(card);

        assertNotNull(image);
    }

    @org.junit.jupiter.api.Test
     void testGetBackImage() {
        Image image = CardImageLoader.getBackImage();

        assertNotNull(image);
    }
}