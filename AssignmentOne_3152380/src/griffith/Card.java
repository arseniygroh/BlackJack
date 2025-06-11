// Arsenii Hrokh 
// Student number: 3152380
package griffith;

public class Card {
	private String type;
	private String rank;
	private int value;
	
	public Card(String type, String rank, int value) {  // initializing constructor with 3 parameters needed to create card objects
		this.type = type;
		this.rank = rank;
		this.value = value;
	}
	
	public int getValue() { // getter needed to get card's value
		return value;
	}
	
	public String getRank() { // getter needed to get card's rank
		return rank;
	}
	
	public String toString() { // I have rewritten toString(), so when I print my card in a good format
        return createCard(this.type, this.rank);
    }
	
	private String createCard(String type, String rank) { // this method creates good design for my cards
		String line = "*-----*"; // this serves as top and bottom parts of my card
		String middle1 = "| " + type + "   |"; // design part of my card
		String middle2 = (rank.length() == 2) ? "|  " + rank + " |" : "|  " + rank + "  |"; // here I am checking if it is a 10, if it is I am decreasing amount of spaces, because it will break the design
		String middle3 = "|   " + type + " |"; // design part of my card
		
		String drawedCard = String.join("\n", line, middle1, middle2, middle3, line); // here I am concatenating my design parts and using "\n" to ensure that all parts appear on a new line
		
		return drawedCard;
	}
}
