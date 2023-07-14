// #3 - Files!

import java.io.*;
import java.util.Scanner;

public class FileHandler {
    // FileWriter and FileReader can throw IOException
    public static void main(String[] args) throws IOException {
        // create a new File object
        File file = new File("testFile.txt");
        // this will create a new file if not exist
        FileWriter writer = new FileWriter(file);
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        // writes over the existing contents of the file
        writer.write(input);
        writer.close();

        FileReader reader = new FileReader(file);
        // use BufferedReader for easier and efficient reading
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        // readLine() reads each line and returns null if there's no more data
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }
}
