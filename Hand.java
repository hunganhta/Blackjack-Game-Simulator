import java.util.ArrayList;

/**
 * Class Hand which will be used to show the dealers and players hand
 * @author Anh Ta
 */
public class Hand {
   
   /**
   * Hand constructor, creating the hand Array List with undefined elements
   */  
   public Hand()
   {
      hand = new ArrayList<Card>();
   }// end Hand Constructor
   
   
   /**
   * add card to the current hand from the deck 
   * @param cardDealt that wants to be added to hand
   */
   public void addCardToHand(Card cardDealt)
   {
      hand.add(cardDealt); //add Card
   }// end addCardToHand
   
   
   /**
   * return the value of the players hand, resolve Kings, Queens, Jacks to appropriate values
   * @return the numeric value of the players Hand
   */
   public int getHandValue()
   {
      int sumOfHandValue = 0; //sum of all card values in the hand
      boolean ace = false; //variable checking for ace
      
      //loop thru the current hand
      for(int i = 0; i < hand.size(); i++)
      {
         int cardValue= hand.get(i).getCardValue(); //retrieve Card Value
            
         //check if card value is not a number but a face character
         if ((cardValue == Hand.JACK) || (cardValue == Hand.QUEEN) || (cardValue == Hand.KING))
         {
            cardValue = 10;
         }
         else if(cardValue == Hand.ACE) //check if it is an ace
         {
            ace = true; // there was an ace
         }
         
         sumOfHandValue += cardValue; //add cardValue to hand
      }// end for 
      
      //If there was an ace, check if there is a chance to get it to largest number to 21
      if((ace == true) && (sumOfHandValue + 10 <= 21))
      {
         sumOfHandValue += 10; //increment to the largest number possible close to 21
      }
      
      return sumOfHandValue;
   }// end addCardToHand
   
   
   /**
   * Display the hands of the players to console
   * @param dealer boolean variable to check if this is a dealers hand 
   * @param showDealerHand check if the round is the first round
   */
   public void displayHand(boolean dealer, boolean showDealerHand)
   {
      if(dealer && showDealerHand)
      {
         System.out.println("\nDealer's hand: " + this.hand.get(0).getCardValueName() + 
            " of " + this.hand.get(0).getCardSuitName() + " and another card faced down");
      }
      else if (dealer) //dealer hand but not first round
      {
         System.out.print("\nDealer's hand: ");
         
         //run thru the hand and print it out
         for (int i = 0; i < this.hand.size(); i++)
         {
            System.out.print(this.hand.get(i).getCardValueName() + " of " + this.hand.get(i).getCardSuitName());
            System.out.print("    ");
         }//end for
         System.out.println();
      }//end if
      else // players hand
      {
         System.out.print("Player's hand: ");
         
         //run thru the hand and print it out
         for (int i = 0; i < this.hand.size(); i++)
         {
            System.out.print(this.hand.get(i).getCardValueName() + " of " + this.hand.get(i).getCardSuitName());
            System.out.print("    ");
         }// end for
         System.out.println();
      }// end if
   }// end displayHand
   
   private ArrayList<Card> hand; //hands of the players containing cards
   
   // declaring the special characters of cards
   private final static int ACE = 1;
   private final static int JACK = 11;
   private final static int QUEEN = 12;
   private final static int KING = 13;
   
}// end class Hand
