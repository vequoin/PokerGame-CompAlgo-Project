import java.util.*;

import javax.lang.model.util.ElementScanner14;
public class Testing {

    public static void main(String[] args) {

    
        /*ArrayList<ArrayList<Integer>>kuku = new ArrayList<ArrayList<Integer>>();
        List<Rankindex<player,Integer>> listcc = new ArrayList<Rankindex<player,Integer>>();
        ArrayList<Cardtest> hand = new ArrayList<>();
        ArrayList<Cardtest> hand1 = new ArrayList<>();
        ArrayList<Cardtest> hand2 = new ArrayList<>();
        ArrayList<Cardtest> hand3 = new ArrayList<>();
        ArrayList<Cardtest> hand4 = new ArrayList<>();
        ArrayList<Cardtest> hand5 = new ArrayList<>();
        ArrayList<Cardtest> hand6 = new ArrayList<>();
        DeckofCards deck = new DeckofCards();
        player player1 = new player("Cassandra", hand);
        player player2 = new player("Rohan", hand2);
        player player3 = new player("Joshua",hand3);
        player player4 = new player("Chen",hand1);
        player player5 = new player("Ledecky", hand4);
        player player6 = new player("Korvin",hand5);
        player player7 = new player ("Tatiana", hand6);
        Cardtest Card1 = new Cardtest();
        Cardtest Card2 = new Cardtest();
        Cardtest Card3 = new Cardtest();
        Cardtest Card4 = new Cardtest();
        Cardtest Card5 = new Cardtest();
        Cardtest Card6 = new Cardtest();
        Cardtest Card7 = new Cardtest();
        Cardtest Card8 = new Cardtest();
        Cardtest Card9 = new Cardtest();
        Cardtest Card10 = new Cardtest();
        Card1.setfaceName("3");
        Card1.setrank(3);
        Card1.setSuit("Spades");
        Card2.setfaceName("Queen");
        Card2.setrank(12);
        Card2.setSuit("Spades");
        Card3.setfaceName("King");
        Card3.setrank(13);
        Card3.setSuit("Spades");
        Card4.setfaceName("10");
        Card4.setrank(10);
        Card4.setSuit("Spades");
        Card5.setfaceName("Jack");
        Card5.setrank(11);
        Card5.setSuit("Spades");
        Card6.setfaceName("3");
        Card6.setrank(3);
        Card6.setSuit("Heart");
        Card7.setfaceName("Queen");
        Card7.setrank(12);
        Card7.setSuit("Hearts");
        Card8.setfaceName("King");
        Card8.setrank(13);
        Card8.setSuit("Hearts");
        Card9.setfaceName("10");
        Card9.setrank(10);
        Card9.setSuit("Hearts");
        Card10.setfaceName("9");
        Card10.setrank(9);
        Card10.setSuit("Hearts");
        player6.hand.add(Card1);
        player6.hand.add(Card2);
        player6.hand.add(Card3);
        player6.hand.add(Card4);
        player6.hand.add(Card5);
        player7.hand.add(Card6);
        player7.hand.add(Card7);
        player7.hand.add(Card8);
        player7.hand.add(Card9);
        player7.hand.add(Card10);
        Collections.shuffle(deck.getDeck());
        for ( int i = 0 ; i < 5; i ++)
        {
            player1.draw(deck);
            player2.draw(deck);
            player3.draw(deck);
            player4.draw(deck);
            player5.draw(deck);
        }
        //listcc.add( new Rankindex<player,Integer>(player1,0));
        //listcc.add(new Rankindex<player,Integer>(player2, 1));
        //listcc.add( new Rankindex<player,Integer>(player3, 2));
        //listcc.add( new Rankindex<player,Integer>(player4, 3));
        //listcc.add( new Rankindex<player,Integer>(player5, 4));
        listcc.add( new Rankindex<player,Integer>(player6, 5));
        listcc.add( new Rankindex<player,Integer>(player7, 6));
        int tempLargest = 0;
        int g = 0;
        for(int i = 0; i < 2; i ++)
        {
            listcc.get(i).rank.showHand();
            System.out.println("Card of other person: ");
        }
        for( int i = 0 ; i < listcc.get(0).rank.hand.size();i++)
                 {
                     for(int j = 0;j < listcc.size();j++)
                     {
                        
                        if(listcc.get(j).rank.hand.get(i).getrank() > tempLargest)
                        {
                            tempLargest = listcc.get(j).rank.hand.get(i).getrank();
                            System.out.println("Inside function");
                            System.out.println(tempLargest);
                        }
                        if(j == listcc.size()-1)
                        {
                        System.out.println("Outside function");
                        System.out.println(tempLargest);
                            
                               for(int l = 0; l < listcc.size(); l++)
                               {
                                   if(listcc.size() == 1)
                                   {
                                       break;
                                   }
                                   if(tempLargest == listcc.get(l).rank.hand.get(i).getrank())
                                   {
                                       System.out.println(listcc.get(l).rank.hand.get(i).getrank());
                                        System.out.println("nothing");
                                   }
                                   else
                                   {
                                       System.out.println(l);
                                       listcc.remove(l);
                                       System.out.println("here now");
                                       l--;
                                   }
                               }
                            tempLargest = 0;
                        }
                     }
                }
            listcc.get(0).rank.showHand();
            System.out.println(listcc);
        
        /*for(Cardtest c: player1.hand)
        {
            if(testings.containsKey(c.getfaceName()))
            {
                testings.put(c.getfaceName(),testings.get(c.getfaceName())+ 1);
            }
            else
            {
                testings.put(c.getfaceName(),1);
            }
        }

        List<Rankindex<String,Integer>>sortedplayer = new  ArrayList<Rankindex<String,Integer>>();

        if(testings.values().contains(2))
            {
                sortedplayer.add(new Rankindex<String,Integer>(getKeyFromValue(testings, 2), 2));
            }
        System.out.println(sortedplayer);
        System.out.println(getKeyFromValue(testings, 2));*/
        
        Poker Game1 = new Poker();
        Game1.shuffleDeck();
        Game1.giveCards();
        Game1.takeHands(Game1.players);
        Game1.printWinner();
        /*
        ArrayList<Cardtest> hand = new ArrayList<>();
        DeckofCards deck = new DeckofCards();
        Collections.shuffle(deck.getDeck());
        Cardtest c = deck.drawCard();
        Cardtest d = deck.drawCard();
        Cardtest e = deck.drawCard();
        player player1 = new player("Ronak", hand);
        for ( int i = 0 ; i < 5; i ++)
        {
            player1.draw(deck);
        }
        System.out.println(player1.hand.size());
        //System.out.println(c.getfaceName() + " of " + c.getSuit());
        //System.out.println(d.getfaceName() + " of " + d.getSuit());
        //System.out.println(e.getfaceName() + " of " + e.getSuit());
        player1.showHand();
        /*for ( int j = 0 ; j < player1.hand.size(); j ++)
        {
            //System.out.println(player1.hand.get(j).getfaceName() + " of " + player1.hand.get(j).getSuit());
        }
        Map<String, Integer> pair1 = new HashMap<String, Integer>();
        for(Cardtest car: player1.hand)
        {
            if(pair1.containsKey(car.getfaceName()))
            {
                pair1.put(car.getfaceName(), pair1.get(car.getfaceName()) + 1);
            }
            else
            {
                pair1.put(car.getfaceName(),1);
            }
        }
        System.out.println(pair1);
        for (int i = 0 ; i < 52; i++)
        {
        System.out.println(deck.getDeck().get(i).getfaceName() + " of " + deck.getDeck().get(i).getSuit());
        }*/
    }
}
