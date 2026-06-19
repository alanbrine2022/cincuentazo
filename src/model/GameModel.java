package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameModel {
    private Deck deck;
    private List<IPlayer> players = new ArrayList<>();
    private Stack<Card> tablePile = new Stack<>();
    private int currentSum;
    private int playerIndex;

    public GameModel(){}

    public void startNewGame(int numBots){
        deck = new Deck();
        players.clear();
        tablePile.clear();
        currentSum = 0;
        playerIndex = 0;

        HumanPlayer player = new HumanPlayer("Jugador");
        deck.dealToPlayer(player);
        players.add(player);

        for(int i=1; i <= numBots; i++){
            MachinePlayer bot = new MachinePlayer("Bot " + i);
            deck.dealToPlayer(bot);
            players.add(bot);
        }

        Card firstCard = deck.drawCard();
        tablePile.push(firstCard);
        currentSum = firstCard.getValue();
    }

    public boolean playCard(IPlayer player, Card card){
        if(player == null || card == null){return false;}

        if(!players.contains(player)){return false;}

        if(!player.getHand().contains(card)){return false;}

        if(!card.isValidMove(currentSum)){return false;}

        player.removeCard(card);
        tablePile.push(card);
        currentSum += card.getValue();

        if(deck.isEmpty()){
            deck.recycleTableCards(new ArrayList<>(tablePile.subList(0, tablePile.size() - 1)));
        }

        player.addCard(deck.drawCard());

        return true;
    }

    public void playCurrentMachineTurn(){
        IPlayer current = getCurrentPlayer();
        if(current.isHuman()){return;}

        MachinePlayer bot = (MachinePlayer) current;
        Card chosen = bot.chooseCardToPlay(currentSum);

        if(chosen != null){playCard(bot, chosen);}
        else{
            deck.addCards(bot.getHand());
            players.remove(bot);
        }
    }

    public IPlayer getCurrentPlayer(){
        if(players.isEmpty()){return null;}

        return players.get(playerIndex % players.size());
    }

    public void nextTurn(){
        if(playerIndex == players.size() - 1){playerIndex = 0;}
        else{playerIndex++;}
    }
}
