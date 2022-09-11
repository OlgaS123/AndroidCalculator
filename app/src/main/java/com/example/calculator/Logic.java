package com.example.calculator;

import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class Logic {
    public enum Operations {PLUS, MINUS, MULT, DIV, PERSENT}
    public static String calculate(String firstInput, String secondInput, Operations op){
        BigDecimal firstNum = new BigDecimal(firstInput);
        BigDecimal secondNum = new BigDecimal(secondInput);
        BigDecimal result=new BigDecimal(0);
        switch (op){
            case PLUS:
                result = firstNum.add(secondNum);
                break;
            case MINUS:
                result = firstNum.subtract(secondNum);
                break;
            case MULT:
                result = firstNum.multiply(secondNum);
                break;
            case DIV:
                result = firstNum.divide(secondNum);
                break;
            case PERSENT:
                result = firstNum.remainder(secondNum);
                break;
        }
        return result.toString();
    }
}
