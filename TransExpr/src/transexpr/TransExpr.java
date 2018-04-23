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
            if (c == ')') {
                String p = aq.pop();
                String m = "!";
                StringBuilder k = new StringBuilder();
                while (!"(".equals(p)) {
                    if (mp.containsKey(p)) {
                        m = p;
                    } else {
                        k.append(p);
                    }
                    p = aq.pop();
                }
                k.reverse();
                k.append(m);
                aq.push(k.toString());
            } else {
                aq.push("" + c);
            }
        }
        System.out.println(aq);
    }

}
