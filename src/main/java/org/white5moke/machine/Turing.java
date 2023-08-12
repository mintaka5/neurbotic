package org.white5moke.machine;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Turing extends Thread {
    private HashMap<Integer, String> states = new HashMap<>();
    private int tapeLength;

    /**
     * this can be any type of characters.
     * default is 0 (off), 1 (on), - (blank)
     * these get randomly and individually
     * placed in each tape cell
     */
    private String stateValues = "01-";

    /**
     * the fixed function table, add as many as you like
     * that will act upon cell's state value.
     */
    private String[] functionTable = new String[] {
        "(x ^ 0)", "(x < 1)", "(x + 2) / (x + 1)", "(x * x) ^ 8"
    };

    public Turing(int cnt, String vals) {
        tapeLength = cnt;
        stateValues = vals.strip();

        // create the tape loop
        IntStream.rangeClosed(0, tapeLength)
                .forEach((i) -> states.put(i, getRandomState()));
    }

    public void run() {
        states.forEach((i, s) -> out.print(s));
        out.print("\n\n");

        int position = 0;
        int funcPos = 0;

        while(true) {
            String state = getStates().get(position);

            // control unit passing over every state to process it.
            String formula = functionTable[funcPos];
            Expression expr = new Expression(formula);
            try {
                // @TODO need to try again on error, or if division by 0 happens
                EvaluationValue result = expr.with("x", Character.getNumericValue(state.charAt(0))).evaluate();
                out.print(result.getNumberValue());
            } catch (EvaluationException | ParseException e) {
                throw new RuntimeException(e);
            }

            position++;
            funcPos++;

            if(position > tapeLength) position = 0; // reset to beginning to restart loop.

            if(funcPos > functionTable.length-1) funcPos = 0; // do the same for functions.

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getRandomState() {
        //String [] inSpl = instructions.split("(?!^)");
        String[] spl = stateValues.split("(?!^)");
        int r1 = new SecureRandom().nextInt(spl.length);
        //int r2 = new SecureRandom().nextInt(inSpl.length);

        return spl[r1]/*inSpl[r2]*/;
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            out.println("1 (numeric) argument is required");
            System.exit(-1);
        }

        Turing t = new Turing(Integer.parseInt(args[0].trim()), args[1].trim());
        t.start();
    }

    public HashMap<Integer, String> getStates() {
        return states;
    }

    public void setStates(HashMap<Integer, String> states) {
        this.states = states;
    }
}
