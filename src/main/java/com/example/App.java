package main.java.com.example;

public class App {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        UserService service = new UserService();

        try {
            // Updated "add-again" to "add" as duplicates were removed
            int result = calc.calculate(10, 5, "add");
            System.out.println("Calculation Result: " + result);
            
            // Database operations
            service.findUser("admin");
            service.deleteUser("admin");

        } catch (IllegalArgumentException | ArithmeticException e) {
            System.err.println("Calculation Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}