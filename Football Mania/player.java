import java.util.Random;

public class player {
    private int points=0, spot;
    Random rand= new Random();
    footballField field= new footballField();
    
    public player () {
        points = 0;
    }
    
    public String showOffensivePlays (boolean fourthDown) {
        String plays;
        if (fourthDown==true) 
             plays = "Type 1 for a run up the middle, 2 for a run right, "
                + "3 for a run left, 4 for a jet sweep, 5 for a short pass, "
                + "6 for a long pass, 7 for a hail mary, 8 for a field goal, or"
                + " 9 for a punt."; 
        else
            plays = "Type 1 for a run up the middle, 2 for a run right, "
                + "3 for a run left, 4 for a jet sweep, 5 for a short pass, "
                + "6 for a long pass, or 7 for a hail mary.";
        return plays;
    }
    
    public String showDefensivePlays() {
        String plays = "Type 1 for a goal line defense, 2 for a 4-3 defense, "
                + "3 for a nickel defense, 4 for a dime defense, or 5 for a prevent"
                + " defense.";
        return plays;
    }
    
    public String getKickoff() {
        String message;
        int kickDistance = kickoffDistance();
        int returnDistance = kickoffReturn();
        spot = 65 - kickDistance;
        spot+=returnDistance;
        if (spot < 0) {
            spot = 25;
            message= "The kickoff was " +kickDistance + " yards and "
                    + "it was a touchback. The ball is spotted on your "
                    + spot + " yard line.";
            }
        else {
            spot = (spot+returnDistance);
            if (spot>50)
                message = "The kickoff was " + kickDistance + " yards"
                        + " and the return was " +returnDistance+ " yards. The ball is "
                        + "spotted on their " + field.getPosition(spot) + " yard line.";
            else
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
    
    public String userPunt() {
        String message = "You punted the ball.";
        return message;
    }
    
    public int puntDistance(int currentspot) {
        int punt = rand.nextInt(100-(currentspot+20)+1) +(currentspot+20);
        return punt;
    }
    
    public String gotTouchdown() {
        String message = "You scored a touchdown!";
        points+=7;
        return message;
    }
    
    public String gotFieldGoal() {
        String message = "You made the field goal!";
        points+=3;
        return message;
    }
    
    public String gotSafety() {
        String message = "You got a safety!";
        points+=2;
        return message;
    }
    
    public String gotFirstDown() {
        String message = "You got a first down.";
        return message;
    }
    
    public int getPoints() {
        return points;
    }
}