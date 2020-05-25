package com.example.java;

import lombok.Data;

@Data
public class EquationResult {
    private double[][] vectors;
    private boolean noDecisions = false;

    public int getVectorSize() {
        return vectors.length;
    }
}
