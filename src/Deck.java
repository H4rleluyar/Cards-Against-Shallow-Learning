import java.util.ArrayList;

public class Deck extends Card{
	private String color; 					// color of deck; white (center deck)  or black (questions) or green (player's hand)  
	private int numOfCards; 				// number of cards in a deck
	private ArrayList<Card> currentDeck; 	// stores a deck as an arraylist
	
	/*
	 * @param array list of Cards called currentDeck
	 */
	public Deck(ArrayList<Card> currentDeck)
	{
		this.currentDeck = currentDeck; 
		
	}
	
	
	// getter and setter for deck color (either white or black) 
	public String getColor() { return color; }
	public void setColor(String newColor) { this.color = newColor; }
	
	
	// setter and getter for number of cards in a deck
	public int getNumOfCards() { return numOfCards; }
	public void setNumOfCards(int numOfCards) { this.numOfCards = numOfCards;} 
	
	// adds a card to the current Deck 
	public void addCard(Card c) 
	{
		this.currentDeck.add(c);
	}
	
	// removes a card from the current Deck 
	public void removeCard(int index)
	{
		this.currentDeck.remove(index);
	}
	
	
	
	
	
}
