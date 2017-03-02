import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This is the main class which interacts with the user.
 * @author Daniel Mulyono
 */
public class Interact 
{   
    static ArrayList<FoodProduct> product = new ArrayList<>();
    
    public static void main(String[] args) throws IOException
    {    
        /*
        This block loads the inventory.txt file into the program, then split the string by whitespace character.
        Then creates an array of each line which will be used to create FoodProduct object.
        The UPC's and expiration's leading zeros will be removed before creating the object.
        */
        final String DEFAULT_FILE = "inventory.txt";
        Scanner in = new Scanner(System.in);
        int choice;
        boolean loop = true;
        File file = new File(DEFAULT_FILE);
        Scanner fileRead = new Scanner(file);
        while (fileRead.hasNextLine())
        {
            String line = fileRead.nextLine();
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
                        displayByName();
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 3:
                        displayByDate();
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 4:
                        searchProduct();
                        in.nextLine();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        break;
                    case 5:
                        decrement();
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

    /**
     * This method will upload any additional .txt files into the program.
     */
    private static void upload() 
    {
        File file;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        String text = in.nextLine();
        file = new File(text + ".txt");
        try {
        Scanner fileRead = new Scanner(file);
        while (fileRead.hasNextLine())
        {
            boolean newItem = true;
            String line = fileRead.nextLine();
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
    
    /*
    This method will sort the ArrayList by name and then display it.
    */
    public static void displayByName()
    {
        Collections.sort(product);
        System.out.println(String.format("%20s %15s %10s %8s %20s", "Product Name", "UPC", "Quantity", "Cost", "Expiration Date"));
        for (FoodProduct i : product)
        {
            System.out.println(String.format("%20s %15s %10s %8s %20s", i.getName(), String.format("%08d", i.getUPC()), i.getQuantity(), i.getCost(), String.format("%08d", i.getExpire())));
        }
    }
    
    /*
    This method will sort the ArrayList by expiration date and then display it.
    */
    public static void displayByDate()
    {
        ComparatorByDate sortDate = new ComparatorByDate();
        Collections.sort(product, sortDate);
        System.out.println(String.format("%20s %20s %15s %10s %8s", "Expiration Date", "Product Name", "UPC", "Quantity", "Cost"));
        for (FoodProduct i : product)
        {
            System.out.println(String.format("%20s %20s %15s %10s %8s", String.format("%08d", i.getExpire()), i.getName(), String.format("%08d", i.getUPC()), i.getQuantity(), i.getCost()));
        }
    }
    
    /*
    This method will search the ArrayList for an object with a matching name and display information about the object.
    */
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
    
    /*
    This method will search the ArrayList for an object with a matching UPC, decrement the quantity by, and display the updated information.
    */
    public static void decrement()
    {
        boolean found = false;
        String upc;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the UPC: ");
        upc = in.nextLine();
        for (FoodProduct i : product) 
        {
            if (Integer.toString(i.getUPC()).equals(upc))
            {
                found = true;
                if (i.getQuantity() > 0)
                {
                    i.decrement();
                    System.out.println("Updated stock information: ");
                    System.out.println(String.format("%20s %15s %10s %8s %12s", i.getName(), i.getUPC(), i.getQuantity(), i.getCost(), i.getExpire()));
                }
                else
                {
                    System.out.println("Inventory quantity is zero!");
                }
            }
        }
        if (found == false)
        {
            System.out.println("ERROR: This item is not found in the inventory records.");
        }
    }

    /*
    This method will remove an object with a matching name from the ArrayList.
    */
    public static void removeItem() 
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
                        " has been removed from inventory.");
                product.remove(i);
            }
        }
        if (checker == false)
        {
            System.out.println("The item is not in the inventory!");
        }
    }
    
    /*
    This method will overwrite the inventory.txt file with the ArrayList and then exit the program.
    */
    public static void quit() throws IOException
    {
        try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter("inventory.txt"))) {
            for (FoodProduct i : product)
            {
                fileWrite.write(i.getName() + " " + Integer.toString(i.getUPC()) + " " + Integer.toString(i.getQuantity()) + " " + 
                        Double.toString(i.getPrice()) + " " + Integer.toString(i.getExpire()));
                fileWrite.newLine();
            }
            fileWrite.close();
        }
        System.exit(0);
    }
}
