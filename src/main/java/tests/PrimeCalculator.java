package tests;

import java.util.BitSet;

import static java.lang.System.exit;
import static java.lang.System.out;

public class PrimeCalculator extends Thread {
    private int n;

    public PrimeCalculator(int n) {
        this.n = n;
    }

    public void run() {
        Sieve sieve = new Sieve(n + 1);
        boolean result = sieve.isPrime(n);

        out.println(("value " + n + " is" + (result ? " " : " not ") + "a prime number").toUpperCase());
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            out.println("please provide an integer!");
            exit(-1);
        }

        for(int i=0; (i<args.length); i++) {
            int n = Integer.parseInt(args[i]);
            (new PrimeCalculator(n)).start();
        }
    }
}

class Sieve {
    private BitSet bitset;
    private int number;
    private int rootN;

    public Sieve(int n) {
        number = n;
        rootN = (int) Math.sqrt((double) number);
        bitset = new BitSet(n);

        for(int i=2; i < number; i++) {
            bitset.set(i);
        }
    }

    public boolean isPrime(int n) {
        ThreadGroup group = new ThreadGroup("filter"+n);

        for(int i=2; i<=rootN; i=nextPrimeAfter(i)) {
            Filter filter = new Filter(i, bitset, number);

            (new Thread(group, filter)).start();
        }

        out.println("started " + group.activeCount() + " filter");

        while(group.activeCount() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        return bitset.get(n);
    }

    private void filter(int prime) {
        int counter = prime;

        while(counter <= number) {
            bitset.clear(counter);
            counter = counter + prime;
        }
    }

    private int nextPrimeAfter(int prime) {
        int i = prime + 1;

        while(!bitset.get(i) && prime < number) {
            i++;
        }

        return i;
    }
}

class Filter implements Runnable {
    private int number;
    private BitSet bitset;
    private int prime;

    public Filter(int p, BitSet b, int n) {
        prime = p;
        bitset = b;
        number = n;
    }

    @Override
    public void run() {
        int counter = prime;

        while(counter < number) {
            bitset.clear(counter);
            counter = counter + prime;
        }
    }
}
