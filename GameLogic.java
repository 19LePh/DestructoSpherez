//The purpose of this class is to set up a launch
import javafx.geometry.Point2D;
public class GameLogic
{
    //Speeds are in m/s. Accelerations are in m/s2.
    //ay and ax are related to vertical and horizontal accelerations. vx and vy are related to vertical and horizontal velocities
    public static double time, deltaTime, endTime, x, y, speed, angle, ay, ax, vx, vy;

    public GameLogic(double speed, double angle, double accelerationX, double startHeight, double startDistance, double gravityA, boolean hasAirDrag)
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
        //Below is for planets with air drag
        if(hasAirDrag)
        {
            //drag equation
        }
        this.vx = this.speed*Math.cos(this.angle*(Math.PI/180.0));
        this.vy = this.speed*Math.sin(this.angle*(Math.PI/180.0));
    }
    //Precondition: Player must choose what planet they want to be on with a button
    //This begins the launch
    public static void init(Planet world, Player player)
    {
        double playerMass = (new Player()).calculateMass();
        double acceleration = /*Launcher force*/1 / playerMass; //F = ma
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
    //Input must be an acceleration
    public static void increaseAccelX(double amount)
    {
        ax += amount;
    }

    public static double getAccelX()
    {
        return ax;
    }

    public void setAngle(double angle)
    {
        this.angle += angle;
    }


    public static void main(String[] args) {
        double playerMass = (new Player()).calculateMass();
        double acceleration = /*Launcher force*/1 / playerMass; //F = ma
        GameLogic logic = new GameLogic(60.0, 45.0, acceleration, 10, 1.0, -9.8, false); //replace boolean with (planet object).getHasAirDrag()
        while(!(y <= 0.0))
        {
            System.out.println(logic.getPoint().getX() + " " + logic.getPoint().getY());
        }
    }
}
