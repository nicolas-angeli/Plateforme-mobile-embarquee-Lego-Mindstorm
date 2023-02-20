import lejos.nxt.Button;
import lejos.nxt.Battery;
public class Ex3{
    public static void main (String[] args) {
    	/*while (!RConsole.isOpen()){
		RConsole.open();
	}*/
	if(Battery.isRechargeable()){
		System.out.println("La batterie est rechargeable");
		
	}
	else{
		System.out.println("La batterie n'est pas rechargeable");
	}
	Button.waitForPress();
   }
}
