import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    //String spade = "\u2660";
    //String heart = "\u2665";
    //String club = "\u2663";
    //String diamond = "\u2666";
    //String cardSuit[] = {spade,heart,club,diamond};
    String cardValue[] = {"A","2","3","4","5","6","7","8","9","10","J","Q","K",}; //每一花色總共有13張牌

    public Deck(){
        this.cards = new ArrayList<Card>();
    }
    //********************************************************************************************
    public void addAllCards(){
            for(int i=0; i<4; i++){
                for(int j=0; j<13; j++){
                    this.cards.add(new Card( cardValue[j]));
                }
            }
    }
    //********************************************************************************************
    public void shuffle(){
        Collections.shuffle(this.cards);
    }
    //********************************************************************************************
    public Card getCard(int i){     //要牌
        return this.cards.get(i);
    }
    //********************************************************************************************
    public void removeCard(int i){  //刪牌
         this.cards.remove(i);
    }
    //********************************************************************************************
    public void addCard(Card addCard){  //加牌
        this.cards.add(addCard);
    }
    //********************************************************************************************
    public void draw(Deck sourceDeck){   //從發牌人要牌
        this.cards.add(sourceDeck.getCard(0));
        sourceDeck.removeCard(0);
    }
    //********************************************************************************************
    public int cardsTotalValue(){   //計算牌的總數值
       int cardsTotalValue = 0;
       int aces = 0;
        for(Card tempCard: this.cards ){
           switch(tempCard.getValue()){

               case "2": cardsTotalValue +=2; break;
               case "3": cardsTotalValue +=3; break;
               case "4": cardsTotalValue +=4; break;
               case "5": cardsTotalValue +=5; break;
               case "6": cardsTotalValue +=6; break;
               case "7": cardsTotalValue +=7; break;
               case "8": cardsTotalValue +=8; break;
               case "9": cardsTotalValue +=9; break;
               case "10": cardsTotalValue +=10; break;
               case "J": cardsTotalValue +=10; break;
               case "Q": cardsTotalValue +=10; break;
               case "K": cardsTotalValue +=10; break;
               case "A": aces +=1; break;
           }
        for (int i=0; i< aces; i++){
            if (cardsTotalValue>10){
                cardsTotalValue +=1;
            }
            else{
                cardsTotalValue +=11;
            }
        }
        }
        return cardsTotalValue;
    }
    //********************************************************************************************
    public void returnAllCards(Deck sourceDeck) { //把牌收回來
        int playingDeckSize = this.cards.size(); 
        for(int i=0; i<playingDeckSize; i++){
            sourceDeck.addCard(this.getCard(i));    
        }
        for(int i=0; i<playingDeckSize; i++){
            this.removeCard(0);
        }
    }
    //********************************************************************************************
    public String toString(){          //(玩家)牌的數值顯示 
        String cardsOutput = "";
        int i=1;
        for(Card tempCard: this.cards ){
            if (i==14 || i==27 || i==40) {
                cardsOutput += "\n" + " "+ tempCard.toString() + ", ";
            } else{
                cardsOutput += " "+ tempCard.toString()+ ", ";
            }
            i+=1;
        }
        return cardsOutput ;
    }
    //********************************************************************************************
    public String toString2(){         //(莊家)牌的數值顯示    
        String cardsOutput = "";
        cardsOutput += " "+ this.cards.get(0) + ", ?";
        return cardsOutput ;
    }

}
