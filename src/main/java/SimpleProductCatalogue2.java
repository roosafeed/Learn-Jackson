// #4 - Save and retrieve

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleProductCatalogue2 {
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
    }

    public static void main(String[] args) throws IOException {
        List<Product> products = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String option;
        boolean exit = false;

        File file = new File("productsCatalogueFile.txt");

        while(!exit) {
            System.out.print("1: add product | 2: list all products | 3: save all | 4: load from file | 5: exit\nChoose an option: ");
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
                case "3" : writeToFile(file, products);
                    break;
                case "4" : products = readFromFile(file);
                    break;
                case "5" :  exit = true;
            }
        }
    }

    public static void writeToFile(File file, List<Product> products) throws IOException {
        FileWriter writer = new FileWriter(file);

        for(Product product : products) {
            writer.write(product.toString());
        }

        System.out.println("saved!");
        writer.close();
    }

    public static List<Product> readFromFile(File file) throws IOException {
        List<Product> products = new ArrayList<>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] productDesc = line.split(" - ");
            Product product = new Product(productDesc[0], productDesc[1], Float.parseFloat(productDesc[2]));
            products.add(product);
        }

        System.out.println("read!");
        bufferedReader.close();
        return products;
    }
}
