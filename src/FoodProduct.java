import java.text.NumberFormat;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * This class creates the FoodProduct object which extends the Product object.
 * @author Daniel Mulyono
 */

public class FoodProduct extends Product
{
    private int upc; //UPC of the food product.
    private int expire; //Expiration date of the food product.
    private int day;
    private int month;
    private int year;
    private GregorianCalendar gregCal;
    
    /**
     * Constructor used to initialize all values.
     * @param name The name of the object.
     * @param upc The UPC of the object
     * @param quantity The quantity of the object.
     * @param cost The price of the object.
     * @param expire The expiration date of the object.
     */
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
    
    /**
     * This method increases the quantity amount.
     * @param quantity The number to add to the object's quantity.
     */
    public void addQuantity(int quantity)
    {
        this.quantity += quantity;
    }
    
    /**
     * This method returns the gregorian calendar version of the expire data member for comparator.
     * @return Calendar to be used for comparator.
     */
    public GregorianCalendar getTime()
    {
        return gregCal;
    }
    
    /** 
     * This is a getter method for the UPC.
     * @return The UPC of the object.
     */
    public int getUPC()
    {
        return upc;
    }
    
    /**
     * This is a getter method for the expiration date.
     * @return The expiration date of the object.
     */
    public int getExpire()
    {
        return expire;
    }
    
    /**
     * This is a getter method for the price of the object.
     * @return The price of the object formatted to US currency.
     */
    public String getCost()
    {
        Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        Currency currentCurrency = Currency.getInstance(currentLocale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyFormatter.format(price);
    }
    
    /**
     * This method decreases the quantity of the object by one.
     */
    public void decrement()
    {
        quantity--;
    }
    
    /**
     * This method creates a string representation of the object.
     * @return String representation of the object.
     */
    public String getInfo()
    {
        String info = name + " " + String.format("%08d", this.upc) + super.toString().replaceFirst(name, " ") + String.format("%08d", this.expire);
        return info;
    }
}
