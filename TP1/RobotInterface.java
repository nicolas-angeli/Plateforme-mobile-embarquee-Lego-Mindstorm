

public interface RobotInterface{
    public double getPosX();
    
    public double getPosy();
    /**
     * 
     * @return the angle in degree (!!) 0 means Robot is facing UP
     */
    
    public double getAngle();

    public void move(final double distance);
    /**
     * 
     * @param angle the angle in degree, positive angle is a left rotation
     */
    
    public void rotate(final double angle);

}
