package com.example.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);

        List<double[]> s = new ArrayList();

        String nextNumbers = scanner.next();
        int eqLength = 0;
        do {
            String[] numbers = nextNumbers.split(" ");
            if (eqLength != 0) {
                eqLength = numbers.length;
            }
            if (numbers.length != eqLength) {
                logger.log(Level.WARNING, "wrong length");
            } else {
                double[] sItem = new double[eqLength];
                for (int i = 0; i < eqLength; i++) {
                    sItem[i] = Double.parseDouble(numbers[i]);
                }
                s.add(sItem);
            }
            nextNumbers = scanner.next();
        } while (!nextNumbers.equals(""));
        double[][] arrayS = new double[s.size()][eqLength];
        for (int i = 0; i < s.size(); i++) {
            arrayS[i] = s.get(i);
        }
        solveSystem(arrayS);
    }

    private static EquationResult solveSystem(double[][] s) {
        for (int i = 0; i < s.length-1; i++) {
            for (int j = i+1; j < s.length; j++) {
                 double coefficient = s[j][i]/s[i][i];
                 for (int k = 0; k < s[j].length; k++) {
                     s[j][k] = s[j][k] - coefficient*s[i][k];
                 }
            }
        }
        EquationResult result = new EquationResult();
        if (s[0].length>s.length) {
            final int varEquationsDif =  s[0].length-s.length;
            double[][] vectors = new double[varEquationsDif][s[0].length];
            for (int i = 0; i < vectors.length; i++) {
                double[] vector = vectors[i];
                vector[i+s[0].length] = 1;
            }
            for (int i = varEquationsDif-1; i >= 0; i--) {
                s[i][i] =
            }
            result.setVectors(vectors);
        }  else if (s[0].length<s.length) {
            result.setNoDecisions(true);
        }
        return result;
    }
}
