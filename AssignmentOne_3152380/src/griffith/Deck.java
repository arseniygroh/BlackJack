// Arsenii Hrokh 
// Student number: 3152380
package griffith;


public class Deck {
	private Card[] cards = new Card[52]; // initializing deck of cards 
	private int upperCard = 0; // this variable I need for dealing cards
	
	public Deck() {
		String types[] = {"♥", "♦", "♣", "♠"}; // initializing array of types
		String ranks[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}; // initializing array of ranks
		int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 0}; // initializing array of values
		
		int index = 0; // this variable is needed for populating deck with cards
		
		// this nested for loop is needed to fill the deck
		for (int i = 0; i < types.length; i++) { // outer loop is needed to ensure that each type of card is has its rank and value
			for (int j = 0; j < ranks.length; j++) {  
				cards[index++] = new Card(types[i], ranks[j], values[j]); // creating cards
			}
		}
		
		shuffle(); 
	}
	public void shuffle() {
       for (int i = 0; i < cards.length; i++) {
    	   int randomCard = (int)(Math.random() * 52); // getting random index from 0 to 52
    	   Card temp = cards[i]; // saving random card to prevent overwriting 
    	   
    	   // swapping cards
    	   cards[i] = cards[randomCard]; 
    	   cards[randomCard] = temp;
       }
       // resetting first card after shuffling
       upperCard = 0;
    }

    public Card dealCard() {
        return cards[upperCard++]; // returns card from a deck, works in combination with addCard()
    }
    
    public Card[] getCheatDeck() {
    	return createCheatDeck(); // returns cheat deck
    }
    
    public Card[] createCheatDeck() {
    	Card[] cheateDeck = new Card[20]; // array with 20 items
    	int index = 0; // needed for adding cards 
    	
    	// this loop is needed to create cheat deck which consists only of high value cards
    	for (int i = 0; i < this.cards.length; i++) {
    		if (cards[i].getRank().equals("10") || // this if statement checks if it high value card
    			cards[i].getRank().equals("J") || 
    			cards[i].getRank().equals("Q") || 
    			cards[i].getRank().equals("K") || 
    			cards[i].getRank().equals("A")) 
    			{
    		      cheateDeck[index++] = cards[i];
    		    }
    	}
    	return cheateDeck;
    	
    }
}
