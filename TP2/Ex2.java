import lejos.nxt.Battery;
import lejos.nxt.Button;
public class Ex2{
    public static void main (String[] args) {
	float f = Battery.getVoltage();
        
	System.out.println("Battery  : " + f*10 + "%");
              
	Button.waitForPress();
    }

}
