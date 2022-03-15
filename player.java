import java.util.ArrayList;

public class player {
    
    // This class build player object in our game. Each player has a name. 
    String name;  
    ArrayList<Cardtest> hand; // Each player has a hand of card objects 


    // Initializing the constructor for the player object
    public player(String name,ArrayList<Cardtest> hand)
    {
        this.name = name;
        this.hand = hand;
    }

    // Method to draw a card from the deck of cards that we build
    public void draw(DeckofCards deck)
    {
        hand.add(deck.drawCard());
    }

    // Method to display each card in hand of player.
    // We use for loop and get methods on Arraylist to get required information of the card objects
    public void showHand()
    {
        for(int i = 0;i < hand.size(); i ++)
        {
            System.out.print(hand.get(i).getfaceName() + " of " +  hand.get(i).getSuit() + " | " );
        }
    }

    
}
