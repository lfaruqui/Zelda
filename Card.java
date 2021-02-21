import java.util.Random;
import java.util.Scanner;

public class Card
{
   public Scanner in = new Scanner(System.in);
   private Random rand= new Random();
   private String cardName;
   private String descrip;
   private int attack;
   private int life;
   private int cost;
   private int posit;

   public Card()//constructs card and assigns default values
   {
       cardName="";
       descrip="";
       life= 0;
       attack=0;
       cost= 0;
       posit=-1;
   }

   public void setCardName(String name) // sets card name 
   {
       cardName= name;
   }

   public String getCardName() //  returns string name of card
   {
       return cardName;
   }

   public void setDescrip(String des) // sets description of cards
   {
       descrip= "Ah yes, " + cardName + ", " + des;
   }

   public String getDescrip() // returns string description of a certain card
   {
       return descrip;
   }

   public void setAttack(int attck) // sets integer value for attack
   {
       attack= attck;
   }

   public int getAttack()// returns integer value of attack
   {
       return attack;
   }

   public void setLife(int lifeValue)// sets life value of card with integer value
   {
       life= lifeValue;
   }

   public int getLife()// returns integer life value
   {
       return life;
   }

   public void setCost(int newCost)// sets cost of card with new integer value
   {
       cost= newCost;
   }

   public int getCost()// returns integer value of cost
   {
       return cost;
   }

   public void setPosit(int pos)// set position of card
   {
       posit= pos;
   }

   public int getPosit()// return integer value of position of card
   {
       return posit;
   }

   public String checkCardLife(Player player, Card target)//checks to see if card life is greater than 0
   {
       if(target.getLife() <= 0)
       {
           player.getBattlefield().remove(target);
           return target.getCardName() + " was defeated!";
       }
       else
       {
           return target.getCardName() + " still stands!";
       }
   }

   public void attack(Card usedCard, Player player, Player otherPlayer)//uses attack value of card on either other player or other players unit
   {
       System.out.println("Do you want to:   1. Attack the player     2. Attack one of their units (Enter Number)");
       if(in.nextInt() == 1)
       {
           otherPlayer.setHealth(otherPlayer.getHealth()-getAttack());
           System.out.println("You attacked the other player!");
       }
       else if(in.nextInt() == 2)
       {
           int targetCardNum;
           Card targetCard;
           System.out.println("Cards to attack: ");
           otherPlayer.printBField();
           System.out.println("Which card?  (Enter its number)");
           targetCardNum= in.nextInt()-1;
           targetCard= otherPlayer.getBattlefield().get(targetCardNum);
           targetCard.setLife(targetCard.getLife()-usedCard.getAttack());
           usedCard.setLife(usedCard.getLife()-targetCard.getAttack());
           System.out.println("You attacked " + targetCard.getCardName() + "!");
           System.out.println("Their " + checkCardLife(otherPlayer, targetCard));
           System.out.println("Your " + checkCardLife(player, usedCard));
       }
   }

   public void attack(Card usedCard, ComPlayer comPlayer, Player otherPlayer)//uses attack value of card on computer or tcomputers units
   {
       if(rand.nextInt(2) == 0)
       {
           otherPlayer.setHealth(otherPlayer.getHealth()-usedCard.getAttack());
           System.out.println("Computer attacked you!");
       }
       else
       {
           if(otherPlayer.getBattlefield().size() == 0)
           {
               System.out.println("Computer passed its turn!");
           }
           else
           {
               int randCard= rand.nextInt(otherPlayer.getBattlefield().size())+1;
               Card targetCard= otherPlayer.getBattlefield().get(randCard-1);
               targetCard.setLife(targetCard.getLife()-usedCard.getAttack());
               usedCard.setLife(usedCard.getLife()-targetCard.getAttack());
               System.out.println("Computer attacked your " + targetCard.getCardName() + "!");
               System.out.println("Your " + targetCard.checkCardLife(otherPlayer, targetCard));
               System.out.println("Computer's " + usedCard.checkCardLife(comPlayer, usedCard));
               comPlayer.getUsableCards().remove(usedCard);
           }
       }
   }

}

