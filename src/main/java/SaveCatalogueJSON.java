// #5 - Save the catalogue as JSON

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SaveCatalogueJSON {
    public static class Product {
        String name;
        String color;
        float price;
        Product(String name, String color, float price) {
            this.name = name;
            this.color = color;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " - " + color + " - " + price + "\n";
        }
        
        public String toJSON() {
            return "{" +
                    "\"name\":\"" + name +
                    "\",\"color\":\"" + color +
                    "\",\"price\":" + price +
                    "}";
        }
    }

    public static void main(String[] args) throws IOException {
        List<Product> products = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String option;
        boolean exit = false;

        File file = new File("productsCatalogueFileJSON.json");

        while(!exit) {
            System.out.print("1: add product | 2: list all products | 3: save as JSON | 4: exit\nChoose an option: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" : System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Price: ");
                    float price = scanner.nextFloat();
                    scanner.nextLine();
                    products.add(new Product(name, color, price));
                    break;
                case "2" : products.forEach(product -> {System.out.print(product.toString());});
                    break;
                case "3" : writeToFileAsJSON(file, products);
                    break;
                case "4" :  exit = true;
            }
        }
    }

    public static void writeToFileAsJSON(File file, List<Product> products) throws IOException {
        FileWriter writer = new FileWriter(file);

        writer.write("[");
        // iterator provides a way to sequentially access elements in a collection
        // without the need for an explicit index or maintaining a separate counter variable
        Iterator<Product> iterator = products.iterator();
        // hasNext() returns true if there's a next element
        // we can use it to determine when to use ','
        while(iterator.hasNext()) {
            writer.write(iterator.next().toJSON());
            if(iterator.hasNext()) {
                writer.write(",");
            }
        }

        writer.write("]");

        System.out.println("saved!");
        writer.close();
    }
}
