public class DefensivePlay {
    private int runPercent, passPercent, hailMary;
    private String playName;
    
    public DefensivePlay() {
        
    }
    
    public DefensivePlay (int run, int pass, int hailmary, String name) {
        runPercent=run;
        passPercent=pass;
        hailMary = hailmary;
        playName=name;
    }
    
    public int getRun() {
        return runPercent;
    }
    
    public int getPass(boolean hailmary) {
        if (hailmary)
            return hailMary;
        else
            return passPercent;
    }
    
    public String toString() {
        return playName;
    }
}