
import java.util.Scanner;
import java.util.Stack;

public class Main {

    // Method to find operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    // Method to convert infix to postfix
    static String infixToPostfix(String infix) {

        Stack<Character> stack = new Stack<>();
        String postfix = "";

        for (int i = 0; i < infix.length(); i++) {

            char ch = infix.charAt(i);

            // If operand, add to postfix
            if (Character.isLetterOrDigit(ch)) {
                postfix += ch;
            }

            // If opening bracket, push into stack
            else if (ch == '(') {
                stack.push(ch);
            }

            // If closing bracket, pop until '('
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }

            // If operator
            else {
                while (!stack.isEmpty() && stack.peek() != '(' &&
                       (precedence(stack.peek()) > precedence(ch) ||
                       (precedence(stack.peek()) == precedence(ch) && ch != '^'))) {

                    postfix += stack.pop();
                }

                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Infix Expression: ");
        String infix = sc.nextLine();

        String postfix = infixToPostfix(infix);

        System.out.println("Postfix Expression: " + postfix);

        sc.close();
    }
}
