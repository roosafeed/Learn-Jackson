// #2 - Simple product catalogue

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleProductCatalogue1 {

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
            return name + " - " + color + " - " + price;
        }
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String option;
        boolean exit = false;

        while(!exit) {
            System.out.print("1: add product | 2: list all products | 3: exit\nChoose an option: ");
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
                case "2" : products.forEach(product -> {System.out.println(product.toString());});
                    break;
                case "3" :  exit = true;
            }
        }
    }
}

