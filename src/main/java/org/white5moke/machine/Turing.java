package org.white5moke.machine;

import org.apache.commons.lang3.ArrayUtils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Turing extends Thread {
    private final int range;

    private String symbols = "AX#";
    private int currentState = 0;


    public Turing(int range) {
        this.range = range;

        // populate default instructions
        /**
         * cheatsheet:
         * a< is shift left
         * b> is shift right
         * 0| is hold
         * 1^ is continue
         * # is halt. all systems no-go
         * if continue happens we need to store last instruction's shift direction
         * basically this is the Turing machine's fixed table of instructions
         * @TODO make this customizable
         */
        String[] instructions = {
                "a<", "b>", "0|", "1^", "#"
        };
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public void run() {
        HashMap<Integer, String> loop = generateTape();
        while(true) {
            String cell = loop.get(currentState);

            out.println(currentState + ": " + cell);


            currentState++;

            if(currentState == range) currentState = 0;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private HashMap<Integer, String> generateTape() {
        HashMap<Integer, String> map = new HashMap<>();

        IntStream.rangeClosed(0, range).forEach((i) -> {
            map.put(i, getRandomSymbol());
        });

        return map;
    }

    private String getRandomSymbol() {
        String[] split = symbols.split("(?!^)");
        int rand = new SecureRandom().nextInt(split.length);

        return split[rand];
    }

    public static void main(String[] a) {
        Turing turing = new Turing(Integer.valueOf(a[0].strip()));
        turing.setSymbols("01AX#");
        turing.start();
    }
}
