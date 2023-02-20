import lejos.nxt.Motor;
import lejos.nxt.Button;

public class Ex5{
	private static final Motor m=Motor.B;

	public static void main(String[] a){
		m.setSpeed(100);
		//while (!RConsole.isOpen())	RConsole.open();
		
		System.out.println("Avance");		
		m.forward();
		
		Button.waitForPress();
		System.out.println("Recule");		
		m.backward();
		
		Button.waitForPress();
		//m.setSpeed(0);
		m.stop();
	}
}//Ex5

