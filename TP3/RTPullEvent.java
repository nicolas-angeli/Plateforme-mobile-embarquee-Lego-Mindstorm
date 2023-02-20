import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.realtime.RealtimeThread;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RelativeTime;
import lejos.realtime.PeriodicParameters;
import lejos.nxt.TouchSensor;

public class RTPullEvent extends RealtimeThread{


    private final RTLineLeader leader;
    
    public RTPullEvent(int priority, long periodMillis, RTLineLeader leader)
    {
        super(
            new PriorityParameters(priority),
            new PeriodicParameters(new RelativeTime(periodMillis,0))
             );
            this.leader=leader;
    }
    
    private final TouchSensor capteur = new TouchSensor(SensorPort.S2);  
    private final  Motor mL = Motor.B;
    private final  Motor mR = Motor.C;
    
    
    @Override
    public void run(){ 
    	while(true){
    	
    	
 
    	

    			if(capteur.isPressed()){ 
    			//mR.stop();mR.stop();while(true){}
    			   		leader.toggleMode();
    			}

    		waitForNextPeriod();
    	} 
	
    }// end run()
}// end class()
