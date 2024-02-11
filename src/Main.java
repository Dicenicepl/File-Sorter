import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //todo Create GUI
        //todo Create background process
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose");
        new Schedule().test(scanner.nextByte());
    }
}