//The purpose of this class is to set up a launch
import javafx.geometry.Point2D;
public class GameLogic
{
    //Speeds are in m/s. Accelerations are in m/s2.
    //ay and ax are related to vertical and horizontal accelerations. vx and vy are related to vertical and horizontal velocities
    //x and y are in meters
    //Player mass is in kg
    private double time, deltaTime, endTime, x, y, speed, angle, ay, ax, vx, vy, playerMass, playerCrossSectionalArea;
    public GameLogic(double speed, double angle, double accelerationX, double startHeight, double startDistance, double accelerationY, double playerMass, double playerCrossSectionalArea)
    {
        time = 0.0;
        deltaTime = 0.0001;
        endTime = 10.0;
        x = startDistance;
        y = startHeight;
        this.speed = speed;
        this.angle = angle;
        ay = accelerationY;
        ax = accelerationX;
        this.playerMass = playerMass;
        this.playerCrossSectionalArea = playerCrossSectionalArea;
        this.vx = this.speed*Math.cos(this.angle*(Math.PI/180.0));
        this.vy = this.speed*Math.sin(this.angle*(Math.PI/180.0));
    }
    //Precondition: Player must choose what planet they want to be on with a button
    //This begins the launch
    public void init(Score score)
    {
        this.getPoint().getX();
        this.getPoint().getY();
        score.updateStats(y, time, x, vx);
        score.calculateScore();
        GUI.updateLaunchLabels(this, score);
    }

    public Point2D getPoint()
    {
        //Calculations below account for air drag
        double pressure = 101.325 * Math.pow(1 - (0.0065 * y / 288.15), (Terrain.gravity * 0.0289644) / (8.31447 * 0.0065)); //See Density of Air wiki
        double density = (pressure * 0.0289644) / (8.31447 * 288.15);
        double airResistance = density * 0.47 * this.playerCrossSectionalArea * 0.5 * Math.pow(vx, 2.0); //In newtons
        ax = -1.0 * airResistance * this.playerMass; //F = ma

        time += this.deltaTime;
        this.x += this.vx*this.deltaTime;
        this.y += this.vy*this.deltaTime;
        this.vx += this.ax*this.deltaTime;
        this.vy += this.ay*this.deltaTime;
        return new Point2D(x, y);
    }

    //Input must be in newtons
    public void boost(double amount)
    {
        this.ax += amount / playerMass;
    }

    public double get_ax()
    {
        return this.ax;
    }

    public double get_vx()
    {
        return this.vx;
    }

    public double get_x()
    {
        return this.x;
    }

    public double get_y()
    {
        return this.y;
    }

    //In m/s, DOES NOT ADD AMOUNT TO TOTAL, IT ONLY CHANGES IT
    public void set_vx(double amount)
    {
        vx = amount;
    }

    public void setAngle(double angle)
    {
        this.angle += angle;
    }

    public double getPlayerMass()
    {
        return playerMass;
    }

    public double getTime()
    {
        return time;
    }

    //For testing purposes
    public static void main(String[] args) {
        Player player = new Player();
        Shop.buy(Shop.upgrades[0][0]);
        Shop.upgrades[0][0].equip(player);
        double playerMass = player.calculateMass(); //test
        double playerCrossSectionalArea = player.getCrossSectionalArea();
        double initialSpeed = player.getLauncher().getPower(); //NEEDS REWORK, NEWTONS != m/s
        double lift = 0.0;
        if(player.getMount() != null)
        {
            lift = player.getMount().getLift();
        }
        double accelerationY = -Terrain.gravity + (lift / playerMass);
        double angle = player.getLauncher().getAngle();
        GameLogic logic = new GameLogic(initialSpeed, angle, 0.0, 10.0, 1.0, accelerationY, playerMass, playerCrossSectionalArea);
        while(!(logic.y <= 0.0))
        {
            System.out.println(logic.getPoint().getX() + " " + logic.getPoint().getY());
        }
    }
}
