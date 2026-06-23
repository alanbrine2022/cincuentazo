package utils;

import javafx.scene.image.Image;
import model.card.Card;

public class CardImageLoader {
    private static final String CARD_PATH = "../resources/cards/";

    public static Image getCardImage(Card card){
        if(card == null){return null;}

        String suit = card.getSuitString();
        String rank = card.getRankString();
        String fullPath = CARD_PATH + suit + "/" + rank + ".jpg";

        try{
            return new Image(CardImageLoader.class.getResourceAsStream(fullPath));
        }catch (Exception e){
            System.err.println("Load failed: " + fullPath);
            return null;
        }
    }

    public static Image getBackImage(){
        try {
            return new Image(CardImageLoader.class.getResourceAsStream(CARD_PATH + "back.png"));
        }catch (Exception e){
            System.err.println("Load failed: back image");
            return null;
        }
    }
}
