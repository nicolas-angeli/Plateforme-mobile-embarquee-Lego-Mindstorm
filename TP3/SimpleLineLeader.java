import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class SimpleLineLeader{
	public static void main(String[] args){
		NXTLineLeader captLine = new NXTLineLeader(SensorPort.S1);
		while(true){
			masque(captLine);
			try{
				Thread.sleep(100);}catch(InterruptedException e){}
		}
    			

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
		        case 0x03 : // 0011, tourne à droite
		        	setSpeed(v3, v2);break;
        		case 0x01 : // 0001, tourne fort à droite
	        		setSpeed(v4, v1);break;
	        		
			case 0x00 : // 0000, stop !
				Motor.B.stop();Motor.C.stop();break;
		        default ://va tout droit
		        	setSpeed(vb, vb);break;
		}
	}
	
	public static void setSpeed(final int vg, final int vd){
		Motor g=Motor.B, d=Motor.C;
		g.setSpeed(vg);
		d.setSpeed(vd);
		g.forward();
		d.forward();
	}		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
