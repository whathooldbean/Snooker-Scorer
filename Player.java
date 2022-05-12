
public class Player {

    private String name;
    private int score;
    private boolean turn;
    private int thisBreak;
    private int highestBreak;
    private int snookersRequired;
    private boolean breakStart;
    
    public Player() {
        name = "No Name";
        score = 0;
        highestBreak = 0;
    }
    
    public Player(String incomingName) {
        setName(incomingName);
    }
    
    public void setName(String inputName) {
        
        if (inputName.length() > 19) //name cannot be more than 19 characters
            name = inputName.substring(0, 19);
        else 
            name = inputName;        
    }
    
    public String getName() {
        return name;
    }
    
    public void printScore() {
        System.out.print(score);
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int newScore) {
        score = newScore;
    }
    
    public int getBreak() {
        return thisBreak;
    }
    
    public int getHighestBreak() {
        return highestBreak;
    }
    
    public void resetBreak() {
        thisBreak = 0;
    }

    public void addToScore(int addToScore) {
        score += addToScore;
        thisBreak += addToScore;
        
        if (thisBreak > highestBreak) {
            highestBreak = thisBreak;
        }
    }
    
    


}
