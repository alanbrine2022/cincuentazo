package model.player;

import model.card.Card;
import model.exceptions.InvalidMoveException;
import model.game.GameModel;

import java.util.List;

public interface IPlayer {
    List<Card> getHand();
    boolean isHuman();

    void addCard(Card card);
    void removeCard(Card card);
    void clearHand();

    boolean hasValidMove(int currentSum);
    default Card chooseCardToPlay(int currentSum){throw new UnsupportedOperationException("Solo es usado por maquinas");}
    default boolean playSelectedCard(Card card, GameModel gameModel) throws InvalidMoveException {throw new UnsupportedOperationException("Solo usado por el jugador");}

    int getHandSize();
}
