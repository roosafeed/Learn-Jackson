// #8 - Deserialize JSON using Jackson

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DeserializeCatalogueJackson {
    public static class Product {
        String name;
        String color;
        float price;
        Product(String name, String color, float price) {
            this.name = name;
            this.color = color;
            this.price = price;
        }

        // default (no-argument) constructor is required by Jackson for proper deserialization of objects
        Product() {}

        // define getter methods for the properties, so that Jackson can access them during serialization
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

        public void setName(String name) {
            this.name = name;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }

    // readValue() can throw JsonProcessingException, ideally we should handle it
    public static List<Product> deserialize(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // readValue() deserializes the JSON into List<Product>
        // readValue() can also accept a File instead of String, so we can pass the file directly too
        // TypeReference object is used to specify the type of generic type, List<Product> in this case
        // The second parameter of readValue() can also be a class, eg. Product.class - if the json is just one Product
        List<Product> products = objectMapper.readValue(json, new TypeReference<List<Product>>() {});
        return products;
    }

    public static void printJSON(List<Product> products) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // withDefaultPrettyPrinter() formats the json string into a more readable (pretty) form
        System.out.println(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(products));
    }

    public static void main(String[] args) throws IOException {
        File file = new File("productsCatalogueFileJSON-Jackson.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<Product> products = deserialize(bufferedReader.readLine());

        // apply the discount
        products.forEach(product -> {product.setPrice(product.getPrice() * 0.75F);});

        printJSON(products);

        bufferedReader.close();
    }
}
