import java.util.Random;

public class MasterSword extends Card
{
   private Random rand= new Random();

   public MasterSword()
   {
       setCardName("Master Sword");
       setDescrip("a useful item, don't travel without it, it can give +4 to a unit's damage. Costs 5 Rupees.");
       setCost(5);
       setPosit(-1);
   }

   public void attack(Card msCard, Player p1, Player enemyPlayer)//Mastersord special move for user
   {
       int input;
       p1.printBField();
       System.out.println("Which card to add 4 damage and 2 life to?");
       input= in.nextInt();
       Card card= p1.getBattlefield().get(input-1);
       card.setAttack(card.getAttack()+4);
       card.setLife(card.getLife()+2);
       System.out.println("You gave " + card.getCardName() + " the Master Sword!");
       p1.getBattlefield().remove(msCard);
   }

   public void attack(Card msCard, ComPlayer comPlayer, Player enemyPlayer)//Mastersord special move for computer
   {
       Card card= comPlayer.getBattlefield().get(rand.nextInt(comPlayer.getBattlefield().size()+1));
       card.setAttack(card.getAttack()+4);
       card.setLife(card.getLife()+2);
       System.out.println("You gave " + card.getCardName() + " the Master Sword!");
       comPlayer.getBattlefield().remove(msCard);
   }
}

