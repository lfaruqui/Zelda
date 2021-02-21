import java.util.ArrayList;
import java.util.Scanner;

public class Player
{
   private Scanner in = new Scanner(System.in);
   private GameDecks deck= new GameDecks();
   private int health;
   private int rupees;
   private int turns;
   private String name;
   private ArrayList<Card> playerDeck= new ArrayList<>();
   private ArrayList<Card> playerHand= new ArrayList<>();
   private ArrayList<Card> battlefield= new ArrayList<>();
   private ArrayList<Card> usableCards= new ArrayList<>();

   public Player()// cosntructs player with default values
   {
       health= 30;
       createDeck();
       deck.shuffleDeck(getDeck());
       createHand();
   }

   public String getName()// returns string name of player
   {
       return name;
   }

   public void setName()// prompts user to input name
   {
       System.out.println("Enter player name: ");
       name= in.next();
   }

   public void setName(String newName)// sets name field with string value
   {
       name= newName;
   }

   public int getHealth()// return integer value of player health
   {
       return health;
   }

   public void setHealth(int newHealth)// sets health field to new health
   {
       health= newHealth;
   }

   public int getTurns()// returns number of turns player has
   {
       return turns;
   }

   public void setTurns(int num)//sets number of terms
   {
       turns= num;
   }

   public int getRupees()// returns integer value of rupees
   {
       return rupees;
   }

   public void setRupees(int num)// set number of rupees 
   {
       rupees= num;
   }

   public void createDeck()// initalizes player's deck
   {
       deck.buildDeck(playerDeck);
   }

   public ArrayList<Card> getDeck() // returns player's deck
   {
       return playerDeck;
   }

   public ArrayList<Card> createHand()// fills hand with 5 cards from deck and removes 5 previous cards
   {
       for(int i=0; i < 5; i++)
       {
           playerHand.add(playerDeck.get(i));
       }
       for(int i=0; i < 5; i++)
       {
           playerDeck.remove(i);
       }
       return playerHand;
   }

   public ArrayList<Card> getPlayerHand() // return player's hand
   {
       return playerHand;
   }

   public void printPlayerHand()// uses for loop to print player's hand
   {
       for(int i=0; i < playerHand.size(); i++)
       {
           System.out.println((i + 1) + ". " + playerHand.get(i).getCardName() + "   ");
       }
   }

   public void addToHand()// adds 2 cards to player hand and removes 2 cards from player hand
   {
          int i=5;
          playerHand.add(playerDeck.get(i));
          playerHand.add(playerDeck.get(i+1));
          playerDeck.remove(i);
          playerDeck.remove(i+1);
   }

   public void printBField()// checks battlefield size and prints units of battlefield if there are any present
   {
       if(battlefield.size() == 0)
       {
           System.out.println("No units yet!");
       }
       else
       {
           for (int i = 0; i < battlefield.size(); i++)
           {
               System.out.println((i + 1) + ". " + battlefield.get(i).getCardName() + "   ");
           }
       }
   }

   public ArrayList<Card> getBattlefield()// returns battlefield
   {
       return battlefield;
   }

   public void playCard(Card playedCard, Player player, Player enemyPlayer)// user-chosen card is put into battlefield and is used
   {
       battlefield.add(playedCard);
       System.out.println("You played " + playedCard.getCardName() + "!\n");
       if(playedCard.getClass() == Triforce.class)
       {
           playedCard.attack(playedCard, player, enemyPlayer);
       }
       setRupees(rupees-playedCard.getCost());
       playerHand.remove(playedCard);
       System.out.println("Rupees: " + getRupees());
       System.out.println("Hand: ");
       printPlayerHand();
   }

   public void buildUsableCards()//checks current lineup on battlefield to see if cards are usable
   {
       for (Card i : battlefield)
       {
           if(!i.getCardName().equals("Triforce"))
           {
               usableCards.add(i);
           }
       }
   }

   public ArrayList<Card> getUsableCards()// returns cards that are usable
   {
       return usableCards;
   }

   public void printUsableCards()// prints cards that the user can use
   {
       if(usableCards.size() == 0)
       {
           System.out.println("No units to attack with or items to use!");
       }
       for(int i=0; i < usableCards.size(); i++)
       {
           System.out.println((i+1) + ". " + usableCards.get(i).getCardName() + " ");
       }
   }
}
