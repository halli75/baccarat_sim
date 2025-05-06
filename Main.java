import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        float bankroll = 10000;
        float bets = 1;
        for(int i = 0; i<50000; i++){

            List<String> deck = createDeck();
            Collections.shuffle(deck, random);
    
            List<String> playerHand = new ArrayList<>();
            List<String> bankerHand = new ArrayList<>();
            
            dealCards(deck, playerHand, bankerHand);
    
            int playerValue = calculateHandValue(playerHand);
            int bankerValue = calculateHandValue(bankerHand);
    
            //System.out.println("Player's hand: " + playerHand + " (Value: " + playerValue + ")");
            //System.out.println("Banker's hand: " + bankerHand + " (Value: " + bankerValue + ")");
    
            String result = determineWinner(playerValue, bankerValue);
            if(result.equals("Player wins!")){
                bankroll += bets;
                bets = bankroll/10000;
            } else if(result.equals("Banker wins!")){
                bankroll -= bets;
                bets = bets * 2;
            }
             System.out.println(bankroll);
             if(bankroll<0){
                 System.out.println(i);
                 break;
             }
        }
        scanner.close();
    }

    private static List<String> createDeck() {
        List<String> deck = new ArrayList<>();
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck.add(rank + " of " + suit);
            }
        }
        return deck;
    }

    private static void dealCards(List<String> deck, List<String> playerHand, List<String> bankerHand) {
        playerHand.add(deck.remove(0));
        bankerHand.add(deck.remove(0));
        playerHand.add(deck.remove(0));
        bankerHand.add(deck.remove(0));
    }

    private static int calculateHandValue(List<String> hand) {
        int value = 0;
        for (String card : hand) {
            String rank = card.split(" ")[0];
            switch (rank) {
                case "Jack":
                case "Queen":
                case "King":
                case "10":
                    value += 0;
                    break;
                case "Ace":
                    value += 1;
                    break;
                default:
                    value += Integer.parseInt(rank);
            }
        }
         return value % 10;
    }

    private static String determineWinner(int playerValue, int bankerValue) {
        if (playerValue > bankerValue) {
            return "Player wins!";
        } else if (bankerValue > playerValue) {
            return "Banker wins!";
        } else {
            return "It's a tie!";
        }
    }
}