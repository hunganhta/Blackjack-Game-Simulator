import java.util.Scanner;

/**
 * Class contains the main function to simulate the program and handles card display
 * @author Anh Ta
 * @version 1.0
 */
public class BlackjackGameSimulator {

   /**
    * Program Main Entry Point
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      // TODO code application logic here
      
      BlackjackGameSimulator game= new BlackjackGameSimulator(); // new Blackjack game
      game.userContinue = true; //set userContinue to True initialy
      Scanner readMoney = new Scanner(System.in); //scanner to read in money input
      
      Deck deck = new Deck(); //create new deck
      
      System.out.print("\n\nHow much dollar do you want to gamble today: ");
      game.setBankRoll(readMoney.nextFloat()); //read in money gamble input
      System.out.println("Bringing out the chips... Let the game begins!");
      
      //while condition for when players bank roll is positive and players still want to play
      while((game.getBankRoll() > 0) && (game.userContinue == true))
      {
         Hand dealer = new Hand(); // create new dealer hand instance for every new round
         Hand player = new Hand(); // create new player hand instance for every new round
      
         float moneyThisRound; // variable to store money user wants to bet in
         System.out.print("\n\nHow much dollar do you want to gamble this round: ");
         moneyThisRound = readMoney.nextFloat();
         
         //check if a valid amount > 0 is being bet
         if(!game.checkValidBettingAmount(moneyThisRound))
         {
            System.out.println("Please enter a valid betting amount that is greater than 0.");
            continue;
         }
         
         //check if the player even has the cash to play this round based on his wish to bet this much
         if(moneyThisRound > game.getBankRoll())
         {
            System.out.println("Please enter a betting amount that is less than your current bank roll of " + game.getBankRoll() + ".");
            continue;
         }

         game.dealerWin = false; //set win condition for dealer
         game.playerWin = false; //set win condition for player
         game.dealerTurn = false; // set dealer's turn to be false as it is not his turn yet

         //deal 2 cards to the player first
         player.addCardToHand(deck.dealCard());
         player.addCardToHand(deck.dealCard());
         
         //deal 2 cards to the dealer later
         dealer.addCardToHand(deck.dealCard());
         dealer.addCardToHand(deck.dealCard());
         
         game.displayHands(player, dealer, true); //first display of cards
         
         //check if this is an instant blackjack for the player
         if(game.checkBlackjackWinCondition(player, dealer, moneyThisRound))
         {
            game.checkUserContinue(); //check whether user wants to continue the game
            continue;
         }
         
         game.userContinueHit = true;
         game.checkUserContinueHit(); // check if user wants to hit
                 
         //ask the users to hit or or stay in the while control loop
         while(game.userContinueHit)
         {
            player.addCardToHand(deck.dealCard());// add more cards to players hand
            
            //check if player is busted
            if(player.getHandValue() > 21)
            {
               game.dealerWin = true; //dealer win player busted
               game.bankRoll -= moneyThisRound; //player loses money
               System.out.print("\n\nDealer Wins! ");
               game.displayHands(player, dealer, false); //display of cards normal printing
               System.out.print("Your updated bank roll is: " + game.getBankRoll() + ". ");
               break;
            }
            //check condition for blackjack and account for bank roll
            if(!game.checkBlackjackWinCondition(player, dealer, moneyThisRound))
            {
               System.out.println();
               game.displayHands(player, dealer, true); //display of cards normal printing
               game.checkUserContinueHit(); // check if user wants to hit
            }
            else// we got a winner for the player blackjack!
            {
               break;
            }
         }//end while and stop adding to player's hand
         
         game.dealerTurn = true; // set dealer turn to be true as it is his turn now
         
         //run the dealer hand value up until 17 while player not wining yet
         while(((dealer.getHandValue() <= 17) && (!game.playerWin)) && (!game.dealerWin))
         {
            dealer.addCardToHand(deck.dealCard());// add more cards to dealers hand
            
            //check if dealer is busted
            if(dealer.getHandValue() > 21)
            {
               game.dealerWin = false; //dealer busted 
               game.playerWin = true; 
               
               game.bankRoll += moneyThisRound; //player wins money
               System.out.print("\n\nPlayer Wins! ");
               game.displayHands(player, dealer, false); //display of cards normal printing
               System.out.print("Your updated bank roll is: " + game.getBankRoll() + ". ");
               break;
            }
            //check condition for blackjack and account for bank roll
            if(!game.checkBlackjackWinCondition(player, dealer, moneyThisRound))
            {
               System.out.println();
               game.displayHands(player, dealer, false); //display of cards normal printing
            }
            else// we got a winner for the player blackjack!
            {
               break;
            }
         }// end while and stop adding to dealer's hand
         
         //If no winner is found by blackjack yet, comparing hands value now 
         if ((!game.dealerWin) && (!game.playerWin))
         {
            //dealer got bigger hand value
            if(dealer.getHandValue() > player.getHandValue())
            {
               game.dealerWin = true;
               game.bankRoll -= moneyThisRound; //player loses money
               System.out.print("\n\nDealer Wins! ");
               game.displayHands(player, dealer, false); //display of cards normal printing
               System.out.print("Your updated bank roll is: " + game.getBankRoll() + ". ");
               
            }
            else if (dealer.getHandValue() < player.getHandValue()) //player winning
            {
               game.playerWin = true;
               game.bankRoll += moneyThisRound; //player wins money
               System.out.print("\n\nPlayer Wins! ");
               game.displayHands(player, dealer, false); //display of cards normal printing
               System.out.print("Your updated bank roll is: " + game.getBankRoll() + ". ");
            }
            else //we have a tie condition
            {
               System.out.println("\n\nWe are tied! ");
               game.displayHands(player, dealer, false); //display of cards normal printing
               System.out.print("Your updated bank roll is: " + game.getBankRoll() + ". ");
            }
         }// end if
         
         //check if bank roll is zero and terminate the game
         if(game.getBankRoll() == 0)
         {
            System.out.println("You have zero bank roll left. Please go to the cashier and re-up.");
            System.out.println("We will see you later!");
            break;
         }
         
         deck.initializeFullDeck(); //reintialize deck for next round
         deck.shuffleCard(); // reshuffle deck
         game.checkUserContinue(); //check whether user wants to continue the game      
      }//end while 
     
   }// end main
   
   
   /**
   * Check if valid amount of money is being bet into the game
   * @param moneyBet the money the user wants to bet this round
   * @return the variable to check valid betting amount
   */
   private boolean checkValidBettingAmount(float moneyBet)
   {
      boolean valid = false;
      //check if moneyBet is greater than 0
      if (moneyBet > 0)
      {
         valid = true; //reset valid
      }
      
      return valid; 
   }// end checkValidBettingAmount
   
   
   /**
   * Check the continue to hit decision of the player
   */
   private void checkUserContinueHit()
   {
      String decision = new String("H");
      
      Scanner playDecisionRead = new Scanner(System.in); //record users play decision
      System.out.print("\nPress (H = \"Hits\"; S/Others = \"Stay\")? : ");
      decision = playDecisionRead.nextLine().toUpperCase();
         
      //check user's decision
      if(!decision.equals("H")) //chose to stay
      {
         this.userContinueHit = false;
      }
      else
      {
         this.userContinueHit = true;   
      }
      
   }// end check user continue Hit
   
   
   /**
   * Check the continue decision of the player 
   */
   private void checkUserContinue()
   {
      Scanner userContinueRead = new Scanner(System.in); //scanner to read in users decision to continue
      System.out.print("Do you want to continue (N = \"No\"; Y/Others = \"Yes\" )? : ");
      String userDecision = userContinueRead.nextLine(); //read in users decision         
            
      //check if users want to continue
      if (userDecision.toUpperCase().equals("N"))
      {
         this.userContinue = false; //if dont want to continue, set userContinue = false
         System.out.println("\nIt was a good game. Your final bank roll is: " + this.getBankRoll() +".");
         System.out.println("Please welcome back anytime!");
      }
   }// end check user continue
   
      
   /**
   * Check if the win condition automatically by blackjack, add or substract money from current round
   * @param player the player hand
   * @param dealer the dealer hand
   * @param moneyThisRound money bet this round from player
   * @return boolean value of blackjack if there is a blackjack
   */
   private boolean checkBlackjackWinCondition(Hand player, Hand dealer, float moneyThisRound)
   {
      boolean blackjack = false; //check whether round should be ending as winners emerging
      
      if (this.checkBlackjack(player)) // check win conditions of player
      {
         System.out.print("\n\nPlayer Wins by Blackjack!");
         this.displayHands(player, dealer, false); // display blackjack hands
         blackjack = true;
         
         //reset winners conditions
         this.dealerWin = false;
         this.playerWin = true;
         
         //reset bank roll
         this.bankRoll += moneyThisRound;
         System.out.print("Your updated bank roll is: " + this.getBankRoll() + ". ");
      }
      
      else if (this.checkBlackjack(dealer) && (this.dealerTurn)) // check win conditions of dealer
      {
         System.out.print("\n\nDealer Wins by Blackjack!");
         this.displayHands(player, dealer, false); // display blackjack hands
         blackjack = true;
         
         //reset winners conditions
         this.dealerWin = true; 
         this.playerWin = false;
         
         //reset bank roll
         this.bankRoll -= moneyThisRound;
         System.out.print("Your updated bank roll is: " + this.getBankRoll() + ". ");
      }
      
      return blackjack; 
   }// end check Win Condition
   
   
   /**
   * Display the hands of the players
   * @param player the player hand
   * @param dealer the dealer hand
   * @param showDealerHand check if this is the first round so that we can see dealer's card
   */
   private void displayHands(Hand player, Hand dealer, boolean showDealerHand)
   {
      dealer.displayHand(true, showDealerHand); //print dealer
      player.displayHand(false, showDealerHand);//print players  
   }// end displayHands
   
   
   /**
   * Check if this is a blackjack hand
   * @param hand the player hand or the dealer hand
   * @return return whether a blackjack is present in the hand
   */
   private boolean checkBlackjack(Hand hand)
   {
      boolean blackjack = false;
      
      //check blackjack
      if(hand.getHandValue() == 21)
      {
         blackjack = true;
      }
      
      return blackjack;
   }// end displayHands
   
   
   /**
   * Set the bank roll based on the money input
   * @param money money equivalent to bank roll
   */   
   private void setBankRoll(float money)
   {
      this.bankRoll = money;
   }// end setBankRoll
   
   
   /**
   * Return the bank roll of the player
   * @return the bank roll of the player
   */   
   private float getBankRoll()
   {
      return this.bankRoll;
   }//end getBankRoll
 
   private float bankRoll; //bankRoll counter of the player
   private boolean userContinue; //user continue variable to play the game
   private boolean userContinueHit; //user continue to hit
   private boolean dealerWin; //dealer win
   private boolean playerWin; //user win
   private boolean dealerTurn; //signal dealer turn

}// end BlackjackGameSimulator

