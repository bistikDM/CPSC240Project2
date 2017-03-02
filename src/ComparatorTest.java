
import java.util.ArrayList;
import java.util.Collections;

public class ComparatorTest 
{
    public static void main(String[] args)
    {
        FoodProduct item1 = new FoodProduct("RiceCrunchies", 11112228, 1, 4.99, 1012016);
        FoodProduct item2 = new FoodProduct("CocoaCrunchies", 11112222, 22, 5.99, 1012019);
        FoodProduct item3 = new FoodProduct("Oranges", 11112227, 1000, 1.99, 4012015);
        FoodProduct item4 = new FoodProduct("Apples", 11112228, 33, 0.99, 3102015);
        FoodProduct item5 = new FoodProduct("HotChocolateMix", 11112228, 3, 4.99, 1012016);
        FoodProduct item6 = new FoodProduct("CheeseCurlz", 11112226, 18, 2.95, 1012077);
        FoodProduct item7 = new FoodProduct("Milk", 11112223, 1, 2.99, 1012022);
        
        ArrayList<FoodProduct> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        
        ComparatorByUPC sortUPC = new ComparatorByUPC();
        ComparatorByDate sortDate = new ComparatorByDate();
        
        Collections.sort(list, sortUPC);
        System.out.println("Sorted by UPC:");
        for(FoodProduct i : list)
            System.out.println(i.getInfo());
        
        Collections.sort(list,sortDate);
        System.out.println("Sorted by Date: ");
        for(FoodProduct i : list)
            System.out.println(i.getInfo());
    }
} 

//fix the long conversion back to int and find solution! *fixed*
//leading zeros in int gets treated as octal and not actual numbers!

//fix dates where month is > year, sorting is not accurate. *fixed*
//Converted to GregorianCalendar, see math in FoodProduct class, assuming the format is mmDDyyyy!