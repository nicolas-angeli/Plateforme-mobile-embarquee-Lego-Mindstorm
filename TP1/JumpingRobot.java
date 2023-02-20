
public class JumpingRobot extends Robot implements Runnable{
    private double aZ;
    
    public JumpingRobot(final double pX, final double pY, final double pA, final double pZ){
        super(pX, pY, pA);
        this.aZ=pZ;
    }
    
    public JumpingRobot(){
        super();
        this.aZ=0;
    }
    
    public double getZ(){
        return this.aZ;
    }
    
    public void setZ(final double pZ){
        this.aZ=pZ;
    }

    public void saute(){
        Runnable r1 = new JumpingRobot();
        Thread t1 = new Thread(r1);
        t1.start();
    }

    public void run()
    {
        try{
            this.aZ+=1;
            Thread.sleep(2000);            
            this.aZ-=1;
        }
        catch(Exception e){
        }
    }
}
