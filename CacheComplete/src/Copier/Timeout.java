package Copier;



import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class Timeout{
    Toolkit toolkit;
    Timer timer;
   

    public Timeout() {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(),
                       0,        //initial delay
                       1*600000);  //subsequent rate
    }

    class RemindTask extends TimerTask {
        int numCopies = 15;
        
        public void run() {
            if (numCopies > 0) {
                toolkit.beep();
              //  System.out.format("Beep!%n");
            //	CacheCopy.copyDirectory(sourceDir, newDir);
            	CacheCopy c = new CacheCopy();
            	new Thread(c).start();
            	System.out.println(numCopies);
                numCopies--;
            } else {
                toolkit.beep(); 
                System.out.format("Time's up!%n");
                //timer.cancel(); //Not necessary because
                                  //we call System.exit
                System.exit(0);   //Stops the AWT thread 
                                  //(and everything else)
            }
        }
    }
    /*

*/public static void main(String args[]){
	
	new Timeout();
}
}