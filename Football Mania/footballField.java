import java.util.Random;

public class footballField {
    private int position;
    Random rand = new Random();
    
    
    public footballField() {
        position =0;
    }
    public int getPosition(int ballspot) {
         if (ballspot>50)
            return (100-ballspot);
         else
            return ballspot;
        }

    public int timeUsed () {
        int timeUsed = rand.nextInt(31)+5;
        return timeUsed;
    }
    
    public int convertToMins (int seconds) {
        int mins = (int) seconds/60;
        return mins;
    }
    
    public int leftoverSecs (int seconds) {
        int secs = seconds % 60;
        return secs;
    }
}
