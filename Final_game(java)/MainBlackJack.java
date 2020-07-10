import java.util.Scanner;

public class MainBlackJack {

    //    public static void nextAction(Deck mainDeck, Deck playerDeck, Deck dealerDeck) {
    public void nextAction(Deck mainDeck, Deck playerDeck, Deck dealerDeck) {   //玩家跟莊家的遊戲資料

        boolean gameEnd = false;
        boolean playerFinish = false;
        boolean playAgain = true;
        int playerMoney = 1000;
        int playerBet = 0;

        while (playAgain)

        {
            Scanner userInput = new Scanner(System.in);
            Boolean inputComplete1 = false;

            while (!inputComplete1) {
                System.out.println(" ");
                System.out.print("Your money:$" + playerMoney + " " + "Place your bet:$");  //拿目前擁有的錢下多少堵住
                int playerBetTemp = userInput.nextInt();
                if (playerBetTemp < 0 || playerBetTemp > playerMoney) {
                    inputComplete1 = false;

                } else {
                    inputComplete1 = true;
                }
                playerBet = playerBetTemp;
            }

            dealerDeck.draw(mainDeck);
            dealerDeck.draw(mainDeck);
            System.out.println(" ");
            System.out.print("Dealer Cards: " + dealerDeck.toString2());  //莊家的牌，和其總數值

            playerDeck.draw(mainDeck);
            playerDeck.draw(mainDeck);
            System.out.println(" ");
            System.out.print("Player Cards: " + playerDeck.toString() + " Total: " +
                    playerDeck.cardsTotalValue()); //玩家的牌，和其總數值

            while (!gameEnd && !playerFinish) {
                System.out.println(" ");
                System.out.print("Would you like to Hit(1) or Stand(2)?");
                int playerAction = userInput.nextInt();

                if (playerAction == 1) {
                    playerDeck.draw(mainDeck);
                    System.out.println(" ");
                    System.out.print("Player Cards: " + playerDeck.toString() + " Total: " +
                            playerDeck.cardsTotalValue()); //玩家的牌，和其總數值

                    if (playerDeck.cardsTotalValue() < 21) {
                        gameEnd = false;
                        playerFinish = false;
                    } else if (playerDeck.cardsTotalValue() > 21) {
                        System.out.println(" ");
                        System.out.println("Bust! Game End!!"); //玩家手上的牌總數值超過21
                        gameEnd = true;
                        playerFinish = true;
                    } else {
                        playerFinish = true;
                    }
                } else if (playerAction == 2) {
                    playerFinish = true;
                } else {
                    System.out.print("Select Hit(1) or Stand(2) only!"); //選擇要牌或保持不變
                    gameEnd = false;
                    playerFinish = false;
                }
            }

            while (dealerDeck.cardsTotalValue() < 17) {                 //若莊家手上的牌總數值小於17則繼續要牌
                dealerDeck.draw(mainDeck);
            }
            System.out.println(" ");
            System.out.print("Dealer Cards: " + dealerDeck.toString() + " Total: " +
                    dealerDeck.cardsTotalValue()); //莊家的牌，和其總數值
            gameEnd = true;

            if (playerDeck.cardsTotalValue() == dealerDeck.cardsTotalValue()
                    && playerDeck.cardsTotalValue() <= 21) {  //玩家的牌跟莊家的牌總數值一樣
                System.out.println(" ");
                System.out.println("低空通過");   
            } else if (playerDeck.cardsTotalValue() <= 21 && dealerDeck.cardsTotalValue() <= 21 //玩家的牌總數值大於莊家的牌
                    && (playerDeck.cardsTotalValue() > dealerDeck.cardsTotalValue())) {
                System.out.println(" ");
                System.out.println("你過了！");
                playerMoney = playerMoney + playerBet;
            } else if (playerDeck.cardsTotalValue() <= 21 && dealerDeck.cardsTotalValue() <= 21 //玩家的牌總數值小於莊家的牌
                    && (playerDeck.cardsTotalValue() < dealerDeck.cardsTotalValue())) {
                System.out.println(" ");
                System.out.println("你被當了！");
                playerMoney = playerMoney - playerBet;
            } else if (playerDeck.cardsTotalValue() <= 21 && dealerDeck.cardsTotalValue() > 21) { //玩家的牌總數值小於等於21, 但莊家的牌總數值超過21
                System.out.println(" ");
                System.out.println("你過了！");
                playerMoney = playerMoney + playerBet;
            } else if (playerDeck.cardsTotalValue() > 21 && dealerDeck.cardsTotalValue() <= 21) { //玩家的牌總數值大於21，莊家的牌小於等於21
                System.out.println(" ");
                System.out.println("你被二一了");
                playerMoney = playerMoney - playerBet;
            } else if (playerDeck.cardsTotalValue() > 21 && dealerDeck.cardsTotalValue() > 21) {  //雙方的牌皆大於21
                System.out.println(" ");
                System.out.println("幸運！作弊沒被老師發現");
            }

            System.out.println(" ");
            System.out.println("Your money:$" + playerMoney);     //你的錢目前剩餘多少

            if (playerMoney > 0) {                                //若錢大於零則繼續玩
                Boolean inputComplete2 = false;
                while (!inputComplete2) {
                    System.out.println(" ");
                    System.out.print("Play again? Yes(1) or No(2):");  //是否繼續玩
                    int playerAction = userInput.nextInt();

                    if (playerAction == 1) {
                        playAgain = true;
                        gameEnd = false;
                        playerFinish = false;
                        inputComplete2 = true;

                    } else if (playerAction == 2) {
                        playAgain = false;
                        System.out.println(" ");
                        System.out.println("休息片刻");   
                        inputComplete2 = true;

                    } else {
                        System.out.println(" ");
                        System.out.println("Please select Yes(1) or No(2) only!");
                        inputComplete2 = false;
                    }
                }
                playerDeck.returnAllCards(mainDeck);
                dealerDeck.returnAllCards(mainDeck);
            } else {                                        //沒錢則無法繼續玩(退學)
                playAgain = false;
                System.out.println(" ");
                System.out.println("你被退學了!");
            }
        }

    }


    public static void main(String ags[]) {


        Deck mainDeck = new Deck();
        mainDeck.addAllCards();

        System.out.println("Welcome to Java Blackjack!");
        //System.out.println("Before Shuffle.");
        // System.out.println(mainDeck.toString());

        mainDeck.shuffle();

        //System.out.println("After Shuffle.");
        //System.out.println(mainDeck.toString());

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

//        nextAction(mainDeck, playerDeck, dealerDeck);
    }
}