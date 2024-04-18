// Kris McFarland
// lab 4 card Game
// J Ramsey

import java.util.*;

public class BlackJackGame {
   public static void main(String[] args) {  
      System.out.println("Welcome to the game of BlackJack");
      System.out.println("You will be asked to hit - h key or stay - s key");
      System.out.println("Aces worth 11 points unless the value becomes more than 21");
      System.out.println("Then the ace will be worth 1 point");
      Scanner input = new Scanner(System.in);
      DeckOfCards blackJack = new DeckOfCards();   
      Queue<String> playersHand = new LinkedList<>();
      Queue<String> dealersHand = new LinkedList<>();
      
      // shuffle cards and deal the first hand
      blackJack.shuffle();  
      for (int i = 0; i < 2; i++) {
         playersHand.add(blackJack.dealCard().toString());
         dealersHand.add(blackJack.dealCard().toString());
      }
     
     // show appropirate cards for the first round
     // dealer shows one card, other is face down     
     System.out.println("YOUR HAND");
     showCards(playersHand); 
     System.out.println(sumCards(playersHand) + "\n");
     System.out.println("DEALER");
     System.out.println(dealersHand.peek());
     System.out.println(cardValue(dealersHand.peek()) + "\n");
      
     // repeat the main parts of blackjack unless
     // there is a bust or blackjack 
     boolean runGame = true;
      while (runGame) {
      
         System.out.println("Hit(h) or Stay(s):");
         String choice = input.next();
         
         // choice is hit, card is dealt
         // else choice is stay and dealers cards are less --> win
         // else choice is stay and dealers cards are more --> loose   
         if(choice.equals("h")) {
            playersHand.add(blackJack.dealCard().toString());
            showCards(playersHand);
            System.out.println(sumCards(playersHand) + "\n");
         } else if (choice.equals("s") 
                    && sumCards(playersHand) > sumCards(dealersHand) 
                    && sumCards(dealersHand) > 17) {
            System.out.print("You Win");
            break;
         } else if (choice.equals("s") 
                    && sumCards(dealersHand) > sumCards(playersHand)
                    && dealersHand.size() > 2) {    
            System.out.println("You lost");
            break;
         }
            
         runGame = checkCards(playersHand);
         // if the above choices did not "break" keeps
         // playing the game for the dealers turn
         if (runGame) {
            System.out.println("DEALER");
            showCards(dealersHand);
            System.out.println(sumCards(dealersHand));
            runGame = checkCards(dealersHand);
            System.out.println();
            // dealer hits only when card is under 17 -> Typical BlackJack rule.
            if(sumCards(dealersHand) < 17) {
               System.out.println(" ..Dealer Hits");
               dealersHand.add(blackJack.dealCard().toString());
               showCards(dealersHand);
               System.out.println(sumCards(dealersHand));
               runGame = checkCards(dealersHand);
            }
         }      
     }       
   } // end of main
   
   // gets the face of the card and returns the value
   // default for ace is 11 but is handled in the sumCards method
   public static int cardValue(String card) {
      int stopIndex = card.indexOf(" ");
      card = card.substring(0, stopIndex);
      switch(card) {
         case "Ace"   : return 11;
         case "Deuce" : return 2;
         case "Three" : return 3;
         case "Four"  : return 4;
         case "Five"  : return 5;
         case "Six"   : return 6;
         case "Seven" : return 7;
         case "eight" : return 8;
         case "nine"  : return 9; 
         default      : return 10;
      }
   }
   
   // iterates through all cards in the players or dealers hand
   // and sums the cards. handles the ace by making it into a 
   // value of 1 and re-adding back to sum if the total is over 21. 
   public static int sumCards(Queue<String> cardsInHand) {
      int sum = 0;
      int countAces = 0;
      for (int i = 0; i < cardsInHand.size(); i++) {
         String card = cardsInHand.remove(); 
         cardsInHand.add(card);
         if (cardValue(card) == 11) {
            countAces++;
         }
         sum += cardValue(card);
      }
      if(sum > 21 && countAces > 0) {
         sum -= 11 * countAces;
         sum += countAces;
      }
      return sum;
   }
  
   // iterates through cards in hand and outputs all to console
   public static void showCards(Queue<String> cardsInHand) {
      for (int i = 0; i < cardsInHand.size(); i++) {
         String card = cardsInHand.remove();
         System.out.println(card);
         cardsInHand.add(card);
      }
   }
   
   // checks to see if the hand is a bust or blackjack
   // and outputs to the console
   // also returns boolean to stop the game
   public static boolean checkCards(Queue<String> cardsInHand) {
         boolean bool = true;
      if (sumCards(cardsInHand) > 21) {
         System.out.println("Bust!");
         bool = false;
      } else if (sumCards(cardsInHand) == 21) {
        System.out.println("BlackJack!");
        bool = false;
      } else {}
      return bool;
   }
         
} // end of BlackJack class