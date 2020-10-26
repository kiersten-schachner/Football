import java.util.Scanner;
public class footballDriver {
    public static void main (String[] args) {
        int playerFace, coinFace, ballSpot, chosenPlay = 0, compPlay = 0;
        boolean onOffense, startsBall;
        int timeLeft = 900, quarter = 1, yardsGained=0, timeUsed, down=1, yardsToGo=10, mins=0, secs=0;
        
        Scanner scan = new Scanner (System.in);
        playbook play = new playbook();
        footballField field = new footballField();
        
        player user = new player();
        computer comp = new computer();
        
        System.out.println("Type \"0\" for heads or \"1\" for tails to receive ball first.");
        playerFace = scan.nextInt();
        coinFace= (int) Math.round(Math.random());
        
        if (coinFace == playerFace) { //determines possession
            startsBall=true;
            onOffense = true;
            System.out.println("You won the coin toss and have the ball first."); }
        else {
            startsBall=false;
            onOffense = false; 
            System.out.println("You lost the coin toss and will be on defense first."); }
        
            
            if (startsBall==true) {
                System.out.println(user.getKickoff());
                ballSpot = user.getKickoffResult();
                if (ballSpot>=100) {
                    System.out.println(user.gotTouchdown());
                    System.out.println(comp.getKickoff());
                    ballSpot=comp.getKickoffResult();
                    timeLeft-=field.timeUsed();
                    down=1;
                    yardsToGo=10;
                    onOffense=false;
                    }
                else {
                    timeLeft-=field.timeUsed();
                down=1;
                yardsToGo=10;
                mins= field.convertToMins(timeLeft);
                secs= field.leftoverSecs(timeLeft);
                System.out.println("Time left in the "+quarter+" quarter: "
                  + String.format("%02d",mins)+ ":"+String.format("%02d",secs)); }
            }
            
            else {
                System.out.println(comp.getKickoff());
                ballSpot = comp.getKickoffResult();
                if (ballSpot<=0) {
                    System.out.println(comp.gotTouchdown());
                    System.out.println(user.getKickoff());
                    ballSpot=user.getKickoffResult();
                    timeLeft-=field.timeUsed();
                    down=1;
                    yardsToGo=10;
                    onOffense=true;
                }
                else{
                    timeLeft-=field.timeUsed();
                    down=1;
                    yardsToGo=10;
                    mins= field.convertToMins(timeLeft);
                    secs= field.leftoverSecs(timeLeft);
                    System.out.println("Time left in the "+quarter+" quarter: "
                  + String.format("%02d",mins)+ ":"+String.format("%02d",secs)); }
            }
        
        while (quarter!=5) {
            
            
            if (onOffense == true) { //User is on offense
                if (down==4)  {
                    System.out.println();
                    System.out.println(user.showOffensivePlays(true)); }
                else {
                    System.out.println();
                    System.out.println(user.showOffensivePlays(false)); }
                
                chosenPlay = scan.nextInt();
                compPlay = comp.runDefensivePlay();
                
                if (chosenPlay<8) 
                    yardsGained = play.runPlay(chosenPlay, compPlay);
                else if (chosenPlay>9) { //bad input
                    System.out.println("Bad input. " +user.showOffensivePlays(false));
                    chosenPlay=scan.nextInt();
                }
                    
                
                ballSpot+=yardsGained;
                timeUsed = field.timeUsed();
                timeLeft-=timeUsed;
                mins = field.convertToMins(timeLeft);
                secs= field.leftoverSecs(timeLeft);
                down++;
                yardsToGo-=yardsGained;
                
                if (ballSpot<=0) { //safety
                    System.out.println(comp.gotSafety());
                    System.out.println(user.userPunt());
                    ballSpot=comp.puntDistance(0);
                    onOffense=false;
                }
                
                if (down>4) {
                    System.out.print("That was the fourth down. ");
                    if (chosenPlay==9) { //chose to punt
                        System.out.println();
                        ballSpot = user.puntDistance(ballSpot);
                        System.out.println("You punted the ball and the computer will now"
                            + " start on the " +field.getPosition(ballSpot)+ " yard line.");
                        System.out.println("Time left in the "+quarter+" quarter: "
                        + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                        down=1;
                        yardsToGo=10;
                        onOffense=false; }
                    else if (chosenPlay==8) { //chose field goal
                        double chance = Math.random();
                        if (chance < .85) {
                            System.out.println(user.gotFieldGoal());
                            System.out.println(comp.getKickoff());
                            ballSpot=comp.getKickoffResult();
                            timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: "+
                                    String.format("%02d",mins)+":"+String.format("%02d",secs));
                            onOffense=false; }
                        else {
                        System.out.println("The field goal was missed.");
                        System.out.println("Computer starts on the "+ballSpot+ " yard line.");
                        System.out.println("Time left in the "+quarter+" quarter: "
                        + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                        down=1;
                        yardsToGo=10;
                        onOffense=false; }
                    }
                    else if (yardsToGo<=0) { //got first down
                        if (ballSpot>=100) {
                            System.out.println(user.gotTouchdown());
                             System.out.println("You ran the " + play.getOffName(chosenPlay)+
                            " and the computer ran the " +play.getDefName(compPlay)+
                            ", resulting in " +yardsGained+" yards.");
                             System.out.println(comp.getKickoff());
                             ballSpot=comp.getKickoffResult();
                             timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: " + String.format("%02d", mins)+ ":"+String.format("%02d", secs)); 
                             onOffense=false; }
                    else {
                        System.out.println(user.gotFirstDown()); 
                        yardsToGo=10;
                        down=1;
                        System.out.println("You successfully converted!");
                        System.out.println("You ran the " + play.getOffName(chosenPlay)+
                        " and the computer ran the " +play.getDefName(compPlay)+
                        ", resulting in " +yardsGained+" yards and the ball"
                        + " is now on the " +field.getPosition(ballSpot)+ ". It is now the "
                        + down+ " down and there are "+yardsToGo+ " yards to go for a first down."
                                + " Time left in the "+quarter+" quarter: " + String.format("%02d", mins)+ ":"+String.format("%02d", secs)); }
                    }
                    else if (ballSpot>=100) { //got touchdown
                             System.out.println(user.gotTouchdown());
                             System.out.println("You ran the " + play.getOffName(chosenPlay)+
                            " and the computer ran the " +play.getDefName(compPlay)+
                            ", resulting in " +yardsGained+" yards.");
                             System.out.println(comp.getKickoff());
                             ballSpot=comp.getKickoffResult();
                             timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: " + String.format("%02d", mins)+ ":"+String.format("%02d", secs)); 
                             onOffense=false; }
                    else { //did not convert first down
                        if (chosenPlay!=9&&chosenPlay!=8) {
                        System.out.println("");
                        ballSpot-=yardsGained;
                        System.out.println("You ran the " + play.getOffName(chosenPlay)+
                        " and the computer ran the " +play.getDefName(compPlay)+
                        ", resulting in " +yardsGained+" yards.");
                        System.out.println("You did not get first down. Computer starts at the " +field.getPosition(ballSpot));
                        System.out.println("Time left in the "+quarter+" quarter: "
                        + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                        down=1;
                        yardsToGo=10;
                        onOffense=false;}
                    }
                }
                
                else{ //not fourth down
                    if (yardsToGo <=0) { //first down
                         if (ballSpot >= 100) {
                             System.out.println(user.gotTouchdown());
                             System.out.println("You ran the " + play.getOffName(chosenPlay)+
                            " and the computer ran the " +play.getDefName(compPlay)+
                            ", resulting in " +yardsGained+" yards.");
                             System.out.println(comp.getKickoff());
                             ballSpot=comp.getKickoffResult();
                             timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: " + String.format("%02d", mins)+ ":"+String.format("%02d", secs)); 
                             onOffense=false; }
                         else {
                             System.out.println(user.gotFirstDown());
                             yardsToGo=10;
                             down=1; 
                             System.out.println("You ran the " + play.getOffName(chosenPlay)+
                        " and the computer ran the " +play.getDefName(compPlay)+
                        ", resulting in " +yardsGained+" yards and the ball"
                        + " is now on the " +field.getPosition(ballSpot)+ ". It is now the "
                        + down+ " down and there are "+yardsToGo+ " yards to go for a first down."
                        + " Time left in the "+quarter+" quarter: " + String.format("%02d", mins)+ ":"+String.format("%02d", secs)); 
                         }
                }
                    else { //no first down
                        if (ballSpot >= 100) {
                             System.out.println(user.gotTouchdown());
                             System.out.println("You ran the " + play.getOffName(chosenPlay)+
                            " and the computer ran the " +play.getDefName(compPlay)+
                            ", resulting in " +yardsGained+" yards.");
                             System.out.println(comp.getKickoff());
                             ballSpot=comp.getKickoffResult();
                             timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: " + String.format("%02d", mins)+ ":"+String.format("%02d", secs)); 
                             onOffense=false; }
                        else        
                              System.out.println("You ran the " + play.getOffName(chosenPlay)+
                            " and the computer ran the " +play.getDefName(compPlay)+
                            ", resulting in " +yardsGained+" yards and the ball"
                           + " is now on the " +field.getPosition(ballSpot)+ ". It is now the "
                          + down+ " down and there are "+yardsToGo+ " yards to go for a first down."
                           + " Time left in the "+quarter+" quarter: " + String.format("%02d", mins)+ ":"+String.format("%02d", secs)); }
                }   
                System.out.println("You have " +user.getPoints()+" points and the computer"
                        + " has "+comp.getPoints()+" points.");
                
                if (mins<=0 && secs<=0){ //clock runs down to 0
                    System.out.println("That is the end of the "+quarter+ " quarter.");
                    quarter++;
                    if (quarter ==3) { //halftime
                        if (startsBall==true) { //started half with ball
                            timeLeft = 900;
                            System.out.println("It is the end of the half and "
                                 + "the computer will begin the second half with the ball.");
                            System.out.println(comp.getKickoff());
                            ballSpot= comp.getKickoffResult();
                            timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in " +quarter+ " quarter is " +String.format("%02d", mins)+":"+String.format("%02d",secs));
                        }
                        else { //started half without ball
                            timeLeft = 900;
                            System.out.println("It is the end of the half and "
                                 + "you will begin the second half with the ball.");
                            
                            System.out.println(user.getKickoff());
                            ballSpot= user.getKickoffResult();
                            timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in "+quarter+" quarter is " +String.format("%02d", mins)+":"+String.format("%02d", secs));
                    }
                        }
                
                    else { //not halftime -- possession does not change
                        timeLeft=900;
                        onOffense=true; }
                     }
            }
            
            
            if (onOffense==false) { //on defense
                
                if (down==4) 
                    compPlay = comp.runOffensivePlay(true); 
                else
                    compPlay= comp.runOffensivePlay(false);
                
                System.out.println();
                System.out.println(user.showDefensivePlays());
                chosenPlay=scan.nextInt();
                
                if(chosenPlay>5) {
                    System.out.println("Bad input. "+user.showDefensivePlays());
                    chosenPlay=scan.nextInt();
                }
                
                if (compPlay<8) 
                    yardsGained = play.runPlay(compPlay, chosenPlay);
                else if (compPlay==8) { //field goal
                    double chance = Math.random();
                    if (chance < .85) {
                        System.out.println(comp.gotFieldGoal());
                        System.out.println(user.getKickoff());
                        ballSpot=user.getKickoffResult();
                        timeLeft-=field.timeUsed();
                        down=1;
                        yardsToGo=10;
                        mins= field.convertToMins(timeLeft);
                        secs= field.leftoverSecs(timeLeft);
                        System.out.println("Time left in the quarter: "+String.format("%02d",mins)+":"
                            +String.format("%02d",secs));
                        onOffense=true; }
                    else {
                        System.out.println("The field goal was missed."); 
                        onOffense=true; }
                }
                ballSpot-=yardsGained;
                timeUsed = field.timeUsed();
                timeLeft-=timeUsed;
                mins= field.convertToMins(timeLeft);
                secs= field.leftoverSecs(timeLeft);
                down++;
                yardsToGo-=yardsGained;
                
                if (ballSpot>=100) { //safety
                    System.out.println(user.gotSafety());
                    System.out.println(comp.compPunt());
                    ballSpot = comp.puntDistance(100);
                    onOffense=true;
                }
                
                if (down>4) { //was a fourth down play
                    System.out.print("That was the fourth down. ");
                    if (compPlay==9) {
                        System.out.println();
                        ballSpot=comp.puntDistance(ballSpot);
                        System.out.println("The computer punted the ball and "
                                + "you will now start on the "
                                +field.getPosition(ballSpot)+ " yard line.");
                        System.out.println("Time left in the "+quarter+" quarter: "
                        + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                        down=1;
                        yardsToGo=10;
                        onOffense=true;
                    }
                    else if (compPlay==8) { //field goal
                        double chance = Math.random();
                        if (chance < .85) {
                            System.out.println(comp.gotFieldGoal());
                            System.out.println(user.getKickoff());
                            ballSpot=user.getKickoffResult();
                            timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: "
                                + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                            onOffense=true; }
                        else {
                        System.out.println("The field goal was missed.");
                        System.out.println("You start on the "+field.getPosition(ballSpot)+ " yard line.");
                        System.out.println("Time left in the "+quarter+" quarter: "
                        + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                        down=1;
                        yardsToGo=10;
                        onOffense=true; } }
                    else if (yardsToGo<0) {
                        if (ballSpot<=0) {
                            System.out.println(comp.gotTouchdown());
                             System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards.");
                             System.out.println(user.getKickoff());
                             ballSpot=user.getKickoffResult();
                             timeLeft-=field.timeUsed();
                             down=1;
                             yardsToGo=10;
                             mins= field.convertToMins(timeLeft);
                             secs= field.leftoverSecs(timeLeft);
                             System.out.println("Time left in the "+quarter+" quarter: "
                                + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                             onOffense=true; 
                        }
                      else {
                        System.out.println(comp.gotFirstDown());
                        down = 1;
                        yardsToGo=10;
                        System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards and the ball "
                        + "is now on the " +field.getPosition(ballSpot)+ ". It is now the "
                        + down+ " down and there are "+yardsToGo+ " yards to go for a first down."
                                + " Time left in the "+quarter+" quarter: " +String.format("%02d", mins)+ ""
                        + ":"+String.format("%02d", secs)); }
                    }
                    else if (ballSpot<=0) {
                         System.out.println(comp.gotTouchdown());
                             System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards.");
                             System.out.println(user.getKickoff());
                             ballSpot=user.getKickoffResult();
                             timeLeft-=field.timeUsed();
                             down=1;
                             yardsToGo=10;
                             mins= field.convertToMins(timeLeft);
                             secs= field.leftoverSecs(timeLeft);
                             System.out.println("Time left in the "+quarter+" quarter: "
                                + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                             onOffense=true; 
                    }
                    else {
                        if (compPlay!=9 && compPlay!=8) {
                        ballSpot+=yardsGained;
                        System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards.");
                        System.out.println("Computer did not get first down. You start at the " +field.getPosition(ballSpot));
                        System.out.println("Time left in the "+quarter+" quarter: "
                        + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                        down=1;
                        yardsToGo=10;
                        onOffense=true;}
                    }
                }
                else { //was not fourth down
                     if (yardsToGo <=0) { //first down
                         if (ballSpot <= 0) {
                             System.out.println(comp.gotTouchdown());
                             System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards.");
                             System.out.println(user.getKickoff());
                             ballSpot=user.getKickoffResult();
                             timeLeft-=field.timeUsed();
                             down=1;
                             yardsToGo=10;
                             mins= field.convertToMins(timeLeft);
                             secs= field.leftoverSecs(timeLeft);
                             System.out.println("Time left in the "+quarter+" quarter: "
                                + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                             onOffense=true; }
                         else {
                             System.out.println(comp.gotFirstDown());
                             yardsToGo=10;
                             down=1; 
                            System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards and the ball "
                        + "is now on the " +field.getPosition(ballSpot)+ ". It is now the "
                        + down+ " down and there are "+yardsToGo+ " yards to go for a first down."
                                + " Time left in the "+quarter+" quarter: " + String.format("%02d",mins)+ ":"+String.format("%02d", secs));
                         }
                    }
                     else {
                         if (ballSpot<=0) {
                              System.out.println(comp.gotTouchdown());
                             System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards.");
                             System.out.println(user.getKickoff());
                             ballSpot=user.getKickoffResult();
                             timeLeft-=field.timeUsed();
                             down=1;
                             yardsToGo=10;
                             mins= field.convertToMins(timeLeft);
                             secs= field.leftoverSecs(timeLeft);
                             System.out.println("Time left in the "+quarter+" quarter: "
                                + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                             onOffense=true; 
                         }
                         System.out.println("Computer ran the " + play.getOffName(compPlay)+
                        " and you ran the " +play.getDefName(chosenPlay)+
                        ", resulting in " +yardsGained+" yards and the ball "
                        + "is now on the " +field.getPosition(ballSpot)+ ". It is now the "
                        + down+ " down and there are "+yardsToGo+ " yards to go for a first down."
                                + " Time left in the "+quarter+" quarter: " + String.format("%02d",mins)+ ":"+String.format("%02d", secs)); }
                }
                System.out.println("You have " +user.getPoints()+" points and the computer"
                        + " has "+comp.getPoints()+" points."); 
                
                if (mins<=0 && secs<=0){ //clock runs down to 0
                    System.out.println("That is the end of the "+quarter+ " quarter.");
                    quarter++;
                    if (quarter ==3) { //start of third quarter
                        if (startsBall==true) { //started first half with ball
                            timeLeft = 900;
                            System.out.println("It is the end of the half and "
                                 + "the computer will begin the second half with the ball.");
                            System.out.println(comp.getKickoff());
                            ballSpot = comp.getKickoffResult();
                            timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: "
                                + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                            onOffense=false;
                        }
                            
                            else { //started first half without ball
                            timeLeft = 900;
                            System.out.println("It is the end of the half and "
                                 + "you will begin the second half with the ball.");
                            
                            System.out.println(user.getKickoff());
                            ballSpot=user.getKickoffResult();
                            timeLeft-=field.timeUsed();
                            down=1;
                            yardsToGo=10;
                            mins= field.convertToMins(timeLeft);
                            secs= field.leftoverSecs(timeLeft);
                            System.out.println("Time left in the "+quarter+" quarter: "
                                + String.format("%02d",mins)+ ":"+String.format("%02d",secs));
                            onOffense=true;
                            
                        }     
                        
                    }
                    else { //not halftime -- possession does not change
                        timeLeft=900;
                        onOffense=false;
                     }
                     }
            }
        }
        
        
        if (user.getPoints() > comp.getPoints())
            System.out.println("The game is over. You won " +user.getPoints()+ "-" +comp.getPoints());
        else if (user.getPoints() < comp.getPoints())
            System.out.println("The game is over. You lost "+user.getPoints()+ "-"+comp.getPoints());
        else
            System.out.println("The game is over. You tied "+user.getPoints()+ "-" +comp.getPoints());
            

    }
}