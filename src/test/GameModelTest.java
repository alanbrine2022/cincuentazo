package test;

import model.card.Card;
import model.game.GameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    @Test
    void testPlayCurrentMachineTurn() {
        GameModel game = new GameModel();
        game.startNewGame(3);
        game.nextTurn();

        boolean played = game.playCurrentMachineTurn();

        assertTrue(played);
    }

    @Test
    void testPlayCard() {
        GameModel game = new GameModel();
        game.startNewGame(3);
        Card card = game.getCurrentPlayer().chooseCardToPlay(0);

        boolean played = game.playCard(game.getCurrentPlayer(), card);

        assertTrue(played);
    }
}