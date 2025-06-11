// Arsenii Hrokh 
// Student number: 3152380
package griffith;

public class Player { 
	private String name;
	private Card[] hand = new Card[5]; // hand can include 5 cards 
	
	public Player(String name) {
		this.name = name;
	}
	
	public void addCard(Card card) {
       for (int i = 0; i < hand.length; i++) { // here I am looping through hand array
    	   if (hand[i] == null) { // if slot at index i is empty
    		   hand[i] = card; // I add card
    		   return; // and imediately exit method to prevent from continuing the loop unnecessarily 
    	   }
       }
    }
	
	public int getHandValue() {
		int total = 0;
		int aceAmount = 0;
		
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null) { // here I am checking if there is a card to calculate total 
				total += hand[i].getValue(); // I add card's value to total variable 
			if (hand[i].getRank().equals("A")) { // if it is an Ace 
				aceAmount++; // I increase aceAmount
			}
		  }
		}
		while (aceAmount > 0) {
          if (total + 11 <= 21) {
                total += 11; // Ace counts as 11
            } else {
               total += 1; // Ace counts as 1
            }
            aceAmount--; // One Ace processed
        }

        return total;
	}
		
	
	
	
	public void showHand(boolean showAll) {
		System.out.println("\033[1m" + name + "'s Hand:\033[0m"); 
		for (int i = 0; i < hand.length; i++) { 
			if (hand[i] == null) // if there is no card and i position 
				continue; // I skip this card

			if (i == 0 && showAll == false && name.equals("Dealer")) { // if it is the first dealer's card
				System.out.println("Hidden"); // display it as "Hidden"
			} else {
				System.out.println(hand[i]); // display a card
			}
		}
		if (showAll) {
            System.out.println("Total Value: " + "\033[0;36m" + getHandValue() + "\033[0m"); // display player's or dealer's total 
        }
	}
	
	public boolean isBust() {
        return getHandValue() > 21; // checks if hand value is > 21
    }

	// getter (gets name)
	
    public String getName() {
        return name;
    }
}
