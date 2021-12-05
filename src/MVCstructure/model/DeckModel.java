package MVCstructure.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/** Represent the Deck in the Game
 * @author 404 Bits Not Found(Henry Lai, Henry Fan, Gabby Kim, Bill Huynh)
 */
public class DeckModel {
	private ArrayList<CardModel> currentDeck;   // stores a deck as an arraylist

	/**Create a Deck that holds an a new Arraylist
	 *
	 */
	public DeckModel(){
		currentDeck = new ArrayList<>();
	}


	/** Get the number of cards in the deck
	 * @return to get the number of cards in the deck
	 */
	public int getNumOfCards() { return currentDeck.size(); }

	/** Get the cards in the deck
	 *
	 * @return an ArrayList holding the cards
	 */
	public ArrayList<CardModel> getCards(){
		return (ArrayList<CardModel>) currentDeck.clone();
	}

	/** Add cards to the deck
	 * 	adds a card to the current Deck (one by one)
 	 */
	public void addCard(CardModel c) { this.currentDeck.add(c); }

	/**Add an arraylist of card to the deck
	 *
	 * @param cards the array list of cards to add to deck
	 */
	public void addCard(ArrayList<CardModel> cards) { currentDeck.addAll(cards); }

	/** Remove a card from Deck
	 *
	 * @param index to remove a card at a certain index
	 */
	public void removeCard(int index) { this.currentDeck.remove(index);}

	/** Return a formatted String
	 *
	 * @return String formatted in a certain way
	 */
	public String toString(){
		String retStr = "";
		for(int i = 0; i < currentDeck.size(); i++){
			retStr += "   " + (i+1) + ".) " + currentDeck.get(i).line() + "\n";
		}
		return retStr;
	}

	/** Return an arraylist of cards in the Deck
	 *
	 * @return an ArrayList of cardmodel which is the Deck
	 */
	public ArrayList<CardModel> getCard(){
		return currentDeck;
	}

	/** ShuffleDeck, to shuffle the Deck
	 *
	 */
	public void shuffleDeck(){
		Collections.shuffle(currentDeck, new Random()); }

}
