import java.util.ArrayList;
public class Shop
{
    private static double balance = 0.01;
    private ArrayList<Upgrades> upgrades; //In case we add more upgrades in the future

    public Shop(ArrayList<Upgrades> upgrades)
    {
        this.upgrades = upgrades;
    }
    
    public static void buy(Upgrades up)
    {
        //will check if the player can buy the upgrade (enough money and not purchased),
        //invoke a method that will change isPurchased to true, and subtract from balance
    }
    
    public static double getBalance()
    {
        return balance;
    }
    
    //Could possibly be exploited when public, but the Powerups and Score classes depend on this method
    public static void setBalance(double amount)
    {
        balance += amount;
    }
}
