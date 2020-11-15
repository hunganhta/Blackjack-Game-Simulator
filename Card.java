/**
 * Class Card which contains the class attributes such as suits and value of the cards
 * @author Anh Ta
 */
public class Card {

   /**
   * Card constructor making a Card from a suit and a value.
   * @param suit the suit of the card
   * @param value the value of the card
   */   
   public Card(int suit, int value){
      this.suit = suit; //initialize suit
      this.value = value; //initialize value
   }// end Card constructor
   
   
   /**
   * Return the value of the Card. Although KINGS, QUEENS, JACKS all have 10, 
   *  function will return different values than 10 which will get processed later in the Hand class
   * @return the value of the card
   */   
   public int getCardValue()
   {
      return this.value;
   }// end getCardValue
   
   
   /**
   * Return the suit of the Card
   * @return the suit of the card
   */   
   public int getCardSuit()
   {
      return this.suit;
   }// end getCardSuit
   
   
   /**
   * Return the name of the Card (including KINGS, QUEENS, JACKS, and ACES)
   * @return a String value of the name of the card
   */   
   public String getCardValueName()
   {
      String cardValueName;
      
      //check the value of the card and assign the appropriate name from the value
      if (this.value == Card.ACE)
      {
         cardValueName = new String("ACE"); // find and asign the ace value
      }// end if
      else if ((this.value < Card.JACK) && (this.value != Card.ACE))
      {
         cardValueName = String.valueOf(value); // assign suit less than jack but more than ace
      }// end else if
      else // checking for the Jacks, Queens, Kings, and logic errors
      {
         if (this.value == Card.JACK) // checking for jack value
         {
            cardValueName = new String("JACK");
         }
         else if (this.value == Card.QUEEN) // checking for queen value
         {
            cardValueName = new String("QUEEN");
         }
         else if (this.value == Card.KING) // checking for king value
         {
            cardValueName = new String("KING");
         }
         else
         {
            cardValueName = new String("INVALID CARD"); //logic error
         }
      }// end else
      
      return cardValueName;      
      
   }// end getCardValueName
   
   
   /**
   * Return the name of the suit of the Card 
   * @return a String value of the suit of the card
   */   
   public String getCardSuitName()
   {
      String cardSuitName;
      switch(this.suit)
      {
         case Card.HEARTS:
            cardSuitName = new String("HEARTS");
            break;

         case Card.SPADES:
            cardSuitName = new String("SPADES");
            break;
         
         case Card.CLUBS:
            cardSuitName = new String("CLUBS");
            break;

         case Card.DIAMONDS:
            cardSuitName = new String("DIAMONDS");
            break;
         
         default:
            cardSuitName = new String("INVALID SUITS");
            break;
      }// end switch
      
      return cardSuitName;
      
   } // end getCardSuitName
  

   // declaring the suits of cards as constants
   private final static int HEARTS = 0;
   private final static int SPADES = 1;
   private final static int CLUBS = 2;
   private final static int DIAMONDS = 3;
   
   // declaring the special characters of cards
   private final static int ACE = 1;
   private final static int JACK = 11;
   private final static int QUEEN = 12;
   private final static int KING = 13;
   
   private int suit; // the suit value
   private int value; //the value of the card
   
}//end Card class
