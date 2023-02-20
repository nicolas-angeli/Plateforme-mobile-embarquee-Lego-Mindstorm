import lejos.nxt.Motor;
import lejos.nxt.Button;
import lejos.realtime.RealtimeThread;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RelativeTime;
import lejos.realtime.PeriodicParameters;

public class Ex6{
	private Motor m=Motor.B;
	public static void main(String[] args) {
		// création des threads
		RealtimeThread t1 = new TM(new PriorityParameters(15), new PeriodicParameters(new RelativeTime(1000, 0)));
		RealtimeThread t2 = new TA(new PriorityParameters(14));
		
		// démarrage des threads
		t1.start();
		t2.start();
	}
}

class TM extends RealtimeThread {
	private Motor m=Motor.B;
	private long td;
	public TM(PriorityParameters priority, PeriodicParameters periodic) {
		super(priority, periodic);
		m.setSpeed(100);
		m.forward();
		this.td = System.currentTimeMillis();
	}
	
	@Override
	public void run() {
	//while (!RConsole.isOpen())	RConsole.open();
		if (m.isMoving())	System.out.println("Le moteur tourne");
		while(true){
			long te = System.currentTimeMillis() - td;
			if (m.isMoving())System.out.println("Le moteur tourne depuis : " + te + " ms");
			waitForNextPeriod();
		}
	}
}

class TA extends RealtimeThread {
	private Motor m=Motor.B;
	public TA(PriorityParameters priority) {
		super(priority);
	}
	
	@Override
	public void run() {
		//while (!RConsole.isOpen())	RConsole.open();
		Button.waitForPress();
		m.stop();
		System.out.println("Le moteur ne tourne plus");
		Button.waitForPress();
		//System.exit(0);
	}
}
