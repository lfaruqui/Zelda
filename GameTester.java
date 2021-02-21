import java.util.Random;
import java.util.Scanner;
public class GameTester
{

   public static void main(String[] args)
   {
       ZeldaGame game= new ZeldaGame();
       Scanner in= new Scanner(System.in);
       System.out.println("Will you be playing: \t(Enter Number)\n" + "1. With someone\n" + "2. Against Computer");
       int input = in.nextInt();
       if(input == 1) //if input is 1 it becomes a 2 player game with 2 users
       {
           Player player1= new Player();
           player1.setName();
           Player player2= new Player();
           player2.setName();
           game.twoPlayerGame(player1,player2);
       }
       else if(input == 2)//if input is 2 user will play against computer
       {
           Player player1 = new Player();
           player1.setName();
           ComPlayer playerCom= new ComPlayer();
           playerCom.setName("Computer");
           game.onePlayerGame(player1, playerCom);
       }
       else
       {
           System.out.println("Please input correct number"); //prompt if invalid input is used
       }
   }
}
