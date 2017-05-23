import java.util.ArrayList;
public class Shop
{
    private double balance;
    private ArrayList<Upgrades> upgrades; //In case we add more upgrades in the future

    public Shop(ArrayList<Upgrades> upgrades)
    {
        balance = 0.01;
        this.upgrades = upgrades;
    }
    
    public void buy(Upgrades up)
    {
        //will check if the player can buy the upgrade (enough money and not purchased),
        //invoke a method that will change isPurchased to true, and subtract from balance
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    //Must be private so that the player cannot give himself money somehow
    private void setBalance(double amount)
    {
        balance += amount;
    }
}
