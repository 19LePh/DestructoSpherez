/*The purpose of this class is to keep track of high scores and cumultive and individual acheivements
 * It can also be used to calculate the score after each run and convert to money */
public class Score
{
    private static double highScore = 0.0;
    private static boolean[] permAchievements = {false, false, false, false}; //Num of booleans = num walls
    private Wall[] walls;
    private boolean[] tempAchievements;
    
    public Score(Wall[] walls)
    {
        this.walls = walls;
        tempAchievements = new boolean[]{false, false, false, false};
    }
    
    public static void calculateScore(double maxHeight, double airTime, double distance, double maxVelocity)
    {
        double total = 0.0;
        if(total > highScore)
        {
            highScore = total;
        }
        //Code that calculates score and adds to total
        Shop.setBalance(total);
    }
    
    public double gethighScore()
    {
        return highScore;
    }
}
