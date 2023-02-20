import lejos.realtime.RealtimeThread;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RelativeTime;
import lejos.realtime.PeriodicParameters;
import lejos.nxt.Button;

public class Ex4 extends RealtimeThread{
	private long td;
	public static void main(String[] a){
		RealtimeThread t1 = new Ex4(new PriorityParameters(15), new PeriodicParameters(new RelativeTime(1000,0)));
		t1.start();
	}
	public Ex4(PriorityParameters priority, PeriodicParameters period){
		super(priority, period);
		this.td = System.currentTimeMillis();
	}
	@Override
	public void run(){
		while(true){
			long te = System.currentTimeMillis() - td;
			System.out.println("Temps écoulé : " + te + " ms");
			waitForNextPeriod();
		}
   	}
}
