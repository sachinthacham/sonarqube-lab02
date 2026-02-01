package main.java.com.example;

public class Calculator {

    public int calculate(int a, int b, String op) {
        // Switch expression (Java 14+) - cleaner and less error-prone
        return switch (op.toLowerCase()) {
            case "add" -> a + b;
            case "sub" -> a - b;
            case "mul" -> a * b;
            case "div" -> {
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                yield a / b;
            }
            case "mod" -> a % b;
            case "pow" -> (int) Math.pow(a, b);
            default -> throw new IllegalArgumentException("Unknown operation: " + op);
        };
    }

    // Kept one utility method if needed, but removed the intentional duplicates
    public int addNumbers(int x, int y) {
        return x + y;
    }
}