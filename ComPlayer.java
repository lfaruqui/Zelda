import java.util.Random;

public class ComPlayer extends Player
{
   private Random rand= new Random();

   public void comTurn(ComPlayer comPlayer, Player otherPlayer)// ai for computer; uses rng to check computer hand and use card if possible
   {
       comPlayer.setRupees(comPlayer.getTurns()+1);
       comPlayer.addToHand();
       boolean turn=false;
       while(!turn)
       {
           
           while(comPlayer.getRupees() > 0)
           {
               int rng = rand.nextInt(getPlayerHand().size()) + 1;
               Card playedCard= comPlayer.getPlayerHand().get(rng-1);
               if(comPlayer.getRupees() >= playedCard.getCost())
               {
                   playCard(playedCard, comPlayer, otherPlayer);
               }
           }
           comAttack(comPlayer, otherPlayer);
           turn=true;
       }
       System.out.print("\n Next player!\n");
       comPlayer.setTurns(comPlayer.getTurns()+2);
   }

   public void playCard(Card playedCard, ComPlayer player, Player enemyPlayer)// computer's card is added to battlefield and used
   {
       player.getBattlefield().add(playedCard);
       System.out.println("\nComputer played " + playedCard.getCardName() + "!\n");
       if(playedCard.getClass() == Triforce.class)
       {
           playedCard.attack(playedCard, player, enemyPlayer);
       }
       player.setRupees(player.getRupees()-playedCard.getCost());
       player.getPlayerHand().remove(playedCard);
   }

   public void comAttack(ComPlayer comPlayer, Player enemyPlayer)// card computer used attacks enemy or enemy unit
   {
       int count=0;
       comPlayer.getUsableCards().clear();
       comPlayer.buildUsableCards();
       boolean attack= false;
       while(!attack)
       {
           int randCard= rand.nextInt(comPlayer.getUsableCards().size())+1;
           Card attkCard= comPlayer.getUsableCards().get(randCard-1);
           attkCard.attack(attkCard, comPlayer, enemyPlayer);
           count++;
           if(count == comPlayer.getBattlefield().size())
           {
               System.out.println("Computer used its turn! \n");
               System.out.println("Computer");
               System.out.println("Health: " + comPlayer.getHealth());
               System.out.println("Rupees: " + comPlayer.getRupees());
               attack= true;
           }
       }
   }


}

