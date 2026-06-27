package model.player;

import model.game.GameModel;
import model.card.Card;

public  class HumanPlayer extends Player{
    public HumanPlayer(String name){
        super(name, true);
    }

    public boolean playSelectedCard(Card card, GameModel gameModel){
        if(card == null && !getHand().contains(card)){
            return false;
        }
        return gameModel.playCard(this, card);
    }
}
