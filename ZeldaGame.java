import java.util.Scanner;
import java.util.Random;

public class ZeldaGame
{
   private Random rand= new Random();
   private Scanner in = new Scanner(System.in);
   private int input;

   public void twoPlayerGame(Player player1, Player player2)// for multiplayer; chooses who goes first with rng an goes through round
   {
       if (rand.nextInt(2) == 0)
       {
           while(player1.getHealth() > 0 && player2.getHealth() > 0)
           {
               twoPlayerGameTurn(player1, player2);
               twoPlayerGameTurn(player2,player1);
           }
       }
       else
       {
           while(player1.getHealth() > 0 && player2.getHealth() > 0)
           {
               twoPlayerGameTurn(player2, player1);
               twoPlayerGameTurn(player1,player2);
           }
       }

       if(player2.getHealth() > player1.getHealth())
       {
           System.out.println(player2.getName() + " wins!");
       }
       else
       {
           System.out.println(player1.getName() + " wins!");
       }
   }

   public void onePlayerGame(Player player1, ComPlayer player2)// playing against computer; runs game until either wins
   {
       while(player1.getHealth() > 0 && player2.getHealth() > 0)
       {
           onePlayerGameTurn(player1,player2);
           player2.comTurn(player2,player1);
       }

       if(player2.getHealth() > player1.getHealth())
       {
           System.out.println(player2.getName() + " wins!");
       }
       else
       {
           System.out.println(player1.getName() + " wins!");
       }
   }


   public void twoPlayerGameTurn(Player player, Player otherPlayer)// single round for multiplayer game including prompts and actions
   {
       player.setRupees(player.getTurns()+1);
       boolean turn = false;
       while(!turn)
       {
           gamePrompts(player, otherPlayer);
           inspectCards(player);
           playCards(player, otherPlayer);
           useCards(player, otherPlayer);
           turn =true;
       }
       checkHealth(otherPlayer);
       player.setTurns(player.getTurns()+2);
   }

   public void onePlayerGameTurn(Player player, ComPlayer comPlayer)// user vs computer game w/ prompts and actions
   {
       player.setRupees(player.getTurns()+1);
       boolean turn = false;
       while(!turn)
       {
           gamePrompts(player, comPlayer);
           inspectCards(player);
           playCards(player,comPlayer);
           useCards(player, comPlayer);
           turn =true;
       }
       checkHealth(comPlayer);
       player.setTurns(player.getTurns()+2);
   }

   public void gamePrompts(Player player, Player enemyPlayer)// prompts for game terminal output
   {
       System.out.println("\n" + player.getName() + "\n Health: " + player.getHealth() + "\n Rupees: " + player.getRupees());
       System.out.println("\nBattlefield: \n You've played: ");
       player.printBField();
       System.out.println("\n" + enemyPlayer.getName() + " has played: ");
       enemyPlayer.printBField();
       player.addToHand();
       System.out.println("\nYour hand is:");
       player.printPlayerHand();
   }

   public void inspectCards(Player player)//allows user to look at card descriptions in hand
   {
       boolean inspect = false;
       while (!inspect)
       {
           System.out.println("\nAny cards to inspect?  (Enter its number, -1 to stop)");
           input=in.nextInt();
           if(input == -1)
           {
               inspect=true;
           }
           else if(input > 0)
           {
               System.out.println(player.getPlayerHand().get(input- 1).getDescrip());
           }
           else
           {
               System.out.println("Please enter acceptable input");
               input=in.nextInt();
           }
       }
   }

   public void playCards(Player player, Player enemyPlayer)//plays chosen card 
   {
       boolean playCards= false;
       while(!playCards)
       {
           System.out.println("\nWhich card(s) to play?   (Enter number, -1 to stop)");
           input = in.nextInt();
           if(input == -1)
           {
               playCards=true;
           }
           else if(player.getRupees() < player.getPlayerHand().get(input-1).getCost())
           {
               System.out.println("Not enough rupees for " + player.getPlayerHand().get(input - 1).getCardName() + "!");
               System.out.println("Pick another card:   (Enter number,-1 to stop)");
               input = in.nextInt();
           }
           else if(input > -1 && input <= player.getPlayerHand().size())
           {
               Card card= player.getPlayerHand().get(input-1);
               player.playCard(card, player, enemyPlayer);
               System.out.println("\nPlayed cards: ");
               player.printBField();
           }
           else
           {
               System.out.println("Please enter acceptable input");
               input=in.nextInt();
           }
       }
   }

   public void useCards(Player player, Player enemyPlayer)// takes played card and checks if they are usable, then the user chooses how to use it
   {
       boolean useCards= false;
       int count=0;
       player.getUsableCards().clear();
       player.buildUsableCards();
       while(!useCards)
       {
           if(player.getBattlefield().size()  == 0)
           {
               System.out.print("No cards to play!");
               break;
           }
           System.out.print("\nUsable cards: \n");
           player.printUsableCards();
           System.out.println("\nWant to use any cards?  (Enter number, -1 to stop)");
           input = in.nextInt();
           if(input == -1)
           {
               useCards = true;
           }
           else if(input > -1 && input <= player.getUsableCards().size())
           {
               Card attkCard= player.getUsableCards().get(input-1);
               attkCard.attack(attkCard, player, enemyPlayer);
               player.getUsableCards().remove(attkCard);
               count++;
               if(count == player.getBattlefield().size())
               {
                   System.out.println("Used all of your cards!");
                   useCards=true;
               }
           }
           else
           {
               System.out.println("Please enter acceptable input");
               input=in.nextInt();
           }
       }
   }

   public void checkHealth(Player player)//  checks health of player and prints winner
   {
       if(player.getHealth() == 0)
       {
           System.out.println("You won the game!");
       }
   }
}

