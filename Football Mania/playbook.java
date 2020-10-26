import java.util.Random;

public class playbook {
    Random rand = new Random();
    
    Offensiveplay oplay1 = new Offensiveplay (80, -1, 2, 5, false, false,"run up the middle");
    Offensiveplay oplay2 = new Offensiveplay (60, -5, 2, 10, false, false,"run right");
    Offensiveplay oplay3 = new Offensiveplay (60, -5, 2, 10, false, false,"run left");
    Offensiveplay oplay4 = new Offensiveplay (50, -3, 6, 15, false, false,"jet sweep");
    Offensiveplay oplay5 = new Offensiveplay (50, 0, 4, 7, true, false,"short pass");
    Offensiveplay oplay6 = new Offensiveplay (33, 0, 9, 20, true, false,"long pass");
    Offensiveplay oplay7 = new Offensiveplay (15, 0, 35, 70, true, true,"hail mary");
    
    DefensivePlay dplay1 = new DefensivePlay (-10, 20, 20,"goal line defense");
    DefensivePlay dplay2 = new DefensivePlay (0, 0, 0,"4-3 defense");
    DefensivePlay dplay3 = new DefensivePlay (5, 0, 0,"nickel defense");
    DefensivePlay dplay4 = new DefensivePlay (15, -15, -15,"dime defense");
    DefensivePlay dplay5 = new DefensivePlay (20, 10, -10,"prevent defense");
    
    public int runPlay (int offplay, int defplay) {
        Offensiveplay playSelected;
            if (offplay == 1)
                playSelected = oplay1;
            else if (offplay ==2)
                playSelected=oplay2;
            else if (offplay==3)
                playSelected=oplay3;
            else if(offplay==4) 
                playSelected=oplay4;
            else if (offplay==5) 
                playSelected=oplay5;
            else if (offplay==6)
                playSelected=oplay6;
            else 
                playSelected=oplay7;      
            
        DefensivePlay defPlay;
            if (defplay == 1) 
              defPlay = dplay1;
            else if (defplay ==2) 
                defPlay=dplay2;
            else if (defplay==3)
                defPlay=dplay3;
            else if(defplay==4) 
                defPlay=dplay4;
            else 
                defPlay=dplay5;
        
        boolean passPlay = playSelected.isPassPlay();
        int defEffect;
       
        if (passPlay) 
            defEffect = defPlay.getPass(playSelected.isHailMary());
        else
            defEffect = defPlay.getRun();
        
        int newChance = playSelected.getSuccess()-defEffect;
        
        double outcome = Math.random() *100;
        
        if (outcome<newChance) 
            return playSelected.getYards();
        else
            return playSelected.getFail();
        
    }

    public String getOffName(int offplay) {
        if (offplay == 1)
                return oplay1.toString();
            else if (offplay ==2)
                return oplay2.toString();
            else if (offplay==3)
                return oplay3.toString();
            else if(offplay==4) 
                return oplay4.toString();
            else if (offplay==5) 
                return oplay5.toString();
            else if (offplay==6)
                return oplay6.toString();
            else 
                return oplay7.toString();
    }
    
    public String getDefName(int defplay) {
         if (defplay == 1) 
            return dplay1.toString();
        else if (defplay ==2) 
            return dplay2.toString();
        else if (defplay==3)
            return dplay3.toString();
        else if(defplay==4) 
            return dplay4.toString();
        else 
            return dplay5.toString();
    }
}