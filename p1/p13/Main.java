//Jaco Bezuidenhout
//u11013878

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String[] lines = new String[10000];
        String[] phi = new String[1000];
        int count = 0;
        int phiCount = 0;

        File file = new File("code.txt");
        try {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNext()) {
                    String temp = sc.nextLine();
                    String tempy[] = temp.split(" ");
                    lines[count] = tempy[0];
                    count++;
                    if (temp.contains(" ")) {
                        lines[count] = tempy[1];
                        lines[count] = lines[count].replace(";", "");
                        count++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
        }

        file = new File("phi.txt");
        try {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNext()) {
                    String temp = sc.next();
                    if (temp.compareTo("=") != 0) {
                        phi[phiCount] = temp;
                        phiCount++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
        }

        Stack vars = new Stack();
        Boolean flag;
        try {
            try (BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"))) {
                System.out.println(vars);
                out.write(vars.toString() + "\n");

                for (int k = 0; k < count; k += 2) {
                    if (lines[k].compareTo("LOAD") == 0) {
                        flag = false;
                        for (int i = 0; i < phiCount; i += 2) {
                            if (lines[k + 1].charAt(0) == phi[i].charAt(0)) {
                                lines[k + 1] = lines[k + 1] + " = " + phi[i + 1];
                                vars.push(phi[i + 1]);
                                flag = true;
                            }
                        }

                        if (flag == false) {
                            vars.push(lines[k + 1]);
                        }
                    } else if ((lines[k].compareTo("EXEC") == 0)) {
                        if (lines[k + 1].compareTo("+") == 0) {
                            String svar1 = vars.pop().toString();
                            String svar2 = vars.pop().toString();
                            int var1 = Integer.parseInt(svar1);
                            int var2 = Integer.parseInt(svar2);
                            vars.push(var1 * var2);
                        } else if (lines[k + 1].compareTo("*") == 0) {
                            String svar1 = vars.pop().toString();
                            String svar2 = vars.pop().toString();
                            int var1 = Integer.parseInt(svar1);
                            int var2 = Integer.parseInt(svar2);
                            vars.push(var1 + var2);
                        } else if (lines[k + 1].compareTo("#") == 0) {
                            String svar1 = vars.pop().toString();
                            int var1 = Integer.parseInt(svar1);
                            vars.push(var1 + 1);
                        }
                    }

                    if ((lines[k].compareTo("") == 0)) {
                        
                        vars.clear();
                        k--;
                        System.out.println();
                        out.write("\n");
                        System.out.println(vars);
                        out.write(vars.toString() + "\n");
                    } else {
                        Stack temp = new Stack();
                        for (int m = vars.size() - 1; m >= 0; m--) {
                            temp.push(vars.pop());
                        }
                        System.out.println(temp.toString() + " (" + lines[k] + " " + lines[k + 1] + ")");
                        out.write(temp.toString() + " (" + lines[k] + " " + lines[k + 1] + ")\n");
                        for (int m = temp.size() - 1; m >= 0; m--) {
                            vars.push(temp.pop());
                        }
                    }
                }
            }
        } catch (IOException e) {
        }
    }

}
