// Card.java
// Card class represents a playing Card 

public class Card {
    private final String face; // face of card ("Ace", "Deuce", ...) 
    private final String suit; // suit of card ("Hearts", Diamonds", etc)
    
    // two-argument constructor initializes card's face and suit 
    public Card(String cardFace, String cardSuit) {
        this.face = cardFace; // initialize face of card
        this.suit = cardSuit; // initialize suit of card 
    } // end of Card Constructor 
    
    // return String representation of Card
    public String toString() {
        return face + " of " + suit; 
    } // end of toString method
    
    public int getValue() {
      
      switch(face) {
         case "Ace"   : return 1;
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
     
} // end class Card 