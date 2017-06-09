//The purpose of this class is to set up a launch
import javafx.geometry.Point2D;
public class GameLogic
{
    //Speeds are in m/s. Accelerations are in m/s2.
    //ay and ax are related to vertical and horizontal accelerations. vx and vy are related to vertical and horizontal velocities
    //x and y are in meters
    //Player mass is in grams
    public static double time, deltaTime, endTime, x, y, speed, angle, ay, ax, vx, vy, playerMass, playerCrossSectionalArea;
    public static boolean hasAirDrag;

    public GameLogic(double speed, double angle, double accelerationX, double startHeight, double startDistance, double gravityA, boolean hasAirDrag,  double playerMass, double playerCrossSectionalArea)
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
        this.hasAirDrag = hasAirDrag;
        this.playerMass = playerMass;
        this.playerCrossSectionalArea = playerCrossSectionalArea;
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
        if(hasAirDrag)
        {
            //Calculations for air density
            double pressure = 101.325 * Math.pow(1 - (0.0065 * y / 288.15), (9.80665 * 0.0289644) / (8.31447 * 0.0065)); //See Density of Air wiki
            double density = (pressure * 0.0289644) / (8.31447 * 288.15);
            //Calculations for air resistance
            double airResistance = density * 0.47 * this.playerCrossSectionalArea * 0.5 * Math.pow(vx, 2.0); //In newtons
            //F = ma
            ax = -1.0 * airResistance * (this.playerMass / 1000.0);
        }
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
        Player player = new Player();
        double playerMass = player.calculateMass(); //test
        double playerCrossSectionalArea = player.getCrossSectionalArea();
        double initialSpeed = 60.0;
        GameLogic logic = new GameLogic(initialSpeed, player.getLauncher().getAngle(), 0.0, 10, 1.0, -9.80665, false, playerMass, playerCrossSectionalArea); //replace boolean with (planet object).getHasAirDrag()
        while(!(y <= 0.0))
        {
            System.out.println(logic.getPoint().getX() + " " + logic.getPoint().getY());
        }
    }
}
