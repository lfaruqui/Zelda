import java.util.Random;
public class Bow extends Card
{
   private Random rand= new Random();
   public Bow()
   {
       setCardName("Bow And Arrow");
       setDescrip("an especially lethal item, it will destroy a unit on the field and damage your opponent for 7 points. Costs 8 Rupees.");
       setCost(8);
       setPosit(-1);
   }

   public void attack(Card bowCard, Player p1, Player enemyPlayer) //bows special attack for user
   {
       int input;
       enemyPlayer.printBField();
       System.out.println("Which card to destroy?");
       input= in.nextInt();
       enemyPlayer.setHealth(enemyPlayer.getHealth()-7);
       System.out.println("You destroyed " + enemyPlayer.getBattlefield().get(input-1).getCardName());
       System.out.println("And dealt 7 damage to " + enemyPlayer.getName());
       p1.getBattlefield().remove(bowCard);
       enemyPlayer.getBattlefield().remove(input-1);
   }

   public void attack(Card bowCard, ComPlayer comPlayer, Player enemyPlayer)//bows special attack for computer
   {
       int randCard= rand.nextInt(enemyPlayer.getBattlefield().size())+1;
       Card targetCard= enemyPlayer.getBattlefield().get(randCard-1);
       enemyPlayer.setHealth(enemyPlayer.getHealth()-7);
       System.out.println("Computer destroyed your " + targetCard.getCardName());
       System.out.println("And dealt 7 damage to you!");
       comPlayer.getBattlefield().remove(bowCard);
       enemyPlayer.getBattlefield().remove(targetCard);
   }
}
