package transexpr;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TransExpr {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayDeque<String> aq = new ArrayDeque<>();
        String expr = sc.nextLine();
        Map<String, Integer> mp = new HashMap<>();
        mp.put("+", 0);
        mp.put("-", 1);
        mp.put("*", 2);
        mp.put("/", 3);
        mp.put("^", 4);
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            StringBuilder sba=new StringBuilder();
            if (c == ')') {
                String operand1 = aq.pop();
              if(aq.peek()=="(")
              {
                  aq.pop();
                  aq.push(operand1);
                  continue;
              }
              else
              {
                  String operator=aq.pop();
                  String operand2=aq.pop();
                  aq.pop();
                  sba.append(operand2).append(operand1).append(operator);
                  aq.push(sba.toString());
              }
            } else {
                aq.push("" + c);
            }
            System.out.println("current aq= "+aq);
        }
        System.out.println(aq);
    }

}
