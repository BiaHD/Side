import java.util.Scanner;

public class MainBlackJack {

    //    public static void nextAction(Deck mainDeck, Deck playerDeck, Deck dealerDeck) {
    public void nextAction(Deck mainDeck, Deck playerDeck, Deck dealerDeck) {   //���a����a���C�����

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
                System.out.print("Your money:$" + playerMoney + " " + "Place your bet:$");  //���ثe�֦������U�h�ְ���
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
            System.out.print("Dealer Cards: " + dealerDeck.toString2());  //���a���P�A�M���`�ƭ�

            playerDeck.draw(mainDeck);
            playerDeck.draw(mainDeck);
            System.out.println(" ");
            System.out.print("Player Cards: " + playerDeck.toString() + " Total: " +
                    playerDeck.cardsTotalValue()); //���a���P�A�M���`�ƭ�

            while (!gameEnd && !playerFinish) {
                System.out.println(" ");
                System.out.print("Would you like to Hit(1) or Stand(2)?");
                int playerAction = userInput.nextInt();

                if (playerAction == 1) {
                    playerDeck.draw(mainDeck);
                    System.out.println(" ");
                    System.out.print("Player Cards: " + playerDeck.toString() + " Total: " +
                            playerDeck.cardsTotalValue()); //���a���P�A�M���`�ƭ�

                    if (playerDeck.cardsTotalValue() < 21) {
                        gameEnd = false;
                        playerFinish = false;
                    } else if (playerDeck.cardsTotalValue() > 21) {
                        System.out.println(" ");
                        System.out.println("Bust! Game End!!"); //���a��W���P�`�ƭȶW�L21
                        gameEnd = true;
                        playerFinish = true;
                    } else {
                        playerFinish = true;
                    }
                } else if (playerAction == 2) {
                    playerFinish = true;
                } else {
                    System.out.print("Select Hit(1) or Stand(2) only!"); //��ܭn�P�ΫO������
                    gameEnd = false;
                    playerFinish = false;
                }
            }

            while (dealerDeck.cardsTotalValue() < 17) {                 //�Y���a��W���P�`�ƭȤp��17�h�~��n�P
                dealerDeck.draw(mainDeck);
            }
            System.out.println(" ");
            System.out.print("Dealer Cards: " + dealerDeck.toString() + " Total: " +
                    dealerDeck.cardsTotalValue()); //���a���P�A�M���`�ƭ�
            gameEnd = true;

            if (playerDeck.cardsTotalValue() == dealerDeck.cardsTotalValue()
                    && playerDeck.cardsTotalValue() <= 21) {  //���a���P����a���P�`�ƭȤ@��
                System.out.println(" ");
                System.out.println("�C�ųq�L");   
            } else if (playerDeck.cardsTotalValue() <= 21 && dealerDeck.cardsTotalValue() <= 21 //���a���P�`�ƭȤj����a���P
                    && (playerDeck.cardsTotalValue() > dealerDeck.cardsTotalValue())) {
                System.out.println(" ");
                System.out.println("�A�L�F�I");
                playerMoney = playerMoney + playerBet;
            } else if (playerDeck.cardsTotalValue() <= 21 && dealerDeck.cardsTotalValue() <= 21 //���a���P�`�ƭȤp����a���P
                    && (playerDeck.cardsTotalValue() < dealerDeck.cardsTotalValue())) {
                System.out.println(" ");
                System.out.println("�A�Q��F�I");
                playerMoney = playerMoney - playerBet;
            } else if (playerDeck.cardsTotalValue() <= 21 && dealerDeck.cardsTotalValue() > 21) { //���a���P�`�ƭȤp�󵥩�21, �����a���P�`�ƭȶW�L21
                System.out.println(" ");
                System.out.println("�A�L�F�I");
                playerMoney = playerMoney + playerBet;
            } else if (playerDeck.cardsTotalValue() > 21 && dealerDeck.cardsTotalValue() <= 21) { //���a���P�`�ƭȤj��21�A���a���P�p�󵥩�21
                System.out.println(" ");
                System.out.println("�A�Q�G�@�F");
                playerMoney = playerMoney - playerBet;
            } else if (playerDeck.cardsTotalValue() > 21 && dealerDeck.cardsTotalValue() > 21) {  //���誺�P�Ҥj��21
                System.out.println(" ");
                System.out.println("���B�I�@���S�Q�Ѯv�o�{");
            }

            System.out.println(" ");
            System.out.println("Your money:$" + playerMoney);     //�A�����ثe�Ѿl�h��

            if (playerMoney > 0) {                                //�Y���j��s�h�~��
                Boolean inputComplete2 = false;
                while (!inputComplete2) {
                    System.out.println(" ");
                    System.out.print("Play again? Yes(1) or No(2):");  //�O�_�~��
                    int playerAction = userInput.nextInt();

                    if (playerAction == 1) {
                        playAgain = true;
                        gameEnd = false;
                        playerFinish = false;
                        inputComplete2 = true;

                    } else if (playerAction == 2) {
                        playAgain = false;
                        System.out.println(" ");
                        System.out.println("�𮧤���");   
                        inputComplete2 = true;

                    } else {
                        System.out.println(" ");
                        System.out.println("Please select Yes(1) or No(2) only!");
                        inputComplete2 = false;
                    }
                }
                playerDeck.returnAllCards(mainDeck);
                dealerDeck.returnAllCards(mainDeck);
            } else {                                        //�S���h�L�k�~��(�h��)
                playAgain = false;
                System.out.println(" ");
                System.out.println("�A�Q�h�ǤF!");
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