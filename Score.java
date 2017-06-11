import java.text.DecimalFormat;
/*The purpose of this class is to keep track of high scores and cumultive and individual acheivements
 * It can also be used to calculate the score after each run and convert to money */
public class Score
{
    private static double topHeight = 0.0;
    private static double topAirTime = 0.0;
    private static double topDistance = 0.0;
    private static double topVelocity = 0.0;
    private static double highScore = 0.0;
    private static boolean[] permAchievements = {false, false, false, false}; //Num of booleans = num walls
    private boolean[] tempAchievements = new boolean[4];
    private double maxHeight, airTime, distance, maxVelocity, score, rewardMoney;
    private Wall[] walls;

    public Score(Wall[] walls)
    {
        this.walls = walls;
        tempAchievements = new boolean[]{false, false, false, false};
        maxHeight = 0.0;
        airTime = 0.0;
        distance = 0.0;
        maxVelocity = 0.0;
        score = 0.0;
        rewardMoney = 0.0;
    }

    public void calculateScore()
    {
        double total = 0.0;
        double rewardMoney = 0.0;
        //Code that calculates score and adds to total
        total += distance +(maxHeight / 5.0) + maxVelocity + (9.0 * airTime);
        if(tempAchievements[0])
        {
            permAchievements[0] = true;
            total += walls[0].getRewardMoney() * 100.0; //Convert money to score
            rewardMoney = walls[0].getRewardMoney();
        }
        if(tempAchievements[1])
        {
            permAchievements[1] = true;
            total += walls[1].getRewardMoney() * 100.0; //Convert money to score
            rewardMoney = walls[1].getRewardMoney();
        }
        if(tempAchievements[2])
        {
            permAchievements[2] = true;
            total += walls[2].getRewardMoney() * 100.0; //Convert money to score
            rewardMoney = walls[2].getRewardMoney();
        }
        if(tempAchievements[3])
        {
            permAchievements[3] = true;
            total += walls[3].getRewardMoney() * 100.0; //Convert money to score
            rewardMoney = walls[3].getRewardMoney();
        }
        if(total > highScore)
        {
            highScore = total;
        }
        //Code that converts score to money
        this.score = Double.parseDouble(new DecimalFormat("##.##").format(total));
        this.rewardMoney = rewardMoney + Double.parseDouble(new DecimalFormat("##.##").format((score / 100.0)));
    }

    public double getRewardMoney()
    {
        return rewardMoney;
    }

    public void updateStats(double height, double airTime, double distance, double velocity)
    {
        if(this.maxHeight < height)
        {
            this.maxHeight = height;
            if(maxHeight > topHeight)
            {
                topHeight = maxHeight;
            }
        }
        if(this.airTime < airTime)
        {
            this.airTime = airTime;
            if(airTime > topAirTime)
            {
                topAirTime = airTime;
            }
        }
        if(this.distance < distance)
        {
            this.distance = distance;
            if(distance > topDistance)
            {
                topDistance = distance;
            }
        }
        if(this.maxVelocity < velocity)
        {
            this.maxVelocity = velocity;
            if(maxVelocity > topVelocity)
            {
                topVelocity = maxVelocity;
            }
        }
    }
    
    public double getAirTime()
    {
        return airTime;
    }

    public double getMaxHeight()
    {
        return maxHeight;
    }

    public double getDistance()
    {
        return distance;
    }

    public double getMaxVelocity()
    {
        return maxVelocity;
    }

    public double getScore()
    {
        return score;
    }

    public boolean[] getTempAchievements()
    {
        return tempAchievements;
    }

    public static double getHighScore()
    {
        return highScore;
    }

    public static boolean[] getPermAchievements()
    {
        return permAchievements;
    }

    public static double getTopDistance()
    {
        return topDistance;
    }

    public static double getTopHeight()
    {
        return topHeight;
    }

    public static double getTopVelocity()
    {
        return topVelocity;
    }

    public static double getTopAirTime()
    {
        return topAirTime;
    }

    public static String permAchToString()
    {
        String text = "Walls Destroyed: ";
        if(permAchievements[0])
        {
            text += "1!";
        }
        if(permAchievements[1])
        {
            text += ", 2!";
        }
        if(permAchievements[2])
        {
            text += ", 3!";
        }
        if(permAchievements[3])
        {
            text += ", 4!";
        }
        if(text.equals("Walls Destroyed: "))
        {
            text = "Walls Destroyed: None -_-";
        }
        return text;
    }

    public String tempAchToString()
    {
        String text = "Walls Destroyed: ";
        if(this.tempAchievements[0])
        {
            text += "1!";
        }
        if(this.tempAchievements[1])
        {
            text += ", 2!";
        }
        if(this.tempAchievements[2])
        {
            text += ", 3!";
        }
        if(this.tempAchievements[3])
        {
            text += ", 4!";
        }
        if(text.equals("Walls Destroyed: "))
        {
            text = "Walls Destroyed: None -_-";
        }
        return text;
    }
}
