import lejos.nxt.*;

public class Ex1 {

    public static final Motor LM = Motor.C;
    public static final Motor RM = Motor.B;


    public static final double R       = 126.0/1000;   // 12,6cm
    public static final double s        = 5.0/100;  // 5cm/sec
    public static final double dw        = 14.0/100; // 14cm 
    public static final double Rw     = 2.0/100;  // 2cm 

    public static void main (String[] args) {

        System.out.println("Drive Circle");
        Button.waitForPress();

        double lws = ((R-dw/2)*(s/R))/Rw;
        double rws = ((R+dw/2)*(s/R))/Rw;

        int lwsd = (int)Math.toDegrees(lws); 
        int rwsd = (int)Math.toDegrees(rws); 

        LM.setSpeed(lwsd); // degree/sec
        RM.setSpeed(rwsd);

        LM.forward();
        RM.forward();

        Button.waitForPress();

    }
}
