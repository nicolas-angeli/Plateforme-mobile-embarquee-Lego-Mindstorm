import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;

public class RTLauncher{


	public static void calibrate()
	{
	NXTLineLeader lineLeaderSensor = new NXTLineLeader(SensorPort.S1);
		
		LCD.clear();
		LCD.drawString("MindSensor calibration ", 0, 0) ;
		LCD.drawString("LineLeader " + lineLeaderSensor.getVersion(), 0, 1);
		
		lineLeaderSensor.wakeUp();
		
		LCD.drawString("White + Push button", 0, 5);
		Button.waitForPress();
		lineLeaderSensor.calibrate(NXTLineLeader.LineColor.WHITE) ;

		LCD.drawString("Black + Push button", 0, 5);
		Button.waitForPress();
		lineLeaderSensor.calibrate(NXTLineLeader.LineColor.BLACK) ;

		LCD.clear();
		LCD.drawString("Push button", 0, 0);
		Button.waitForPress();

	}


    public static void main(String[] args){

// if needed
//		while (!RConsole.isOpen()){
//			RConsole.open();
//		}


	calibrate();

        RTLineLeader rt_ll = new RTLineLeader(15, 100);
        RTPullEvent rt_pe = new RTPullEvent(16, 100, rt_ll);
        
	rt_pe.start();
        rt_ll.start();
        
        try{
          rt_ll.join();
          rt_pe.join();
        }catch(InterruptedException e){					
            // RConsole.println("ie in launcher");
            System.out.println("coucou\n");				
        }

    }
}
