
import java.util.ArrayList;
import java.util.Collections;

public class ComparableTest 
{
    public static void main(String[] args)
    {
        Product orange = new Product("orange", 2, 2.99);
        Product banana = new Product("banana", 4, 1.95);
        Product apple = new Product("apple", 1, 0.99);
        Product grape = new Product("grape", 3, 2.50);
        
        ArrayList<Product> food = new ArrayList<>();
        food.add(orange);
        food.add(banana);
        food.add(apple);
        food.add(grape);
        
        Collections.sort(food);
        
        for (Product i : food)
            System.out.println(i.toString());
    }
}
