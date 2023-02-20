import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.realtime.RealtimeThread;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RelativeTime;
import lejos.realtime.PeriodicParameters;

public class RTLineLeader extends RealtimeThread{

	public void masque(){//C droit, B gauche
		int value = capteur.getResult();

		int vb = 200, v3 = 2*vb,
		 v2 = vb - vb/3, v4 = 4*vb, v1 = vb - (4*vb)/5;
		
		switch( (value&0x3C) >>>2 ){
			case 0x0C : // 1100, tourne à gauche
				setSpeed(v2, v3);break;
	        	case 0x0E : // 1110, tourne à gauche
	        		setSpeed(v2, v3);break;
		        case 0x08 : // 1000, tourne fort à gauche
		        	setSpeed(v1, v4);break;
		        	
	        	case 0x07 : // 0111, tourne à droite
	        		setSpeed(v3, v2);break;
		        case 0x03 : // 0011, tourne à droite
		        	setSpeed(v3, v2);break;
        		case 0x01 : // 0001, tourne fort à droite
	        		setSpeed(v4, v1);break;
	        		
			case 0x00 : // 0000, stop !
				mL.stop();mR.stop();break;
		        default ://va tout droit
		        	setSpeed(vb, vb);break;
		}
	}
	
	public  void setSpeed(final int vg, final int vd){	
		mL.setSpeed(vg);
		mR.setSpeed(vd);
		mL.forward();
		mR.forward();

	}		
    
    public RTLineLeader(int priority, long periodMillis)
    {
        super(
            new PriorityParameters(priority),
            new PeriodicParameters(new RelativeTime(periodMillis,0))
             );
    }

    private final  NXTLineLeader capteur = new NXTLineLeader(SensorPort.S1);
    private final  Motor mL = Motor.B;
    private final  Motor mR = Motor.C;
    
    private boolean mode = true;
    
    public void toggleMode()
    {
    	if(mode)mode=false;
    	else mode=true;
    }
    
    @Override
    public void run(){
        

	while(true){
		if(mode){
			masque();
		}
		else{
		mL.stop();mR.stop();
			
		}
		waitForNextPeriod();
	}
    }// end run()
}// end class()
