public class MyRTClass extends RealtimeThread{
    
    public MyRTClass(int priority, long periodMillis)
    {
        super(
            new PriorityParameters(priority),
            new PeriodicParameters(new RelativeTime(periodMillis,0))
             );
    }

    private Motor leftM = Motor.C;
    private Motor rightM = Motor.A;
    // ... add any sensor you want to use

    @Override
    public void run(){

        // any oneshot computation needed at launch goes here
        // TODO
        
        while (true){

            waitForNextPeriod();

            // the code for the control goes here
            // TODO

        }// end while
    }// end run()
}// end class()

