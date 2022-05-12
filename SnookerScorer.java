import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class SnookerScorer {
    public static Player p1 = new Player("Player 1");
    public static Player p2 = new Player("Player 2");   
    public static boolean p1Turn = true;        
    public static int scoreDifference;
    public static int remainingReds = 15;
    public static int remainingPoints = remainingReds * 8 + 27;
    public static Player currPlayer = p1;
    public static Player otherPlayer = p2;
    public static boolean onARed;
    public static boolean freeBall;
    public static int currEndGameBall = 2;
    public static boolean isEndGame; 
    public static boolean isSuddenDeath;
    
    public static String input;
    public static Scanner scnr = new Scanner(System.in);
    
    public static Map<String, Integer> ballMap = new HashMap<String, Integer>();
    public static Map<Integer, String> endMap = new HashMap<Integer, String>();
    
    public static final int p1ScorePlacement = 22,
                            p2ScorePlacement = 46;
    
    
    private static void printMenu() {
        // TODO Auto-generated method stub
        System.out.println("Welcome to Snooker Scorer! \n"
                + "At any time, press q to quit.\n"
                + "\n");
        printOptions();
        printDisclaimer();
    }
    
    private static void printOptions() {
        System.out.println("When prompted, please type one of the following:\n"
                + "• the ball colour (i.e. “red”) or the value of the ball (i.e. “1”).\n"
                + "• “safety” or “missed pot” if you miss a pot.\n"
                + "• “foul” and follow the instructions.\n"
                + "• “correct” to correct an error (such as a score error or update the number of Reds).\n"
                + "• “name” or “rename” to rename a player.\n"
                + "• “menu” or “options” to print these options again.\n"
                + "• “q” or “quit” to quit.\n");
    }
 
    private static void printDisclaimer() {
        System.out.println("v. 0.1\n"
                + "Created by whathooldbean.\n"
                + "Free to download and use as you please. Any changes, please credit yourself.\n"
                + "\n"
                + "Known bugs: \n"
                + "• After a correction, it prints out “Invalid entry. Please try again.”\n"
                + "\n"
                + "This is a bare-bones text-based version. Coming soon to v. 1.0!\n"
                + "• a GUI!\n"
                + "• Save/Load features."
                + "\n"
                + "\n");
    }
//Name Line
    public static StringBuilder nameLine() {
 
        String name1 = new String(p1.getName());
        String name2 = new String(p2.getName());
        StringBuilder s = new StringBuilder("*                   P1:                     P2:                      *");
        
        if (!p1.getName().equals("No Name"))
            s.replace(24, 24 + name1.length(), name1);
        if (!p2.getName().equals("No Name"))
            s.replace(49, 49 + name2.length(), name2);      
        
        if (p1Turn) {
            s.replace(18, 19, ">"); 
        }
        else {
            s.replace(42, 43, ">");
        }
        
        return (s);
    }
//SCORELINE    
    public static StringBuilder scoreLine() {
        StringBuilder scoreLine = new StringBuilder("*                                                                    *");
        Integer p1Score = p1.getScore();
        Integer p2Score = p2.getScore();
        
        int p1Offset = 1;
        if (p1Score > 10) 
            p1Offset = 2;
        else if (p1Score > 100)
            p1Offset = 3;  
        
        int p2Offset = 1;
        if (p2Score > 10) 
            p2Offset = 2;
        else if (p2Score > 100)
            p2Offset = 3;
        
        scoreLine.replace(p1ScorePlacement - p1Offset, p1ScorePlacement, p1Score.toString());
        scoreLine.replace(p2ScorePlacement - p2Offset, p2ScorePlacement, p2Score.toString());
        return (scoreLine);
    }
    
    public static String filler() {
        return "*                                                                    *";
    }
    
//CURRENT BREAK
    public static StringBuilder currentBreakLine() {
        StringBuilder t = new StringBuilder("* Current break:                                                     *");
        
        int p1Offset = 1;
        if (p1.getBreak() > 10) 
            p1Offset = 2;
        else if (p1.getBreak() > 100)
            p1Offset = 3;
        
        int p2Offset = 1;
        if (p2.getBreak() > 10) 
            p2Offset = 2;
        else if (p2.getBreak() > 100)
            p2Offset = 3;
        
        Integer currBreak = currPlayer.getBreak();
        String breakString = currBreak.toString();
        
        if (currPlayer == p1)
            t.replace(p1ScorePlacement - p1Offset, p1ScorePlacement, breakString);
        else
            t.replace(p2ScorePlacement - p2Offset, p2ScorePlacement, breakString);
        
        return (t);
    }
    
// HIGHEST BREAK
    public static StringBuilder highestBreakLine() {
        StringBuilder u = new StringBuilder("* Highest break:                                                     *");
        
        int p1HighOffset = 1, 
            p2HighOffset = 1;
        
        if (p1.getHighestBreak() > 10) 
            p1HighOffset = 2;
        else if (p1.getHighestBreak() > 100)
            p1HighOffset = 3;
        
        if (p2.getHighestBreak() > 10) 
            p2HighOffset = 2;
        else if (p2.getHighestBreak() > 100)
            p2HighOffset = 3;
        
        Integer currP1HighBreak = p1.getHighestBreak();
        Integer currP2HighBreak = p2.getHighestBreak();
        String p1hb = currP1HighBreak.toString();
        String p2hb = currP2HighBreak.toString();
        u.replace(p1ScorePlacement - p1HighOffset, p1ScorePlacement, p1hb);
        u.replace(p2ScorePlacement - p2HighOffset, p2ScorePlacement, p2hb);
        
        return u;
    }
    
//Remaining Reds
    public static StringBuilder remainingRedsLine() {
        StringBuilder remainingRedsLine = new StringBuilder("* Reds Remaining:                                                    *" );
        
        Integer rR = remainingReds;
        
        if (rR > 10)
            remainingRedsLine.replace(35, 37, rR.toString());
        else
            remainingRedsLine.replace(36, 37, rR.toString());
        
        return remainingRedsLine;
       
    }
    
//remaining Points
    public static StringBuilder remainingPointsLine() {
        StringBuilder remainingPointsLine = new StringBuilder("* Points Remaining:                                                  *");
        
        Integer rP = remainingPoints;
        int rpPlacement = 37;
        int rpOffset = 3;

        if (remainingPoints < 100) {
            rpOffset = 2;
        }
        if (remainingPoints < 10) {
            rpOffset = 1;
        }
        
        remainingPointsLine.replace(rpPlacement - rpOffset, rpPlacement, rP.toString());
        
        return remainingPointsLine;
    }
    
//Score Difference
    public static StringBuilder scoreDiffLine() {
        scoreDifference = (p1.getScore() - p2.getScore());
        
        StringBuilder s = new StringBuilder("* Score difference:                                                  *");
        
        Integer absScoreDifference = Math.abs(scoreDifference);
        String aSD = absScoreDifference.toString();
        
        int asdPlacement = 36;
        int asdOffset = 1;

        if (absScoreDifference > 10) {
            asdOffset = 2;
        }
        if (absScoreDifference > 100) {
            asdPlacement = 37;
            asdOffset = 3;
        }
        
        s.replace(asdPlacement - asdOffset, asdPlacement, aSD);
        
        //Adding who is ahead
        if (scoreDifference > 0) { //P1 is ahead
            s.replace(32, 33, "<");
        }
        else if (scoreDifference < 0) { //P2 is ahead
            s.replace(38, 39, ">");
        }
        
        return s;
        
    }
    
    
//Snookers Required
    public static StringBuilder snookersRequiredLine() {
        StringBuilder snookersRequiredLine = new StringBuilder("* Snookers required:                                                 *");
        int absvalScoreDiff = Math.abs(scoreDifference);
        
        if (absvalScoreDiff > remainingPoints) { //Snookers are required
            int minSnooker; 
            Double snookersRequired;
            
            if (currEndGameBall <= 4) {
                minSnooker = 4;
            }
            else if (currEndGameBall == 5) {
                minSnooker = 5;
            }
            else if (currEndGameBall == 6) {
                minSnooker = 6;
            }
            else {
                minSnooker = 7;
            }
            
            snookersRequired = Math.ceil((double) (absvalScoreDiff - remainingPoints) / minSnooker);
            String s = snookersRequired.toString();
            String t = s.substring(0, s.length() - 2);
            
            int offset = t.length();
            
            if (scoreDifference < 0) { //P1 is behind
                snookersRequiredLine.replace(23 - offset, 23, t);
            }
            else if (scoreDifference > 0) {
                snookersRequiredLine.replace(46 - offset, 46, t);
            }
        }
        return (snookersRequiredLine);
    }
    
    public static String suddenDeathLine() {
        return "*                           SUDDEN DEATH!                             *";
    }
    
    
    public static void printScreen() { //Placeholder. TODO make this use a GUI
        
        System.out.println("*************************** SNOOKER SCORER ***************************\n"
                + "*                                                                    *");

        System.out.println(nameLine());
        System.out.println(scoreLine());
        
        if (!isSuddenDeath)
            System.out.println(filler());
        else
            System.out.println(suddenDeathLine());
        
        System.out.println(currentBreakLine());
        System.out.println(highestBreakLine());
        
        System.out.println(filler());
        
        System.out.println(remainingRedsLine());
        System.out.println(remainingPointsLine());
        
        System.out.println(filler());
        
        System.out.println(scoreDiffLine());
        System.out.println(snookersRequiredLine());        
        
        System.out.println("*                                                                    *\n"
                + "**********************************************************************");
        
        }
    
    public static void initializeMaps() {
        ballMap.put("Red", 1);
        ballMap.put("red", 1);
        ballMap.put("1", 1);
        ballMap.put("Yellow", 2);
        ballMap.put("yellow", 2);
        ballMap.put("2", 2);
        ballMap.put("Green", 3);
        ballMap.put("green", 3);
        ballMap.put("3", 3);
        ballMap.put("Brown", 4);
        ballMap.put("brown", 4);
        ballMap.put("4", 4);
        ballMap.put("Blue", 5);
        ballMap.put("blue", 5);
        ballMap.put("5", 5);
        ballMap.put("Pink", 6);
        ballMap.put("pink", 6);
        ballMap.put("6", 6);
        ballMap.put("Black", 7);
        ballMap.put("black", 7);
        ballMap.put("7", 7);
        
        endMap.put(2, "yellow");
        endMap.put(3, "green");
        endMap.put(4, "brown");
        endMap.put(5, "blue");
        endMap.put(6, "pink");
        endMap.put(7, "black");
    }

    
    private static void whoseTurn() {
        if (p1Turn) {
            currPlayer = p1;
            otherPlayer = p2;
        }
        else {
            currPlayer = p2;
            otherPlayer = p1;
        }
    }
    
     /* 
     * 
     * The following are the input methods.
     * 
     * */
    
    public static void rename() {
        System.out.println("Whose name would you like to change? Please enter 'P1' or 'P2'. ");
        
        String player = scnr.nextLine();
        
        while (!player.equals("P1") && !player.equals("P2")) {
            System.out.println("Error. Please enter 'P1' or 'P2'.");
            player = scnr.nextLine();
        }
        
        System.out.println("Please enter the player's name");
        String name = scnr.nextLine();
        
        if (player.equals("P1"))
            p1.setName(name);
        else if (player.equals("P2"))
            p2.setName(name);
    }
    
    
    public static void didNotPot() {
        
        currPlayer.resetBreak();
        
        if (p1Turn) { //i.e. P1 missed
            currPlayer = p2;
            otherPlayer = p1;
            p1Turn = false;
        }
        else { //i.e. P2 missed
            currPlayer = p1;
            otherPlayer = p2;
            p1Turn = true;
        }
        onARed = true;
        if (!isEndGame)
            remainingPoints = remainingReds * 8 + 27; 
    }
    
    public static void foul() {
        System.out.println ("What was the highest ball involved in the foul?");
        
        input = scnr.nextLine();
        
        while (ballMap.containsKey(input) == false) {
            System.out.println ("Error. Please enter a ball.");
            input = scnr.nextLine();
        }
        
        currPlayer.resetBreak();
        if (!isEndGame)
            remainingPoints = remainingReds * 8 + 27;
            
        int foul = ballMap.get(input);
        
        if (foul <= 4) {
            otherPlayer.addToScore(4);
        }
        else {
            otherPlayer.addToScore(foul);
        }
        
        System.out.println ("Who is taking the next shot?  Please type the name of the player, or either 'P1' or 'P2', or 'Free Ball'");
        input = scnr.nextLine();
        
        while (!input.equalsIgnoreCase("p1") && !input.equalsIgnoreCase("p2") && !input.equalsIgnoreCase("free ball") &&!input.equalsIgnoreCase("freeball"))
        
        if (input.equals("P1") || input.equals("p1") || input.equals(p1.getName())) {
            currPlayer = p1;
            otherPlayer = p2;
            p1Turn = false;
        }
        else if (input.equals("P2") || input.equals("p1") || input.equals(p2.getName())) {
            currPlayer = p2;
            otherPlayer = p1;
            p1Turn = true;
        }
        else if (input.equalsIgnoreCase("Free Ball") || input.equalsIgnoreCase("freeball")) {
            if (p1Turn) { //i.e. P1 fouled
                currPlayer = p2;
                otherPlayer = p1;
                p1Turn = false;
            }
            else { //i.e. P2 fouled
                currPlayer = p1;
                otherPlayer = p2;
                p1Turn = true;
            }
            freeBall = true;
            if (!isEndGame)
                remainingPoints = (remainingReds + 1) * 8 + 27;
            else
                remainingPoints = remainingPoints + currEndGameBall;  
        }
    }
    
//CORRECT    
    public static void correct() {
        if (!isEndGame)
            System.out.println("What would you like to correct? Please choose A, B, or C:\n" 
                        + "1. Player 1 Score   2. Player 2 Score    3. Number of Reds" );
        else
            System.out.println("What would you like to correct? Please choose A, B, or C:\n" 
                    + "1. Player 1 Score   2. Player 2 Score    3. Which final colour player is on" );
        
        String correction = scnr.nextLine();
        
        while (!correction.equals("1") && !correction.equals("2") && !correction.equals("3")) {
                    System.out.println("Please enter 1 or 2 or 3.");
                    correction = scnr.nextLine();
                }
        
        if (correction.equals("1")) {
            System.out.println("How many points should P1 have?");
            int newscore = scnr.nextInt();
            p1.setScore(newscore);
        }
        else if (correction.equals("2")) { 
            System.out.println("How many points should P2 have?");
            int newscore = scnr.nextInt();
            p2.setScore(newscore);
        
        }
        else if (correction.equals("3")) {
            if (!isEndGame) {
                System.out.println("How many reds are on the table?");
                int reds = scnr.nextInt();
                remainingReds = reds;
            
                remainingPoints = remainingReds * 8 + 27;
                
                if (reds == 0) {
                    onARed = false;
                    isEndGame = true;
                }
            }
            else {
                System.out.println("Which end colour are you on? Please type the colour or the value of the ball.");
                String colour = scnr.nextLine().toLowerCase();
                currEndGameBall = ballMap.get(colour);
                
                int subtraction = 0;
                int n;
                
                for (n = currEndGameBall - 1; n >= 2; n--) {
                    subtraction += n;
                }
            
                remainingPoints = 27 - subtraction;
            }
        }
        input.trim();
    }
//SCORE    
    public static void score() {
        try {
            int pottedScore = ballMap.get(input);  //will throw exception if anything other than a ball name; caught below.
            
            if (remainingReds > 0 || !isEndGame) {
            
                if (pottedScore == 1) {  //RED
                    if (!onARed) {
                        System.out.println("You were on a colour; please enter in a valid entry.");
                    }
                    else { //onARed = true;
                        currPlayer.addToScore(1);
                        if (freeBall == false) {
                            remainingReds--;
                        }
                        else {
                            freeBall = false;
                        }
                        onARed = false;
                    }
                    remainingPoints = (remainingReds + 1) * 8 + 27 - 1;
                }
                else {
                    if (onARed) {
                        System.out.println("You were on a red; please enter in a valid entry.");
                    }
                    else {
                        currPlayer.addToScore(pottedScore);
                        onARed = true;
                        if (remainingReds == 0) {
                            isEndGame = true;
                            onARed = false;
                        }
                    }
                    remainingPoints = remainingReds * 8 + 27;
                }

            }
            else { //end game
                if (pottedScore == 1) {
                    System.out.println("Only colours left on the board. Please enter a valid entry.");
                }
                else {
                    if (pottedScore != currEndGameBall) {
                        System.out.println("You are on the " + endMap.get(currEndGameBall) + ". Please enter a valid entry.");
                    }
                    else {
                        currPlayer.addToScore(pottedScore);
                        remainingPoints -= currEndGameBall;
                        currEndGameBall++;
                    }
                }
            }
        }
        catch (Exception e) {  //invalid entry
            System.out.println("Invalid entry. Please try again.");
        }
    }

    
    
    
    public static void main(String[] args) {
        initializeMaps();
        onARed = true;
        
        printMenu();
   
        while (remainingPoints >= 0) {
            printScreen();
            
            if (remainingPoints == 0)
                break;
            
            //whose turn is it?
            whoseTurn();
            
            System.out.println("Please type a command: ");
            input = scnr.nextLine();
            
            //  QUIT
            if (input.equalsIgnoreCase("q") || input.equals("quit"))
                break;
            
            if (input.equalsIgnoreCase("menu") || input.equalsIgnoreCase("options")) {
                printOptions();
            }
                    
            
            //NAME CHANGE
            if (input.equalsIgnoreCase("name") || input.equalsIgnoreCase("rename")) {
                rename();
            }
            
            //SAFETY/MISSED POT
            else if (input.equalsIgnoreCase("safety") || input.equalsIgnoreCase("missed pot") 
                    || input.equalsIgnoreCase("did not pot") || input.equalsIgnoreCase("missed") 
                    || input.equalsIgnoreCase("miss")) {
                didNotPot();
                
                if (remainingPoints == 7 && scoreDifference > 7) { //game has ended
                    break;
                }
            }
            
            //FOUL
            else if (input.equals("foul")){
                foul();
                
                if (remainingPoints == 7 && scoreDifference > 7) {
                    break;
                }
            }

                //CORRECT AN ERROR
            else if (input.equals("correct")) {
                correct();
                
                if (remainingPoints == 7 && scoreDifference > 7) {
                    break;
                }
            }
 
                //IS A SCORE or is INVALID ENTRY
            else { 
                score();
            }
            
            if (remainingPoints == 0 && scoreDifference == 0) { //game "ends" with a tie: sudden death with a replacement black
                remainingPoints = 7;
                currEndGameBall = 7;
                isSuddenDeath = true;
            }
        }
        
        Player winningPlayer = new Player();
        
        if (scoreDifference > 0)
            winningPlayer = p1;
        if (scoreDifference < 0)
            winningPlayer = p2;
        
        System.out.print(winningPlayer.getName() +  " is the winner!");
    }

 


}
