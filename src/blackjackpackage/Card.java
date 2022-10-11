package blackjackpackage;

public class Card {
	    // Author: Quinn Murphy
		// Date: 10/5/22
		// Class: CS145
		// Lab: Deck of Cards
		// References: Old programs, book, StackOverflow
	
	// this class will simply gets our enum constants. 
	// It then initializes them. It also has 
	// a toString() for the text to display
	// something like "HEART-NINE"
	private Suit suit;
	private Value value;
	
	// card constructor
	public Card(Suit suit, Value value) {
		this.value = value;
		this.suit = suit;
	}
	
	// return suit and value as string
	public String toString() {
		return this.suit.toString() + " OF " + this.value.toString();
		
	}
	// get card value
	public Value getCardValue() {
		
		return this.value;
	}
	
	
}
