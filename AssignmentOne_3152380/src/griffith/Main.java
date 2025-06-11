// Arsenii Hrokh 
// Student number: 3152380
package griffith;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        
        int playerWinAmount = 0;
        String answer;
        int balance = 100; // initial player's balance
        boolean cheatMode = false;
        int index = 0; // needed for adding cards from cheat deck to player's hand
        
        
        // main game loop
        while (true && balance > 0) {
        	System.out.println("\033[1;37;44mWelcome to Blackjack!\033[0m");
            System.out.print("Do you want to enable cheat mode? (yes/no): ");
            String cheatAnswer = input.nextLine().toLowerCase().trim(); // saving user answer in lowercase format and without spaces 
            // simply asking player's input until he/she writes desired input
            while (!cheatAnswer.equals("yes") && !cheatAnswer.equals("no")) { 
            	System.out.println("\033[0;31mInvalid input, try again\033[0m");
            	cheatAnswer = input.nextLine().toLowerCase().trim();
            }
            // simply turns on cheat mode
            if (cheatAnswer.equals("yes")) {
            	cheatMode = true;
            }
            
        	Deck deck = new Deck(); // creating instance of Deck class
        	Card[] cheatDeck = cheatMode ? deck.getCheatDeck() : null;  // initialize cheat deck if cheatMode is true
            Player player = new Player("Player"); // creating instance of Player class
            Player dealer = new Player("Dealer"); // creating instance of Player class
            
            
            // simply adds cards to player's hand from cheat deck if cheatMode is true, else from default deck
            if (cheatMode) {
            	player.addCard(cheatDeck[index++]);
            	player.addCard(cheatDeck[index++]);
            } else {
            	player.addCard(deck.dealCard());
                player.addCard(deck.dealCard());
            }
           
            // simply adds cards to dealer's hand 
            dealer.addCard(deck.dealCard());
            dealer.addCard(deck.dealCard());
            
            // this while block responsible for accepting player's bet
            int bet = -1;
            while (bet == -1) {
            	System.out.print("Your balance is: " + balance + ". " + "Enter your bet:");
            	// try block responsible for checking if bet is negative or > than player's balance, otherwise it accepts bet
            	try {
            		bet = input.nextInt();
            		if (bet > balance || bet <= 0) {
            			System.out.println("\033[0;31mInvalid input, try again\033[0m");
                    	continue;
                    } else {
                    	break;
                    }
            		// catch block responsible for handeling not numeric values
            	} catch(InputMismatchException e) {
            		System.out.println("\033[0;31mYour bet must be number\033[0m");
            		input.next(); // clears invalid value, prevents from infinite looping
            	}		
            }
            input.nextLine(); // clears scanner's buffer
            
            
            
            
            boolean playerTurn = true;
        	
        	 while (playerTurn) {
             	player.showHand(false); // displays player's cards, but without total value
             	dealer.showHand(false); // displays one dealer's card and second as "Hidden", but without total value
             	
             	System.out.print("\033[0;33mDo you want to hit or stand? \033[0m");
                 
                 	String action = input.nextLine().toLowerCase().trim();
                 	// checks for desired input
                 	if (!action.equals("hit") && !action.equals("stand")) {
                 		System.out.println("\033[0;31mInvalid input, try again\033[0m");
                 		continue;
                 	} else {
                 		if (action.equals("hit")) {
                 			System.out.println("You have chosen to hit");
                 			// if cheatMode is true, player receives card from cheat deck
                 			if (cheatMode) {
                 				player.addCard(cheatDeck[index++]);
                 			}
           
                 			player.addCard(deck.dealCard()); //receives from default deck
                 			player.showHand(false);
                 			// check is payer's hand value > 21
                 			if(player.isBust()) {
                 				 System.out.println("\033[0;31mYou busted! Your hand value is: " + player.getHandValue() + "\033[0m"); 
                 				 System.out.println("Unlucky you lost your money");
                 				 balance -= bet; // player loses his money
                 				 System.out.println("Your balance is " + balance); // displays updated balance
                 				 break; // leaves while loop
                 			}
                 		} else if (action.equals("stand")) { // if player has chosen to stand, we leave loop
                 			playerTurn = false;
                 			break;
                 	}
                 }
             }
        	 
        	 if (player.isBust()) {
        		System.out.println("Do you want to play again? Enter \033[0;32m'yes'\033[0m to continue or \033[0;31m'no'\033[0m to stop:");
        		answer = input.nextLine().toLowerCase().trim();
        		// simply asking player's input until he/she writes desired input
              	while (!answer.equals("yes") && !answer.equals("no")) {
              		System.out.println("\033[0;31mInvalid input, try again\033[0m");
        			answer = input.nextLine().toLowerCase().trim();
              	}
        		
        		if (answer.equals("yes")) {
                     if (balance <= 0) { // checks if player has money to continue
                            System.out.println("You're out of money! Game over.");
                            break;
                     } else { 
                           	continue; 
                     }
                     		
                     } else if(answer.equals("no")) {
                     		System.out.println("Thank you for playing Blackjack.");
                     		System.out.println("Your win streak is " + playerWinAmount); // displays how many times player won
                     		break;
                     }
             } else {
            	 System.out.println("Dealer's turn");
                 dealer.showHand(false);
                 while (dealer.getHandValue() < 17) { // dealer receives cards until his hand value is < 17
                 	System.out.println("Dealer hits");
                 	dealer.addCard(deck.dealCard());
                 	dealer.showHand(true); // shows all dealer's cards and his hand's value
                 }
                 if (dealer.isBust()) { // if dealer busted
                	 playerWinAmount++; // I increase wins amount
                     System.out.println("Dealer busted! You won!");
                     balance += bet; // player increases his balance 
                     System.out.println("Congrats, your balance is " + balance); // displays updated balance
                     //return;
                 } else {
                	 player.showHand(true); // displays player's cards including total value
                     dealer.showHand(true); // displays dealer's cards including total value
                     
                     int playerValue = player.getHandValue(); // receives player's total
                     int dealerValue = dealer.getHandValue(); // receives player's total
                     
                     
                     // simple winner checking by comparing total values
                     
                     if (playerValue > dealerValue) {
                    	playerWinAmount++;
                     	System.out.println("\033[0;32mYou won!\033[0m");
                     	balance += bet;
                        System.out.println("Congrats, your balance is " + balance);
                     } else if (playerValue < dealerValue) {
                     	System.out.println("\033[0;31mDealer won!\033[0m");
                     	System.out.println("Unlucky you lost your money");
        				balance -= bet;
        				System.out.println("Your balance is " + balance);
                     } else {
                     	System.out.println("\033[0;36mIt's a tie!\033[0m");
                     	System.out.println("Your balance remains the same: " + balance);
                     }
                 }
            	 
             }
        	 
        	 
        	System.out.println("Do you want to play again? Enter \033[0;32m'yes'\033[0m to continue or \033[0;31m'no'\033[0m to stop:");
          	answer = input.nextLine().toLowerCase().trim();
          	// simply asking player's input until he/she writes desired input
          	while (!answer.equals("yes") && !answer.equals("no")) {
          		System.out.println("\033[0;31mInvalid input, try again\033[0m");
    			answer = input.nextLine().toLowerCase().trim();
          	}
    		
    		if (answer.equals("yes")) {
    			// checks if player has money to continue
                 if (balance <= 0) {
                        System.out.println("You're out of money! Game over.");
                        break;
                 } else { 
                       	continue;
                 }
                 		
                 } else if(answer.equals("no")) {
                 		System.out.println("Thank you for playing Blackjack.");
                 		System.out.println("Your win streak is " + playerWinAmount);  // displays how many times player won
                 		break; // leaves main game loop
                 }
        }
        
	}
	

}
