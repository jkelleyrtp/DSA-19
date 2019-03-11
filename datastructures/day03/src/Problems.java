import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // TODO: your code here
        // For now, return a List that's correct size, but contains only 0s
        //List<Integer> l = new LinkedList<>();
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            System.out.println("Testing: ");
            System.out.println(A[i]);

            while (k > 0 && !s.empty() && A[i] < s.peek()) {
                System.out.println("Popping");
                System.out.println(s.pop());
                k--;
            }
            System.out.println("Pushing");
            System.out.println(A[i]);
            s.push(A[i]);
            System.out.println(s.toString());
        }
        // Trim stack
        while( k > 0 ){
            s.pop();
            k--;
        }

        System.out.println(s.toString());
        return s;

    }

    public static boolean isPalindrome(Node n) {
        // This test finds the middle of the list and flips the last half
        // The original insertion point is stored and we iterate and check through the list
        // until the first check arrives at the insertion point.
        //
        // 1001001 > 100 100 1
        //           ^   ^
        // The center is found be marching to the end of the list and keeping the center stored n-1 away for each step.
        // After the center is found, we

        if(n == null || n.next == null) {
            return true;
        }


        Node forward = n;
        Node middle = n;

        while(forward.next!=null && forward.next.next!=null){
            forward = forward.next.next;
            middle = middle.next;
        }

        Node n2 = middle.next;
        middle.next = null;

        Node s1 = n2;
        Node s2 = s1.next;

        while(s1 != null && s2 != null){
            // Set the next item's .next to the current item and move forward.
            Node next_node = s2.next;
            s2.next = s1;
            s1 = s2;
            s2 = next_node;
        }

        // Remove the last dangling reference
        n2.next = null;

        //Compare from the original middle and the new head
        Node n1march = (s2==null?s1:s2);
        Node n2march = n;
        while(n1march!=null){
            if(n1march.val != n2march.val){
                return false;}

            n1march = n1march.next;
            n2march = n2march.next;

        }

        return true;
    }


    public static String infixToPostfix(String s) {
        return infix4(s);
    }

        public static String infixToPostfixOLD(String s) {
        //return infixContents(s);
        //return infix2(s);
        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < s.length(); i++)
        {
            System.out.println("next");

            if (isOperand(s.charAt(i)) || s.charAt(i) != ' ' || s.charAt(i) != '(' || s.charAt(i) != ')') {
                stack.push(s.charAt(i) + "");
                System.out.println("match found");
            }  else {
                System.out.println("oof");

                String op1 = stack.peek();
                stack.pop();
                String op2 = stack.peek();
                stack.pop();
                stack.push("(" + op2 + s.charAt(i) + op1 + ")");
            }
        }
        return stack.toString();
    }

    public static String infix4(String s){
        {

            String[] params = s.split(" ");

            Stack<String> ops = new Stack<>();

            Stack<String> vals = new Stack<>();

            for (int i = 0; i < params.length; i++)
            {
                switch (params[i]) {
                    case "(":
                        break;
                    case "+":
                        ops.push(params[i]);
                        break;
                    case "*":
                        ops.push(params[i]);
                        break;
                    case ")":
                        String d1 = vals.pop();
                        String d2 = vals.pop();
                        String op = ops.pop();
                        vals.push( d2 + " " + d1 + " "+ op);
                        break;
                    default:
                        vals.push(params[i]);
                        break;
                }
            }
            return vals.pop();

        }
    }



    private static String infix2(String s) {
        String assembled = "";
        System.out.println(s);
        for(int i = 1; i< s.length(); i++) {
            char operator = s.charAt(i);
            if (operator == '('){
                assembled += infix2(s.substring(i+3, s.lastIndexOf(')')));
            }
            if (operator == ')'){
                return "null";
            }
        }
        return "null";

    }

    private static String infix3(String s){
        for(int i = 1; i< s.length(); i++) {
        }
        return s;
    }

    private static String infixContentsOLD(String s){
    String assembled = "";
    System.out.println(s);
    for(int i = 1; i< s.length(); i++) {
        char operator = s.charAt(i);

        if (operator == '*' || operator == '+' ){
            System.out.println("======");
            System.out.println(s.substring(i));
            char a = s.charAt(i - 2);
            char b = s.charAt(i + 2);
            System.out.println(a);
            System.out.println(b);

            if (s.charAt(i+2) == '('){
                //assembled += s.charAt(i - 2) + " " + infixContents(s.substring(i+2)) + operator + " ";
                String add = a + " " + infixContentsOLD(s.substring(i+3, s.lastIndexOf(')'))) + " " + operator;
                assembled += add;
                i += add.length()*5/3;

            }else if(a == ')') {
                if(s.charAt(i+4)==')'){
                    //assembled +=
                }
                String combined = " " + b + " " + operator;
                assembled += combined;

            }else{

                String combined = a + " " + b + " " + operator;
                assembled += combined;


            }


        }
    }
        System.out.println(assembled);
        return assembled;
        }

}
