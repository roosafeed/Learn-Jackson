// #1 - Getting user input

import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        // import Scanner from java.util.Scanner
        // create an instance of Scanner and pass an input stream
        // System.in is the standard input stream, typically the keyboard input in the console
        // Run the following code and input "string 1 1.002 string2" in the console
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = scanner.nextInt();
        float f = scanner.nextFloat();
        // The methods above will not read the trailing \n
        String stringWithReturn = scanner.nextLine();

        System.out.println(s + " " + n + " " + f + " " + stringWithReturn);
        System.out.println(s);
        System.out.println(n);
        System.out.println(f);
        System.out.println(stringWithReturn);

        // close the scanner to free system resources
        scanner.close();
    }
}
