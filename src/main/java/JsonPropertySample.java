// #9 - JsonProperty

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonPropertySample {
    public static class Product {
        String name;
        String color;
        float price;
        float discountPrice;

        Product(String name, String color, float price) {
            this.name = name;
            this.color = color;
            this.price = price;
        }

        Product() {}

        public String getName() {
            return this.name;
        }

        public String getColor() {
            return this.color;
        }

        // serialize price field as originalPrice
        @JsonProperty("originalPrice")
        public float getPrice() {
            return this.price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setColor(String color) {
            this.color = color;
        }

        // deserialize price field from json
        @JsonProperty("price")
        public void setPrice(float price) {
            this.price = price;
        }

        // serialize discountPrice field as price
        @JsonProperty("price")
        public float getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(float discountPrice) {
            this.discountPrice = discountPrice;
        }
    }

    public static List<Product> deserialize(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Product> products = objectMapper.readValue(json, new TypeReference<List<Product>>() {});
        return products;
    }

    public static void printJSON(List<Product> products) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(products));
    }

    public static void main(String[] args) throws IOException {
        File file = new File("productsCatalogueFileJSON-Jackson.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<Product> products = deserialize(bufferedReader.readLine());

        products.forEach(product -> {product.setDiscountPrice(product.getPrice() * 0.75F);});

        printJSON(products);

        bufferedReader.close();
    }
}
