package test;

import model.card.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getRankString() {
        Card card = new Card(13, 4);

        assertEquals("K", card.getRankString());
    }

    @Test
    void getSuitString(){
        Card card = new Card(13, 4);

        assertEquals("SPADES", card.getSuitString());
    }
}