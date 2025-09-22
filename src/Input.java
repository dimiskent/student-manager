import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);
    public void clear() {
        scanner.nextLine();
    }
    public void close() {
        scanner.close();
    }
    public String getString() {
        System.out.print("Enter a name for the student: ");
        return scanner.nextLine();
    }
    public double getNumber(int min, int max) {
        double result;
        while (true) {
            if(scanner.hasNextDouble()) {
                result = scanner.nextDouble();
                if(result >= min && result <= max) {
                    break;
                } else {
                    System.out.printf("Must be between %d and %d!\n", min, max);
                }
            } else {
                System.out.println("Please input a number!");
                scanner.next();
            }
            System.out.print("Enter a number: ");
        }
        return result;
    }
}
