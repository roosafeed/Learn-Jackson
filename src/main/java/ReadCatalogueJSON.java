// #6 - Manual deserialization of JSON

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadCatalogueJSON {
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

    public static List<Product> deserialise(String json) {
        List<Product> products = new ArrayList<>();

        // remove [{ from the beginning and
        // }] from the end
        json = json.substring(2, json.length() - 2);

        // remove all the quotes and split into individual json objects
        String[] jsonObjects = json.replace("\"", "").split("\\},\\{");

        for(String obj : jsonObjects) {
            String name = "";
            String color = "";
            float price = 0.00F;

            // separate the field
            String[] fields = obj.split(",");
            for(String field : fields) {
                // split the field into key and value
                String[] keyValue = field.split(":");
                switch (keyValue[0]) {
                    case "name" : name = keyValue[1]; break;
                    case "color" : color = keyValue[1]; break;
                    case "price" : price = Float.parseFloat(keyValue[1]);
                }
            }
            products.add(new Product(name, color, price));
        }

        return products;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("productsCatalogueFileJSON.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // since our json data was printed in one single line in our previous activity,
        // it is sufficient to read just one line
        String json = bufferedReader.readLine();

        List<Product> products = deserialise(json);

        products.forEach(product -> {System.out.println(product.toString());});

        bufferedReader.close();
    }
}
