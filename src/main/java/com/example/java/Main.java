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

        String nextNumbers = scanner.nextLine();
        int eqLength = -1;
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
            nextNumbers = scanner.nextLine();
            System.out.println(nextNumbers + " " + nextNumbers.equals(""));
        } while (!nextNumbers.equals(""));
        double[][] arrayS = new double[s.size()][eqLength];
        for (int i = 0; i < s.size(); i++) {
            arrayS[i] = s.get(i);
        }
        EquationResult result = solveSystem(arrayS);
        System.out.println(result);
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
            for (int i = s.length-1; i>=0; i--) {
                double[] coefficients = new double[s[i].length];
                for (int j = 0; j < s[i].length; j++) {
                   coefficients[j] += -s[i][j]/s[i][i];
                   if (j < s.length) {
                       vectors[i][j] = coefficients[j];
                   } else if (j-i == s.length) {
                       vectors[i][j] = 1;
                   }
                }
                s = replaceVarFromEqInOthers(s, i, i, coefficients);
            }
            result.setVectors(vectors);

           /* double[][] vectors = new double[varEquationsDif][s[0].length];
            for (int i = 0; i < vectors.length; i++) {
                double[] vector = vectors[i];
                vector[i+s[0].length] = 1;
            }
            for (int i = varEquationsDif-1; i >= 0; i--) {
                s[i][i] =
            }
            result.setVectors(vectors);*/
        }  else if (s[0].length<s.length) {
            result.setNoDecisions(true);
        }
        return result;
    }

    private static double[][] replaceVarFromEqInOthers(double[][] s, int eqNumber, int varNumber, double[] coefficients) {
        if (s.length == 0 || eqNumber >= s.length || varNumber >= s[0].length) {
            throw new IllegalArgumentException();
        }
        /*double[] coefficients = new double[s[eqNumber].length];
        for (int i = 0; i < s[eqNumber].length; i++) {
            coefficients[i] += -s[eqNumber][i]/s[eqNumber][varNumber];
        }*/
        double[][] result = new double[s.length-1][s[0].length-1];
        for (int i = 0; i < s.length; i++) {
            int newEqNumber = i < eqNumber? i: i-1;
            if (i != eqNumber) {
                for (int j = 0; j < s[i].length; j++) {
                    int newVarNumber = j < varNumber? j: j-1;
                    if (j != varNumber) {
                        result[newEqNumber][newVarNumber] = s[i][j] / coefficients[j];
                    }
                }
            }
        }
        return result;
    }
}
