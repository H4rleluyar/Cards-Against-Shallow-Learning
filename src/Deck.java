import java.util.ArrayList;

public class Deck{
	private ArrayList<Card> currentDeck; 	// stores a deck as an arraylist
	/*
	 * @param array list of Cards called currentDeck
	 */
	public Deck(){
		currentDeck = new ArrayList<>();
	}
	
	// setter and getter for number of cards in a deck
	public int getNumOfCards() { return currentDeck.size(); }
	
	// adds a card to the current Deck (one by one, or as a list)
	public void addCard(Card c) { this.currentDeck.add(c); }
	public void addCard(ArrayList<Card> cards) { currentDeck.addAll(cards); }
	
	// removes a card from the current Deck 
	public void removeCard(int index) { this.currentDeck.remove(index);}
	
	public String toString(){
		String retStr = "";
		for(int i = 0; i < currentDeck.size(); i++){
			retStr += "   " + (i+1) + ".) " + currentDeck.get(i).line() + "\n";
		}
		return retStr;
	}
}
