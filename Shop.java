public class Shop
{
    private static double balance = 1110.01;
    public static final Upgrades[][] upgrades = 
        {{Launchers.TIER_1_PATH_1, Launchers.TIER_2_PATH_1, Launchers.TIER_3_PATH_1, Launchers.TIER_4_PATH_1},
            {Launchers.TIER_1_PATH_2, Launchers.TIER_2_PATH_2, Launchers.TIER_3_PATH_2,Launchers.TIER_4_PATH_2}, 
            {Enhancements.TIER_1_PATH_1, Enhancements.TIER_2_PATH_1, Enhancements.TIER_3_PATH_1, Enhancements.TIER_4_PATH_1},
            {Enhancements.TIER_1_PATH_2, Enhancements.TIER_2_PATH_2, Enhancements.TIER_3_PATH_2, Enhancements.TIER_4_PATH_2}, 
            {Boosters.TIER_1_PATH_1, Boosters.TIER_2_PATH_1, Boosters.TIER_3_PATH_1, Boosters.TIER_4_PATH_1},
            {Boosters.TIER_1_PATH_2, Boosters.TIER_2_PATH_2, Boosters.TIER_3_PATH_2, Boosters.TIER_4_PATH_2}, 
            {Mount.TIER_1_PATH_1, Mount.TIER_2_PATH_1, Mount.TIER_3_PATH_1, Mount.TIER_4_PATH_1},
            {Mount.TIER_1_PATH_2, Mount.TIER_2_PATH_2, Mount.TIER_3_PATH_2, Mount.TIER_4_PATH_2}};

    //Buys the upgrade. Returns false if the purchase is unsuccessful
    public static boolean buy(Upgrades u)
    {
        if((u.getCost() <= balance) && !u.getIsPurchased())
        {
            balance -= u.getCost();
            u.setIsPurchased(true);
            return true;
        } else {
            return false;
        }
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
