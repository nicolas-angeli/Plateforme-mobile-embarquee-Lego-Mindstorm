
public class Robot implements RobotInterface{
    public static void main(String[] arg){
        Robot.test();
    }
    
    public static double pi=Math.PI;
    private double aX;
    private double aY;
    private double a;
    public static int n=0;

    public Robot(final double pX, final double pY, final double pA){
        this.aX = pX;
        this.aY = pY;
        this.a = pA;
        n++;
    }

    /*public double modulo(final double a){
    return a%(2*pi);
    }*/

    public Robot(){
        this(0, 0, 0);
    }

    @Override
    public void move(final double distance){
        this.aX+=distance*Math.cos(this.a+pi/2);
        this.aY+=distance*Math.sin(this.a+pi/2);
    }
    
    public void avance(){
        move(2);
    }
    
    public void recule(){
        move(-2);
    }

    @Override
    public void rotate(final double angle){
        this.a=angle;
    }

    @Override
    public double getAngle(){
        return this.a;
    }
    
    @Override public String toString(){
        return "x : "+this.aX+"\n"+"y : "+this.aY+"\n"+"angle : "+this.a;
    }

    public static void robotCount(){
        System.out.println(n);
    }

    @Override public double getPosX(){
        return this.aX;
    }

    @Override public double getPosy(){
        return this.aY;
    }

    public static void test(){
        Robot r=new Robot();
        Robot r2=new Robot(10, 15, 20);
        Robot r3=new Robot(5, -5, 20);
        Robot.robotCount();
    }
}
