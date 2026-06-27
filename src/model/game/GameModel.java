package model.game;

import model.card.Card;
import model.deck.Deck;
import model.player.HumanPlayer;
import model.player.IPlayer;
import model.player.MachinePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameModel {
    private Deck deck;
    private List<IPlayer> players;
    private Stack<Card> table;
    private HumanPlayer player;
    private int currentSum;
    private int playerIndex;

    public GameModel(){
        currentSum = 0;
        playerIndex = 0;
        players = new ArrayList<>();
        table = new Stack<>();
    }

    public void startNewGame(int numBots){
        deck = new Deck();
        players.clear();
        table.clear();
        currentSum = 0;
        playerIndex = 0;

        player = new HumanPlayer("Jugador");
        deck.dealToPlayer(player);
        players.add(player);

        for(int i=1; i <= numBots; i++){
            MachinePlayer bot = new MachinePlayer("Bot " + i);
            deck.dealToPlayer(bot);
            players.add(bot);
        }

        Card firstCard = deck.drawCard();
        table.push(firstCard);
        currentSum = firstCard.getValue(currentSum);
    }

    public boolean playCard(IPlayer player, Card card){
        if(player == null || card == null){return false;}

        if(!players.contains(player)){return false;}

        if(!player.getHand().contains(card)){return false;}

        if(!card.isValidMove(currentSum)){return false;}

        player.removeCard(card);
        table.push(card);
        currentSum += card.getValue(currentSum);

        if(deck.isEmpty()){
            deck.recycleTableCards(new ArrayList<>(table.subList(0, table.size() - 1)));
        }

        if(!deck.isEmpty()) {
            player.addCard(deck.drawCard());
        }

        nextTurn();

        return true;
    }

    public boolean playCurrentMachineTurn(){
        IPlayer current = getCurrentPlayer();
        if(current.isHuman()){return false;}

        MachinePlayer bot = (MachinePlayer) current;
        Card chosen = bot.chooseCardToPlay(currentSum);

        if(chosen != null){
            return playCard(bot, chosen);
        }else{
            deck.addCards(bot.getHand());
            players.remove(bot);
            return false;
        }
    }

    public IPlayer getCurrentPlayer(){
        if(players.isEmpty()){return null;}

        return players.get(playerIndex % players.size());
    }

    public int getCurrentSum(){return currentSum;}

    public void nextTurn(){
        if(playerIndex == players.size() - 1){playerIndex = 0;}
        else{playerIndex++;}
    }

    public boolean isGameOver(){
        if(!players.contains(player)){return true;}

        return players.size() < 2;
    }

    public Card getTopCard(){
        return table.peek();
    }

    public IPlayer getHumanPlayer(){return players.get(0);}

    public List<IPlayer> getPlayers(){return players;}
}
