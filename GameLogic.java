//The purpose of this class is to set up a launch
import javafx.geometry.Point2D;
public class GameLogic
{
    public static double time, deltaTime, endTime, x, y, speed, angle, ay, ax, vx, vy;

    public GameLogic(double speed, double angle, double accelerationX, double startHeight, double startDistance, double gravityA)
    {
        time = 0.0;
        deltaTime = 0.0001;
        endTime = 10.0;
        x = startDistance;
        y = startHeight;
        this.speed = speed;
        this.angle = angle;
        ay = gravityA;
        ax = accelerationX;
        this.vx = this.speed*Math.cos(this.angle*(Math.PI/180.0));
        this.vy = this.speed*Math.sin(this.angle*(Math.PI/180.0));
    }
    //Precondition: Player must choose what planet they want to be on with a button
    //This begins the launch
    public void init(Planet world, Player player)
    {
        calculateMass(player);
        //Code that calculates initial speed based on mass and launcher's power
    }

    public Point2D getPoint()
    {
        time += this.deltaTime;
        this.x += this.vx*this.deltaTime;
        this.y += this.vy*this.deltaTime;
        this.vx += this.ax*this.deltaTime;
        this.vy += this.ay*this.deltaTime;
        return new Point2D(x, y);
    }

    //Could be expoited if public, but crucial to the SpeedBoost class
    public static void increaseAccelX(double amount)
    {
        ax += amount;
    }

    public static double getAccelX()
    {
        return ax;
    }

    public void calculateMass(Player player)
    {
        double mass = 20.0; //This is the mass of the player without upgrades
        mass += player.getBoosters().getMass() + player.getMount().getMass() + player.getEnhancement().getMass();
    }

    public static void main(String[] args) {
        GameLogic logic = new GameLogic(60.0, 45.0, 0.0, 10, 1.0, -9.8);
        while(!(y <= 0.0))
        {
            System.out.println(logic.getPoint().getX() + " " + logic.getPoint().getY());
        }
    }
}
