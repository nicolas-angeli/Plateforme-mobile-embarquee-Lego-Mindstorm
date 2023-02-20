import lejos.nxt.Motor;
import lejos.realtime.RealtimeThread;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RelativeTime;
import lejos.realtime.PeriodicParameters;
import lejos.nxt.Button;

public class Ex2 extends RealtimeThread{

	public static final Motor LM = Motor.C, RM = Motor.B;
	public static double dl=dk(LM), dr=dk(RM), pi=Math.PI;
	public static double Rw = 2.0/100, dw = 14.0/100, s=5.0/100, x0=0, y0=0;
	public static double R=dw*(dr+dl)/(2*(dr-dl));//dr!=dl
	public static double d0=(dl+dr)/(2*R), d=(dl+dr)/2, x=R, y=0, a=0;
	
	public Ex2(){
		super(new PriorityParameters(15), new PeriodicParameters(new RelativeTime(1000, 0)));
	}
	
	public static double dk(Motor m){
		return m.getTachoCount()*pi/180;
	}
	
	public static void coord(){
		System.out.println("x = "+x+"\n"+"y = "+y);//"angle = "+a+"\n"+
		dl=dk(LM); dr=dk(RM);
		if(dl==dr){
			y+=dr*Math.sin(a);
			x+=dr*Math.cos(a);
			if(R==0){
				R+=dr;
				a+=pi;
			}
			else R+=dr;

		}
		else{
			R=dw*(dr+dl)/(2*(dr-dl));//dr!=dl
			d0=(dl+dr)/(2*R);
			a+=d0;

			x=R*Math.cos(a);//-pi/2 ?
			y=R*Math.sin(a);//-pi/2 ?
		}
	}

	public static void main (String[] args) {
		System.out.println("Drive Circle");
		Button.waitForPress();
		
		/*LM.setSpeed(10); // degree/sec
		RM.setSpeed(100);*/
		R       = 126.0/1000;   // 12,6cm
		s        = 5.0/100;  // 5cm/sec
		dw        = 14.0/100; // 14cm 
		Rw     = 2.0/100;  // 2cm 
		
		double lws = ((R-dw/2)*(s/R))/Rw;
		double rws = ((R+dw/2)*(s/R))/Rw;

		int lwsd = (int)Math.toDegrees(lws); 
		int rwsd = (int)Math.toDegrees(rws); 

		LM.setSpeed(lwsd); // degree/sec
		RM.setSpeed(rwsd);

		LM.forward();
		RM.forward();
		

		RealtimeThread t1 = new Ex2();
		t1.start();
		Button.waitForPress();
	}
		
	@Override
	public void run(){
		
		while(true){
			coord();

			double lws = (R-dw/2)*(s/R)/Rw;
			double rws = (R+dw/2)*(s/R)/Rw;

			int lwsd = (int)Math.toDegrees(lws);
			int rwsd = (int)Math.toDegrees(rws);

			LM.setSpeed(lwsd); // degree/sec
			RM.setSpeed(rwsd);
			
			waitForNextPeriod();
		}
	}
}
