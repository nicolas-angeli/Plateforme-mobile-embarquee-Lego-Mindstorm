import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.realtime.RealtimeThread;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RelativeTime;
import lejos.realtime.PeriodicParameters;

public class RTLineLeader extends RealtimeThread{
    private boolean mode; // mode peut prendre les valeurs true ou false, par défaut false

    public RTLineLeader(int priority, long periodMillis){
        super(new PriorityParameters(priority), new PeriodicParameters(new RelativeTime(periodMillis,0)));
        this.mode = false;
    }

    public void setMode(){
        this.mode = true;
    }

    public boolean getMode(){
        return this.mode;
    }

    public static void masque(NXTLineLeader capteur){//C droit, B gauche
        int value = capteur.getResult(), vb = 200, v3 = 2*vb,
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
                case 0x08 : // 0011, tourne à droite
                    setSpeed(v3, v2);break;
                case 0x01 : // 0001, tourne fort à droite
                    setSpeed(v4, v1);break;

            case 0x00 : // 0000, stop !
                Motor.B.stop();Motor.C.stop();break;
                default ://va tout droit
                    setSpeed(vb, vb);break;
        }
    }

    public void setSpeed(final int vg, final int vd){
        Motor g=Motor.B, d=Motor.C;
        g.setSpeed(vg);
        d.setSpeed(vd);
        g.forward();
        d.forward();
    }

    @Override
    public void run(){
        NXTLineLeader captLine = new NXTLineLeader(SensorPort.S1);

    while(true){
        if(!this.mode){ // si mode == false, on execute le programme normalement
            masque(captLine);
        }
        else{ // sinon, on arrête le robot
            Motor.B.stop();
            Motor.C.stop();
        }
        waitForNextPeriod();
    }
}

public class RTPullEvent extends RealtimeThread{

	public static void main(String[] args){
		RealtimeThread t1=new RTPullEvent(16, 100);//>15(priorité RTLineLeader)
		t1.start();			
	}	
    
    public RTPullEvent(int priority, long periodMillis)
    {
        super(
            new PriorityParameters(priority),
            new PeriodicParameters(new RelativeTime(periodMillis,0))
             );
    }

    @Override
    public void run(){ 
    	SensorPort.S2.addSensorPortListener(new SensorPortListener() { 
    		public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue){ 
    			setMode();
    			} 
		}
	);	
	waitForNextPeriod();
    }// end run()
}// end class()

public class RTLauncher{

    public static void main(String[] args){

// if needed
//		while (!RConsole.isOpen()){
//			RConsole.open();
//		}

        RTLineLeader rt_ll = new RTLineLeader(15, 100);
        RTPullEvent rt_pe = new RTPullEvent(16, 100);
        
	rt_pe.start();
        rt_ll.start();
        
        try{
            rt_ll.join();
        }catch(InterruptedException e){					
            // RConsole.println("ie in launcher");				
        }

    }
}
