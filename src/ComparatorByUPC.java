
import java.util.Comparator;

public class ComparatorByUPC implements Comparator<FoodProduct>
{
    public int compare(FoodProduct item1, FoodProduct item2)
    {
        return (item1.getUPC() - item2.getUPC());
    }
}
