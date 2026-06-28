package test;

import model.card.Card;
import model.exceptions.InvalidMoveException;
import model.game.GameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    @Test
    void testPlayCurrentMachineTurn() throws InvalidMoveException {
        GameModel game = new GameModel();
        game.startNewGame(3);
        game.nextTurn();

        boolean played = game.playCurrentMachineTurn();

        assertTrue(played);
    }

    @Test
    void testPlayCard() throws InvalidMoveException {
        GameModel game = new GameModel();
        game.startNewGame(3);
        Card card = game.getCurrentPlayer().chooseCardToPlay(0);

        boolean played = game.playCard(game.getCurrentPlayer(), card);

        assertTrue(played);
    }

    @Test
    void testGetHumanPlayer() {
        GameModel game = new GameModel();
        game.startNewGame(3);

        assertTrue(game.getHumanPlayer().isHuman());
    }
}