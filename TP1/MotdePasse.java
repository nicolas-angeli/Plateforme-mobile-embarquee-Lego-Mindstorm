import java.util.Scanner;
import javax.swing.JTextField;

public class MotdePasse implements Runnable
{
    private static boolean m=false;

    public static void main(String[] p){
        Runnable r1 = new MotdePasse();
        Thread t1 = new Thread(r1);
        t1.start();

        String mdp="";
        Scanner pInput = new Scanner( System.in );
        while(!m){
            mdp = pInput.nextLine();
            if(mdp.equals("motdepasse")){
                System.out.println("Code correct");
                System.exit(0);
            }                
            System.out.println("Code incorrect");
        }
    }

    public void run()
    {
        System.out.println("Vous avez 10 secondes !");
        System.out.println("Entrez le mot de passe");

        try{
            Thread.sleep(10000);            
            System.exit(0);
        }
        catch(Exception e){
        }
    }
}

