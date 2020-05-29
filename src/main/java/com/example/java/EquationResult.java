package com.example.java;

import lombok.Data;

@Data
public class EquationResult {
    private double[][] vectors = new  double[0][0];
    private boolean noDecisions = false;

    public int getVectorSize() {
        return vectors.length;
    }

    @Override
    public String toString() {
       if (noDecisions) {
           return "The system has no decisions";
       } else if (vectors.length == 0) {
           return "trivial decision";
       } else {
           StringBuilder result = new StringBuilder();
           for (double[] vector : vectors) {
               for (int i = 0; i < vector.length; i++) {
                   result.append(vector[i]+" ");
               }
               result.append("\\");
           }
           return result.toString();
       }
    }
}
