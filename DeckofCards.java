import java.util.ArrayList;
public class DeckofCards {

    //Using an Arraylist to hold all the card objects we will create and build a deck of cards
    ArrayList<Cardtest> deck = new ArrayList<>();

    //Initializing a constructor to construct a deck
    public DeckofCards(ArrayList<Cardtest> deck)
    {
        this.deck = deck;
    }

    public DeckofCards() {

        // Building an array of facenames which we will use to build individual cards
        String facenames[] = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        // Array to build suit of cards
        String types[] = {"Spades","Clubs","Diamonds","Hearts"};
        //Assigning rank to individual cards to calculate winnes
        int ranks[] = {2,3,4,5,6,7,8,9,10,11,12,13,14};

        //Nested for loop to create 52 card objects in our deck of cards
        for(int i = 0 ; i < facenames.length; i ++)
        {
            for (int j = 0; j < types.length;j++)
            {
                Cardtest card = new Cardtest();
                card.setfaceName(facenames[i]);
                card.setSuit(types[j]);
                card.setrank(ranks[i]);
                deck.add(card);
            }
        }   
        }

        // This method is used to get the deck in every other class and to run methods on the Arraylist.
        // It returns an Arraylist of the deck object.
        public ArrayList<Cardtest> getDeck(){
            return deck;
        }

        // Method used to draw the topcard of the deck of cards.
        public Cardtest drawCard(){
            
            return deck.remove(0);
        }
        
}

