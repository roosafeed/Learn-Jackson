// #7 - Serialize using Jackson

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveCatalogueJackson {

    public static class Product {
        String name;
        String color;
        float price;
        Product(String name, String color, float price) {
            this.name = name;
            this.color = color;
            this.price = price;
        }

        // define getter methods for the properties, so that Jackson can access them during serialization (or make the fields public)
        // by default, Jackson derives the JSON element names from the getter/setter method names
        // getName() becomes name
        public String getName() {
            return this.name;
        }

        public String getColor() {
            return this.color;
        }

        public float getPrice() {
            return this.price;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Product> products = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String option;
        boolean exit = false;

        File file = new File("productsCatalogueFileJSON-Jackson.json");

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
        // ObjectMapper is the main class in Jackson that handles JSON serialization and deserialization
        ObjectMapper mapper = new ObjectMapper();

        // serialize
        // writeValueAsString() takes an object and returns the JSON representation of that object as a string
        writer.write(mapper.writeValueAsString(products));

        System.out.println("saved!");
        writer.close();
    }
}
