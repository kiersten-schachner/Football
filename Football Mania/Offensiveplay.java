import java.util.Random;

public class Offensiveplay {
    private int chance, failYards, firstYards, secondYards;
    private boolean passPlay, hailMary;
    private String playName;
    Random rand= new Random();
    
    public Offensiveplay() {
        
    }
    
    public Offensiveplay(int success, int failyards, int firstyards, int secondyards, boolean pass, boolean hailmary, String name) {
        chance = success;
        failYards=failyards;
        firstYards= firstyards;
        secondYards= secondyards;
        passPlay = pass;
        hailMary=hailmary;
        playName=name;
    }
    public boolean isPassPlay() {
        return passPlay;
    }
    
    public int getSuccess() {
        return chance;
    }
    
    public int getFail() {
        return failYards;
    }
    
    public int getYards() {
        return rand.nextInt(secondYards-firstYards+1) + firstYards;
    }
    
    public boolean isHailMary() {
        return hailMary;
    }
    
    public String toString() {
        return playName;
    }   
}