package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;
import java.util.regex.Pattern;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here

        if (!isCorrectProblem(statement))
            return null;

        double a = RPNToSolution(stringToRPN(statement));
        if (a==Double.POSITIVE_INFINITY || a == Double.NEGATIVE_INFINITY){
            return null;
        }
        int b = (int) a;
        if (a - b == 0) return Integer.toString(b);
        return Double.toString(a);
    }

    private boolean isCorrectProblem(String st) {
        if (st==null || st.length()==0) return false;
        if (Pattern.matches("[+,-,*,/,(]", st.substring(st.length() - 1))) return false;
        if (Pattern.matches("[+,*,/,)]", st.substring(0, 1))) return false;

        int parentesisOpen = 0, parentesisClose = 0;
        for (int i = 0; i < st.length(); i++) {
            if ('(' == st.charAt(i)) parentesisOpen++;
            if (')' == st.charAt(i)) parentesisClose++;
        }
        if (parentesisClose != parentesisOpen) return false;

        for (int i = 2; i < st.length(); i++) {
            if (Pattern.matches("[+,\\-,*,/,.]", st.substring(i - 2, i - 1))) {
                if (Pattern.matches("[+,\\-,*,/,.]", st.substring(i - 1, i))) return false;
            }
        }
        if (st.contains(",")) return false;
        return true;
    }

    private String stringToRPN(String problem) {
        String rpn = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < problem.length(); i++) {
            if (getPriority(problem.charAt(i)) == 0) rpn += problem.charAt(i);
            if (getPriority(problem.charAt(i)) == 1) stack.push(problem.charAt(i));
            if (getPriority(problem.charAt(i)) > 1) {
                rpn += " ";
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= getPriority(problem.charAt(i))) rpn += stack.pop();
                    else break;
                }
                stack.push(problem.charAt(i));
            }
            if (getPriority(problem.charAt(i)) == -1) {
                rpn += " ";
                while (getPriority(stack.peek()) != 1) {
                    rpn += stack.pop();
                }
                stack.pop();
            }
        }

        while (!stack.empty()) {
            rpn += " ";
            rpn += stack.pop();
        }
        return rpn;
    }

    private double RPNToSolution(String rpn) {
        String number = "";
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ') {
                    number += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(number));
                number = "";
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                double x = stack.pop(), y = stack.pop();

                switch (rpn.charAt(i)) {
                    case '+':
                        stack.push(y + x);
                        break;
                    case '-':
                        stack.push(y - x);
                        break;
                    case '*':
                        stack.push(y * x);
                        break;
                    case '/':
                        stack.push(y / x);
                        break;
                }
            }
        }
        return stack.pop();
    }

    private int getPriority(char symbol) {
        if (symbol == '*' || symbol == '/') return 3;
        else if (symbol == '+' || symbol == '-') return 2;
        else if (symbol == '(') return 1;
        else if (symbol == ')') return -1;
        else return 0;
    }
}