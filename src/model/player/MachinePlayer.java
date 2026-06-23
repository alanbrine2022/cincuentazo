package model.player;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class MachinePlayer extends Player{
    public MachinePlayer(String name){
        super(name, false);
    }

    @Override
    public Card chooseCardToPlay(int currentSum){
        if(!hasValidMove(currentSum)){
            clearHand();
            return null;
        }

        List<Card> validCards = new ArrayList<>();

        for(Card card : getHand()){
            if(card.isValidMove(currentSum)){
                validCards.add(card);
            }
        }

        Card bestCard = validCards.get(0);
        int bestSum = currentSum + bestCard.getValue(currentSum);

        for(Card card : validCards){
            int newSum = currentSum + card.getValue(currentSum);
            if(newSum > bestSum){
                bestSum = newSum;
                bestCard = card;
            }
        }
        return bestCard;
    }
}
