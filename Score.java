/*The purpose of this class is to keep track of high scores and cumultive and individual acheivements
 * It can also be used to calculate the score after each run and convert to money */
public class Score
{
    private static double highScore = 0.0;
    private static boolean[] isAchievedP = {false, false, false, false}; //Num of booleans = num walls, P stands for plural
    private Wall[] walls;
    private boolean[] isAchievedI; //For individual launches, I stands for individual
    
    public Score(Wall[] walls)
    {
        this.walls = walls;
        isAchievedI = new boolean[]{false, false, false, false};
    }
    
    public static double calculateScore(double maxHeight, double airTime, double distance, double maxVelocity)
    {
        double total = 0.0;
        if(total > highScore)
        {
            highScore = total;
        }
        return 0.0; //Depends on units, will implement later
    }
    
    public double gethighScore()
    {
        return highScore;
    }
}
