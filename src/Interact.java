
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Interact 
{   
    static ArrayList<FoodProduct> product = new ArrayList<>();
    
    public static void main(String[] args)
    {    
        
        final String DEFAULT_FILE = "inventory.txt";
        Scanner in = new Scanner(System.in);
        int choice;
        boolean loop = true;
        Scanner fileRead = new Scanner(DEFAULT_FILE);
        while (fileRead.hasNextLine())
        {
            String line = in.nextLine();
            String[] item = line.split("\\s+");
            String name = item[0];
            int upc = Integer.parseInt(item[1].replaceFirst("^0+(?!$)", ""));
            int quantity = Integer.parseInt(item[2]);
            double cost = Double.parseDouble(item[3]);
            int expire = Integer.parseInt(item[4].replaceFirst("^0+(?!$)", ""));
            FoodProduct food = new FoodProduct(name, upc, quantity, cost, expire);
            product.add(food);
        }
        
        while (loop == true)
        {
            System.out.println("Welcome to Foods B Us Inventory Management System.");
            System.out.println("The inventory in the file has been successfully imported.");
            System.out.println("\nPlease choose an option from the following menu:");
            System.out.println("(1) Upload product information for a delivery");
            System.out.println("(2) Print the current inventory sorted by product name");
            System.out.println("(3) List the current inventory sorted by expiration date");
            System.out.println("(4) Search for an item by product name");
            System.out.println("(5) Decrement the quantity for an item");
            System.out.println("(6) Remove/discontinue an item");
            System.out.println("(7) Quit the system");
            System.out.print("\n\nEnter your choice: ");
            try {
                choice = Integer.parseInt(in.nextLine());
                switch (choice) {
                    case 1:
                        upload();
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 2:
                        display();
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 3:
                        //Code goes here.
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 4:
                        //Code goes here.
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 5:
                        //Code goes here.
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 6:
                        removeItem();
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 7:
                        System.out.print("Please confirm to quit (1)Confirm   (2)Cancel: ");
                        try {
                            choice = Integer.parseInt(in.nextLine());
                            if (choice == 1)
                            {
                                quit();
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Input is not a valid number!");
                        }   break;
                    default:
                        System.out.println("Not a valid choice!");
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Input is not a valid number!");
            }
        }
    }

    private static void upload() 
    {
        File file;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        file = new File(in.nextLine() + ".txt");
        try {
        Scanner fileRead = new Scanner(file);
        while (fileRead.hasNextLine())
        {
            boolean newItem = true;
            String line = in.nextLine();
            String[] item = line.split("\\s+");
            String name = item[0];
            int upc = Integer.parseInt(item[1].replaceFirst("^0+(?!$)", ""));
            int quantity = Integer.parseInt(item[2]);
            double cost = Double.parseDouble(item[3]);
            int expire = Integer.parseInt(item[4].replaceFirst("^0+(?!$)", ""));
            FoodProduct addition = new FoodProduct(name, upc, quantity, cost, expire);
            for (FoodProduct i : product)
            {
                if (i.getName().equals(addition.getName()))
                {
                    newItem = false;
                    i.addQuantity(addition.getQuantity());
                }
            }
            if (newItem == true)
            {
                product.add(addition);
            }
        }
        } catch (FileNotFoundException ex) {
            System.out.println("The file does not exist!");
        }
    }
    
    public static void display()
    {
        ComparatorByDate sortDate = new ComparatorByDate();
        
        System.out.println(String.format("%20s %20s %20s %20s %20s", "Product Name", "UPC", "Quantity", "Cost", "Expiration Date"));
    }
    
    public static void searchProduct()
    {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a product name: ");
        name = in.nextLine();
        boolean checker = false;
        for (int i = 0; i < product.size(); i++)
        {
            if (name.equals(product.get(i).getName()))
            {
                checker = true;
                System.out.println("Current Stock report: ");
                System.out.println(product.get(i).getInfo());
            }
        }
        if (checker == false)
        {
            System.out.println("The item is not in the inventory!");
        }
    }

    private static void removeItem() 
    {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a product name: ");
        name = in.nextLine();
        boolean checker = false;
        for (int i = 0; i < product.size(); i++)
        {
            if (name.equals(product.get(i).getName()))
            {
                checker = true;
                System.out.println(product.get(i).getName() + " " + product.get(i).getUPC() + 
                        "has been removed from inventory.");
                product.remove(i);
            }
        }
        if (checker == false)
        {
            System.out.println("The item is not in the inventory!");
        }
    }
    
    public static void quit() //Not done!
    {
        System.exit(0);
    }
}
