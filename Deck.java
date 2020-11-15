import java.util.ArrayList;
import java.util.Collections;

/**
 * Class Deck contains 52 Cards and process reshuffling and produce Cards to add to players' Hands.
 * @author Anh Ta
 */
public class Deck { 
   
   /**
   * Deck constructor, initializing Deck with 52 cards (standard style) by calling initializeFullDeck
   */  
   public Deck()
   {
      //initialize the card with full 52 cards
      initializeFullDeck();
      //shuffle the deck
      shuffleCard();
   }// end Deck Constructor
   
   
   /**
   * initialize the full deck with cards in order
   */
   public void initializeFullDeck()
   {
      // setting up 52 cards in the deck but no value or suits yet
      this.deck = new ArrayList<Card>(NO_OF_CARDS); 
           
      //initializing the nested for loop to intialize the deck of 52 cards
      for(int suit = 0; suit <= 3; suit++)
      {
         for(int value = 1; value <= NO_OF_CARDS / 4; value++)
         {
            //create new cards based on iterated suits and values
            Card newCard = new Card(suit, value);
            this.deck.add(newCard); //append card to the end of the deck      
         }// end inner for loop
      }// end outer for loop
   }// end initializeFullDeck
   
  
   /**
   * shuffle the deck of 52 cards by randomizing the positions of the Collections
   */
   public void shuffleCard()
   {
      //shuffle the deck
      Collections.shuffle(this.deck);   
   }// end shuffleCard
   
   
   /**
   * Deal the card at the beginning of the left over deck, card dealt will be removed from the deck
   * @return a Card instance to be added to the players' Hands
   */
   public Card dealCard()
   {
      Card cardDealt = deck.get(0); //new card from deck's first element
      deck.remove(0); // remove the card just dealt from deck
  
      return cardDealt;
   }// end dealCard
  
   
   private ArrayList<Card> deck; // Deck of Cards
   private final static int NO_OF_CARDS = 52; //52 cards in a deck
   
}// end Class Deck
