//Jaco Bezuidenhout
//u11013878

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String[] lines = new String[10000];
        int count = 0;

        File file = new File("input.txt");
        try {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNext()) {
                    lines[count] = sc.next();
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
        }

        Stack vars = new Stack();
        Stack ops = new Stack();
        try {
            try (BufferedWriter out = new BufferedWriter(new FileWriter("code.txt"))) {
                for (int k = 0; k < count; k++) {
                    for (int j = 0; j < lines[k].length(); j++) {
                        if (lines[k].charAt(j) == '(') {
                            
                        } else if (lines[k].charAt(j) == ')') {
                            if (!ops.isEmpty()) {
                                vars.push(ops.pop());
                                out.write(vars.peek().toString());
                            }
                        } else if (lines[k].charAt(j) == '+' || lines[k].charAt(j) == '*' || lines[k].charAt(j) == '#') {
                            ops.push("EXEC " + lines[k].charAt(j) + ";\n");
                        } else {
                            vars.push("LOAD " + lines[k].charAt(j) + ";\n");
                            out.write(vars.peek().toString());
                        }
                    }
                    
                    while (!ops.isEmpty()) {
                        vars.push(ops.pop());
                        out.write(vars.peek().toString());
                    }
                    System.out.println(vars);
                    vars.clear();
                    out.write("\n");
                }
            }
        } catch (IOException e) {
        }

    }
}
