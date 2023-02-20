

import javax.swing.SwingUtilities;
public class Test {
    
    public static void moveSlowly(final double distance, final Robot r, final int speed){
        int dir = distance>0?1:-1;
        for(int i=0; i< Math.abs(distance) ; i++){
            r.move(dir);        
            dr.refresh();
            dr.sleep(10000/speed);        
        }

    }
    
    public static void rotateSlowly(final double angle, final Robot r, final int speed){
        int dir=angle>0?1:-1;
        for(int i = 0; i<Math.abs(angle%360);i++) {
            r.rotate(dir);
            dr.refresh();
            dr.sleep(10000/speed);
        }        
    }

    private static    DrawRobots dr = new DrawRobots(); 

    public static void main(String[] args)  {
        Robot r2=new Robot(10, 15, 20);
        Robot r3=new Robot(500, 300, 200);
        dr.show();
        Robot r = new Robot(0,0,0);
        dr.addRobot(r);
        dr.addRobot(r2);
        dr.addRobot(r3);

        moveSlowly(100, r, 1000);

        dr.sleep(1000);                
        rotateSlowly(-90,r,1000);

        dr.sleep(1000);
        moveSlowly(100, r, 1000);

        dr.sleep(1000);                
        rotateSlowly(45,r,1000);

        dr.sleep(1000);
        moveSlowly(100,r,1000);

        dr.sleep(1000);
        moveSlowly(-100,r,1000);

        dr.sleep(1000);
        dr.hide();

    }
}
