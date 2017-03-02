import java.text.NumberFormat;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FoodProduct extends Product
{
    private int upc; //UPC of the food product.
    private int expire; //Expiration date of the food product.
    private int day;
    private int month;
    private int year;
    private GregorianCalendar gregCal;
    
    public FoodProduct(String name, int upc, int quantity, double cost, int expire)
    {
        super(name, quantity, cost);
        this.upc = upc;
        this.expire = expire;
        year = expire % 10000;
        month = expire / 1000000;
        day = (expire % 1000000) / 10000;
        gregCal = new GregorianCalendar(year, month, day);
    }
    
    public void addQuantity(int quantity)
    {
        this.quantity += quantity;
    }
    
    public GregorianCalendar getTime()
    {
        return gregCal;
    }
    
    public int getUPC()
    {
        return upc;
    }
    
    public int getExpire()
    {
        return expire;
    }
    
    public String getCost()
    {
        Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        Currency currentCurrency = Currency.getInstance(currentLocale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(price);
    }
    
    public void decrement(int upc)
    {
        if (this.upc == upc)
        {
            if (quantity > 0)
            {
                quantity--;
                System.out.println("Updated stock information:");
                System.out.println(name + "\t" + this.upc + "\t" + quantity + "\t" + 
                        price + "\t" + expire);
            }
            else
            {
                System.out.println("The quantity is zero, please check inventory!");
            }
        }
        else
        {
            System.out.println("ERROR: This item is not found in the inventory record.");
        }
    }
    
    public String getInfo()
    {
        String info = name + " " + String.format("%08d", this.upc) + super.toString().replaceFirst(name, " ") + String.format("%08d", this.expire);
        return info;
    }
}
