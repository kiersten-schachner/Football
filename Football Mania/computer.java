import java.util.Random;

public class computer {
    Random rand = new Random ();
    private int points=0, spot;
    footballField field = new footballField();

    public computer() {
        points = 0;
    }
    
    public String getKickoff() {
         String message;
        int kickDistance = kickoffDistance();
        int returnDistance = kickoffReturn();
        spot = 35+ kickDistance;
        spot-=returnDistance;
        if (spot >= 100) {
            spot = 75;
            message= "The kickoff was " +kickDistance + " yards and "
                    + "it was a touchback. The ball is spotted on the "
                     +"25 yard line.";
            }
        else if (spot>50) {
                message = "The kickoff was " + kickDistance + " yards"
                        + " and the return was " +returnDistance + " yards. The ball is "
                        + "spotted on their " + field.getPosition(spot) + " yard line."; 
        }
        else {
	message = "The kickoff was " + kickDistance + " yards"
                        + " and the return was " +returnDistance + " yards. The ball is "
                        + "spotted on your " + field.getPosition(spot) + " yard line.";
}
        return message;
    }
    
    public int getKickoffResult() {
        return spot;
    }
    
    private int kickoffDistance() {
        int distance = rand.nextInt(31)+35;
        return distance;
    }
    
    private int kickoffReturn() {
        int distance = rand.nextInt(49)+0;
        return distance;
    }
    
    public int runOffensivePlay(boolean fourthDown) {
        int play;
        if (fourthDown==true)
            play = rand.nextInt(7)+1; 
        else
            play = rand.nextInt(6)+1;
        return play;
    }
    
    public int runDefensivePlay() {
        int play = rand.nextInt(5)+1;
        return play;
    }
    public String gotTouchdown() {
        String message = "Computer scored a touchdown!";
        points+=7;
        return message;
    }
    
    public String gotFieldGoal() {
        String message = "Computer made the field goal.";
        points+=3;
        return message;
    }
    
    public String gotSafety() {
        String message = "Computer got a safety";
        points+=2;
        return message;
    }
    
    public String gotFirstDown() {
        String message = "Computer got a first down.";
        return message;
    }
    
    public String compPunt() {
        String message = "Computer punted the ball.";
        return message;
    }
    
    public int puntDistance(int currentspot) {
        int punt = rand.nextInt(currentspot-50) +(currentspot-20);
        return punt;
    }
    
    public int getPoints() {
        return points;
    }
}