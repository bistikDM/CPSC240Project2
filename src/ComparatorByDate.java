
import java.util.Comparator;

public class ComparatorByDate implements Comparator<FoodProduct>
{
    public int compare(FoodProduct item1, FoodProduct item2)
    {
        return (item1.getTime().compareTo(item2.getTime()));
    }
}
