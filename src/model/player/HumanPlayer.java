package model.player;

import model.game.GameModel;
import model.card.Card;

public class HumanPlayer extends Player{
    public HumanPlayer(String name){
        super(name, true);
    }

    @Override
    public Card chooseCardToPlay(int currentSum){
        return null;
    }
}
