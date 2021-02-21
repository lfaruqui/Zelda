public class Triforce extends Card
{
   public Triforce()
   {
       setCardName("Triforce");
       setDescrip("this ancient is most helpful, play all 3 triangles and emerge victorious! Costs 9 Rupees.");
       setCost(9);
       setPosit(-1);
   }

   public void attack(Card triCard, Player p1, Player enemyPlayer)//Triforce for user
   {
       int num = 0;
       for(Card i : p1.getBattlefield())
       {
           if(i.getCardName().equals("Triforce"))
           {
               num ++;
           }
       }
       if(num>=3 )
       {
           enemyPlayer.setHealth(0);
       }
   }

   public void attack(Card triCard, ComPlayer comPlayer, Player enemyPlayer)//Triforce for computer
   {
       int num = 0;
       for(Card i : comPlayer.getBattlefield())
       {
           if(i.getCardName().equals("Triforce"))
           {
               num ++;
           }
       }
       if(num>=3 )
       {
           enemyPlayer.setHealth(0);
       }
   }


}

