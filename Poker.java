import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Poker {

    /*This class builds a game of poker . We initially have:
    1) An Arraylist of players(A list of player objects).
    2) The number of players(which we get from the user).
    3) We make a deck of cards initially.
    4) An arraylist that stores scores of each player which helps in printing winner.
    5) Two integers n  helps in a certain functions
    6) 4 Custom Arraylists to help keep tabs of player index and player pairs
    7) A scanner function */

    ArrayList<player> players = new ArrayList<>();
    int numofplayers;
    DeckofCards deck = new DeckofCards();
    ArrayList<Integer> scores = new ArrayList<>();
    int n = 0;
    ArrayList<Cardtest> HighcardArray = new ArrayList<>();
    List<Rankindex<Integer,Integer>> threeofaKind = new ArrayList<Rankindex<Integer,Integer>>();
    List<Rankindex<Integer,Integer>> fourofaKind = new ArrayList<Rankindex<Integer,Integer>>();
    List<Rankindex<Integer,Integer>> onepair = new ArrayList<Rankindex <Integer, Integer>>();
    List<Rankindex<Integer,Integer>> twoPaair = new ArrayList<Rankindex <Integer, Integer>>();
    int option = 0;
    Scanner selector = new Scanner(System.in);

    public Poker(ArrayList<player> players) // Creating the constructor with the players array
    {
        this.players = players;
    }

    public Poker()
    {
        // Printing a menu and options to choose 
        while(option != 1 && option != 2)
        {
            System.out.println("************MAIN MENU***************");
            System.out.println("1. Play Game");
            System.out.println("2. Exit");
            System.out.println("Select your option: ");
            option = selector.nextInt();
        }
        if (option == 2)
        {
            System.exit(0);
        }
        System.out.println("Enter the number of player you want: "); // Asking the user for number of players
        numofplayers = selector.nextInt();
        while(numofplayers > 10) // Max number of player can only be 10 since deck has 52 cards
        {
            System.out.println("Max number of players allowed is 10");
            System.out.println("Enter the number of player you want");
            numofplayers = selector.nextInt();
        }
        for(int i = 0 ; i < numofplayers; i++)
        {
            ArrayList<Cardtest> hand = new ArrayList<>(); // Creating an Arraylist with card objects for each hand
            String name;
            Scanner sc = new Scanner(System.in); 
            System.out.println("Enter the name of player " + (i+1) + ": "); // Getting name of each player 
            name = sc.nextLine();
            players.add( new player(name,hand)); // Adding each created player object to the players array
        }
    }

    // We have a Method to shuffle the deck 
    public void shuffleDeck()
    {
        Collections.shuffle(deck.getDeck());
    }

    // Method to give cards to every player, we have a nested for loop, I have used an advanced for loop of java
    public void giveCards()
    {
        for(player p:players)
        {
            for(int i = 0; i < 5; i++)
            {
                p.draw(deck);
            }
        }
    }

    // Method to take each individual hand from each player and pass it to the main method which applies logic
    public void takeHands(ArrayList<player> players)
    {
        for(player p:players)
        {
            thesplit(p.hand);
        }
    }

    // A method that basically gets keys from the values of a hashmap since getting keys from values is not possible otherwise
    private static <K, V> K getKeyFromValue(Map<K, V> map, Object value) {
        
        //get all map keys using keySet method
        Set<K> keys = map.keySet();
        
        //iterate all keys
        for(K key : keys){
            
            //if maps value for the current key matches, return the key
            if( map.get(key).equals(value) )
            {
                return key;
            }          
        }
        
        //if no values matches, return null
        return null;        
    }


    /* thesplit is one of the long methods in this project:
    This method takes in each hand from each player in the game 
    Reasons I why i created such a long method are: 
    1. I wanted all local variables to refresh each turn for each player
    2. I did not want to seperate local variables as combining them later from different 
    methods will prove to be a nightmare.
    */
    public void thesplit(ArrayList<Cardtest> phand)
    {
        Map<String, Integer> pair1 = new HashMap<String, Integer>(); // Creating a hashmap of pairs
        Map<String,Integer> suit1 = new HashMap<String, Integer>();  // Creating a hashmap of suits
        Map<Integer,Integer> rank1 = new HashMap<Integer,Integer>(); // Creating a hashmap of ranks

        // These Hashmaps are basically like dictionaries in python.
        // I need them to calculate number of pairs of each suit and each facename to make the logic work

        Cardtest Highcard = new Cardtest();
        // Creating a new card for highcard
        int bigrank = 0;
        int countPair = 0; // to count number of pairs
        ArrayList<Integer> Sortedranks = new ArrayList<>(); // to check for sorted ranks for straight
        // Defining all conditions or instances in the game as boolean values which are originally set to false
        boolean twopairs = false; 
        boolean threeOfAKind = false,fourOfAKind = false,FullHouse = false,Straight = false,Flush = false,StraightFlush = false,RoyalFlush = false;

        // Takes each card in hand and adds it's facename to pair1 Hashmap.
        // if face is  in pair1, it adds 1 to the values, if not, it sets the value to 1
        for(Cardtest c : phand) {
 
            if(pair1.containsKey(c.getfaceName())){

                pair1.put(c.getfaceName(), pair1.get(c.getfaceName()) + 1);
            } 
            else {
                pair1.put(c.getfaceName(), 1);
            }
        }

        // same this here as facename, I am getting suits this time 
        for(Cardtest c: phand)
        {
            if(suit1.containsKey(c.getSuit()))
            {
                suit1.put(c.getSuit(),suit1.get(c.getSuit()) + 1);
            }
            else{
                suit1.put(c.getSuit(),1);
            }
        }

        // doing the same for ranks
        for(Cardtest c: phand)
        {
            if(rank1.containsKey(c.getrank()))
            {
                rank1.put(c.getrank(),rank1.get(c.getrank()) + 1);
            }
            else
            {
                rank1.put(c.getrank(),1);
            }
        }

        // Using the pair1 hashmap, we can see if we have a pair or not
        // i.e if two or more cards are the same , i.e if we have 2 or 3 aces or not
        // Adding the ranks and the indexes of the player to the pairs list to keep record of both
        for (Integer c:pair1.values())
        {
            if (c == 2)
            {
               countPair++;
               if(countPair == 1)
                {
                if(rank1.values().contains(2))
                {
                    onepair.add(new Rankindex<Integer,Integer>(getKeyFromValue(rank1, 2),n));
                }
                } 
            }
            if (c == 3)
            {
                threeOfAKind = true; //checking for three of a kind
                if(rank1.values().contains(3))
                {
                    threeofaKind.add(new Rankindex<Integer,Integer>(getKeyFromValue(rank1, 3),n));
                    // Adding the rank and index of player to threeofakind arraylist to keep record
                    //Fething the keys associated with values 3 by using the kgetKeyFromValue method
                }
            }
            if (c == 4)
            {
                fourOfAKind = true; //checking for four of a kind 
                if(rank1.values().contains(4))
                {
                    fourofaKind.add(new Rankindex<Integer,Integer>(getKeyFromValue(rank1, 4), n));
                    //Same addition of values here as well
                }
            }
        }

        if (countPair == 2)
        {
            twopairs = true; // check for two pair 
            if(rank1.values().contains(2))
            {
                twoPaair.add(new Rankindex<Integer,Integer>(getKeyFromValue(rank1, 2), n));
                //Same addition of values 
            }
        }

        if (countPair == 1 && threeOfAKind == true)
        {
            FullHouse = true; // check for fullhouse
        }

        // now we use the suit1 hashmap to check for flush,Straight Flush or Royal Flush 
        for(Integer s:suit1.values())
        {
            if(s == 5) // if all cards are the same suit, we have a flush
            {
                Flush = true;
            }
        }

        // Adding ranks of each card to a array which will be sorted to check for Straight 
        for(int i = 0; i < phand.size(); i ++)
        {
            Sortedranks.add(phand.get(i).getrank());
        }

        Collections.sort(Sortedranks);

        // Checking for Straight.
        for(int i = 0; i < Sortedranks.size(); i++)
        {
            if (i == Sortedranks.size()-1) // if value of i reaches the last element, it is a straight. 
            {
                Straight = true;
                break;
            }
            if(Sortedranks.get(i)+1 == Sortedranks.get(i+1)) // Comparing ranks of each card with the successive card in a sorted arraylist
            {
                continue;
            }
            else
            {
                break;
            }
        }

        if (Flush == true && Straight == true)
        {
            StraightFlush = true; // Checking for Straight Flush
        }

        // If the firstcard of Sortedlist has a rank of 11, i.e it's a Jack and StraightFlush == True,
        // RoyalFlush is Automatically true as all conditions are satisfied.
        if (StraightFlush == true && Sortedranks.get(0) == 11)
        {
            RoyalFlush = true; // Checking for Royal Flush 
        }

        // Following two loops check for highcard using the ranks of each card object 
        for(Cardtest card: phand)
        {
            if (card.getrank() > bigrank)
            {
                bigrank = card.getrank();
            }

        }
        for (Cardtest card:phand)
        {
            if(card.getrank() == bigrank)
            {
                Highcard = card;
            }
        }
        HighcardArray.add(Highcard); // Adding each highcard to the Highcard Array.
        // Passing each variable and boolean to another method to get results 
        getResult(countPair, twopairs, threeOfAKind, Straight, Flush, FullHouse, fourOfAKind, StraightFlush, RoyalFlush, Highcard);

    }


    // A series of if else statements which further pass values and rank to another method to display results 

    public void getResult(int countpair,boolean twopairs,boolean threeOfAKind,boolean Straight,boolean Flush,boolean fullhouse,boolean fourOfAKind,boolean StraightFlush,boolean RoyalFlush,Cardtest highcard)
    {
        if(RoyalFlush == true)
        displayResult(10,"Royal Flush");
        else if(StraightFlush == true)
        displayResult(9,"Straight Flush");
        else if(fourOfAKind == true)
        displayResult(8,"Four of a kind");
        else if(fullhouse == true)
        displayResult(7,"Fullhouse");
        else if(Flush == true)
        displayResult(6,"Flush");
        else if(Straight == true)
        displayResult(5,"Straight");
        else if(threeOfAKind == true)
        displayResult(4,"Three of a kind");
        else if(twopairs == true)
        displayResult(3,"Two pairs");
        else if(countpair == 1)
        displayResult(2,"One pair");
        else
        displayResult(1, "Highcard of " + highcard.getfaceName());
    }

    // This method displays results and prints cards for each player.
    // We pass in the rank or values of score of each player to the scores array. 
    public void displayResult(int rank,String result)
    {
        System.out.println("Cards of player " + players.get(n).name + ":");
        players.get(n).showHand();
        System.out.println("\nThe Result is: " + result);
        scores.add(rank);
        n++;
    }

    /* I was not expecting this method to become so big but it ended up becoming the most complicated method 
    in the entire project. 
    As the name suggests, this method compared every player's cards and then prints out the winner. This can a hard
    explain because I've used some cryptic looking syntax*/
    public void printWinner()
    {
        // initializing globals for the method
        int scoreBiggest = 0;
        int biggest = 0;
        int playerIndex = 0;
        int highcardLargest = 0;
        int indexHighcard = 0;
        //Checking the scores array for the biggest score 
        for (Integer i: scores)
        {
            if (biggest < i)
            {
                biggest = i;
            }
        }
        // Creating a custom list intergerlist which stores score and index of players having same scores 
        // If two people have same scores, we check for other conditions and keep tabs on the index of players
        List<printwinner<Integer,Integer>> integerlist = new ArrayList<printwinner<Integer,Integer>>();
        for(int i = 0 ; i < scores.size(); i ++)
        {
            if(biggest == scores.get(i)) // getting largest scores and index of it,filling the arraylist
            {
                integerlist.add(new printwinner<Integer,Integer>(scores.get(i), scores.indexOf(scores.get(i))));
            }
        }

        // Checking if the size is just 1, meaning we have no duplicates in the scores, Therefore we have 
        // A clear winner , so printig the winner .
        if(integerlist.size() == 1)
        {
            System.out.println("This winner is " + players.get(integerlist.get(0).index).name + " ! ");
            System.out.println("Congratulations " + players.get(integerlist.get(0).index).name + " ! ");
        }
        // If there are duplicates in the list 
        else if(integerlist.size()>1)
        {
            //Checking for condition 1, i.e if there are only highcards in each player's hand 
            // We check which highcard is the highest in highcard array 
            if(integerlist.get(0).score == 1)
            {
                int totalhighestcards = 0;
                ArrayList<Integer>localWinners = new ArrayList<>();
                //If highcards are the same for some players, or duplicate, we need to take the winners and check 
                // Their other cards to find out who wins and elimate people who have a lower card. 
                // This process continues till there is a single person left 
                // Creating a customlist, which stores the player and the index number of that player in the players array
                List<Rankindex<player,Integer>> Mainlist = new ArrayList<Rankindex<player,Integer>>();
                for(int i = 0; i < HighcardArray.size();i++)
                {
                    if (HighcardArray.get(i).getrank() >highcardLargest)
                    {
                        highcardLargest = HighcardArray.get(i).getrank();
                    }
                } // taking the highest card value
                
                for(int i = 0; i < HighcardArray.size(); i ++)
                {
                    if (highcardLargest == HighcardArray.get(i).getrank())
                    {
                        totalhighestcards++;
                        indexHighcard = i;
                        localWinners.add(i);
                    } // getting highest card and a list of index of winner or winners
                }
                //If there is a clear winner 
                if (totalhighestcards == 1)
                {
                 System.out.println("This winner is " + players.get(indexHighcard).name + " ! ");
                 System.out.println("Congratulations " + players.get(indexHighcard).name + " ! ");
                }

                // if we have duplicates 
                else
                {
                    for(Integer i: localWinners) // check the index in players array
                {
                    //ArrayList<player>temp = new ArrayList<>();
                    for(int j = 0; j < players.size();j++)
                    {
                        Mainlist.add(new Rankindex<player,Integer>(players.get(i),i)); 
                        // Fill the Mainlist with players and index of that player
                    }
                }
                int tempLargest = 0;
                //Running a complex for loop. This is a bit hard to explain 
                // So , we hare taking the second card in the hands of each player, this is represented by i
                // We are iterating over players j and comparing their cards, if anything is same or not
                 for( int i = 1 ; i < Mainlist.get(0).rank.hand.size();i++)
                 {
                     for(int j = 0;j < Mainlist.size();j++)
                     {
                        
                        if(Mainlist.get(j).rank.hand.get(i).getrank() > tempLargest)
                        {
                            tempLargest = Mainlist.get(j).rank.hand.get(i).getrank(); //takes the largest card
                        }
                        if(j == Mainlist.size()-1) 
                        {
                            // if j is highest , check the templargest against the ith cards of each player
                            for(int g = 0; g < Mainlist.size(); g++)
                               {
                                   if(Mainlist.size() == 1) // if size of mainlist drops to 1, break the loop
                                   {
                                       break; 
                                   }
                                   // if templargest is not equal to a current player's card, remove that player 
                                   if(tempLargest != Mainlist.get(g).rank.hand.get(i).getrank())
                                   {
                                        Mainlist.remove(g);
                                        g--; // decrement g 
                                   }
                               }
                               tempLargest = 0; // set templargest back to zero 
                        }
                       
                        }
                     }
                     // The winner is the last player left in the Mainlist , get the index value of that 
                     // player and put it in the player array index to get the name. 
                     System.out.println("This winner is " + players.get(Mainlist.get(0).index).name + " ! ");
                     System.out.println("Congratulations " + players.get(Mainlist.get(0).index).name + " ! ");
                }
                
            }

            // Condition 2, if two people have a pair 
            if(integerlist.get(0).score == 2) 
            {
                int numoftimes = 0;
                ArrayList<Integer> indexList = new ArrayList<>();
                // get largest value from onepair  Arraylist
                for(int i= 0; i < onepair.size(); i++)
                {
                    if(scoreBiggest < onepair.get(i).rank)
                    {
                        scoreBiggest = onepair.get(i).rank;
                    }
                }
                // get index of the largest player from onepair Arraylist
                for(int i = 0; i < onepair.size(); i ++)
                {
                    if(scoreBiggest == onepair.get(i).rank)
                    {
                        playerIndex = onepair.get(i).index;
                        numoftimes++; // checking if both have the same rank of cards or not
                        indexList.add(onepair.get(i).rank); // adding indexes of players who have the highest card in an indexlist
                    }
                }
                //If clear winner, print the winner 
                if(numoftimes == 1)
                {
                    System.out.println("This winner is " + players.get(playerIndex).name + " ! ");
                    System.out.println("Congratulations " + players.get(playerIndex).name + " ! ");
                }
                // If not , iterate over the remaining players in the indexList to find whose cards are the highest
                // Same as the previous function
                else
                {
                    List<Rankindex<player,Integer>> Mainlist = new ArrayList<Rankindex<player,Integer>>();
                    for(int i = 0; i < indexList.size(); i ++)
                    {
                        Mainlist.add(new Rankindex<player,Integer>(players.get(indexList.get(i)), indexList.get(i)));
                        // fills the Mainlist
                    }
                    int tempLargest = 0;
                    for( int i = 1 ; i < Mainlist.get(0).rank.hand.size();i++)
                 {
                     for(int j = 0;j < Mainlist.size();j++)
                     {
                        
                        if(Mainlist.get(j).rank.hand.get(i).getrank() > tempLargest)
                        {
                            tempLargest = Mainlist.get(j).rank.hand.get(i).getrank(); //takes the largest card
                        }
                        if(j == Mainlist.size()-1) 
                        {
                            // if j is highest , check the templargest against the ith cards of each player
                            for(int g = 0; g < Mainlist.size(); g++)
                               {
                                   if(Mainlist.size() == 1) // if size of mainlist drops to 1, break the loop
                                   {
                                       break; 
                                   }
                                   // if templargest is not equal to a current player's card, remove that player 
                                   if(tempLargest != Mainlist.get(g).rank.hand.get(i).getrank())
                                   {
                                        Mainlist.remove(g);
                                        g--; // decrement g 
                                   }
                               }
                               tempLargest = 0; // set templargest back to zero 
                        }
                       
                        }
                     }
                     //print out the winner 
                     System.out.println("This winner is " + players.get(Mainlist.get(0).index).name + " ! ");
                     System.out.println("Congratulations " + players.get(Mainlist.get(0).index).name + " ! ");
                }
            }

            //Checking two pairs , similar to one pair 
            if(integerlist.get(0).score == 3)
            {
                for(int i= 0; i < twoPaair.size(); i++)
                {
                    if(scoreBiggest < twoPaair.get(i).rank)
                    {
                        scoreBiggest = twoPaair.get(i).rank;
                    }
                }
                for(int i = 0; i < twoPaair.size(); i ++)
                {
                    if(scoreBiggest == twoPaair.get(i).rank)
                    {
                        playerIndex = twoPaair.get(i).index;
                    }
                }

                System.out.println("This winner is " + players.get(playerIndex).name + " ! ");
                System.out.println("Congratulations " + players.get(playerIndex).name + " ! ");
            }

            //Checking three of a kind 
            // This is easier since we do not need to check for highcards. 
            // If two people have a threeofakind, one must definitely be bigger than the other.
            if(integerlist.get(0).score == 4)
            {
                for(int i = 0;i<threeofaKind.size();i++)
                {
                    if (scoreBiggest < threeofaKind.get(i).rank)
                    {
                        scoreBiggest = threeofaKind.get(i).rank;
                    }
                }

                for(int i = 0; i < threeofaKind.size(); i ++)
                {
                    if(scoreBiggest == threeofaKind.get(i).rank)
                    {
                        playerIndex = threeofaKind.get(i).index;
                    }
                }
                System.out.println("This winner is " + players.get(playerIndex).name + " ! ");
                System.out.println("Congratulations " + players.get(playerIndex).name + " ! ");
            }

            //Checking for 4ofakind , similar logic
            // if works easier since I have the index of players 
            if(integerlist.get(0).score == 8)
            {
                for(int i = 0;i<fourofaKind.size();i++)
                {
                    if (scoreBiggest < fourofaKind.get(i).rank)
                    {
                        scoreBiggest = fourofaKind.get(i).rank;
                    }
                }
                for(int i = 0;i<fourofaKind.size();i++)
                {
                    if (scoreBiggest == fourofaKind.get(i).rank)
                    {
                        playerIndex = fourofaKind.get(i).index;
                    }
                }

                System.out.println("This winner is " + players.get(playerIndex).name + " ! ");
                System.out.println("Congratulations " + players.get(playerIndex).name + " ! ");
            }

            //Checking for duplicate Straight, flush and straight flush, The ones with the highest card will win 
            // As such we just need to check the highcard array. 
            // If there is a tie, I have not accounted the logic for a tie 
            if(integerlist.get(0).score == 5 | integerlist.get(0).score == 6 | integerlist.get(0).score == 9)
            {
                for(int i = 0; i < integerlist.size(); i++)
                {
                    if(HighcardArray.get(integerlist.get(i).index).getrank()>scoreBiggest)
                    {
                        scoreBiggest = HighcardArray.get(integerlist.get(i).index).getrank();
                    }
                }

                for(int i = 0;i<HighcardArray.size();i++)
                {
                    if (scoreBiggest == HighcardArray.get(i).getrank())
                    {
                        playerIndex = i;
                    }
                }

                System.out.println("This winner is " + players.get(playerIndex).name + " ! ");
                System.out.println("Congratulations " + players.get(playerIndex).name + " ! ");
            }

            // Checking for a duplicate fullhouse, we just need to compare threeofakind 
            if(integerlist.get(0).score == 7)
            {
                for(int i = 0;i < threeofaKind.size(); i++)
                {
                    if (scoreBiggest < threeofaKind.get(i).rank)
                    {
                        scoreBiggest = threeofaKind.get(i).rank;
                    }
                }

                for(int i = 0; i < threeofaKind.size(); i ++)
                {
                    if(scoreBiggest == threeofaKind.get(i).rank)
                    {
                        playerIndex = threeofaKind.get(i).index;
                    }
                }
            }
        }

            //When the game ends, We give the user a choice to play again or not 
            int choice = 4;
            while(choice != 1 && choice != 2)
            {
                System.out.println("Do you wish to play again ? ");
                System.out.println("1.Yes");
                System.out.println("2.No");
                choice = selector.nextInt();
            }
            if(choice == 1)
            {
                Poker Game = new Poker();
                Game.shuffleDeck();
                Game.giveCards();
                Game.takeHands(Game.players);
                Game.printWinner();
            }
            else if(choice == 2)
            {
                System.out.println("Thank you for playing");
                System.exit(0);
            }
    }

}
