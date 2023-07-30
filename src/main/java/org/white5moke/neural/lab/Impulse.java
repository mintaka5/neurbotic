package org.white5moke.neural.lab;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.concurrent.ConcurrentInitializer;
import org.apache.commons.math3.primes.Primes;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.System.out;

public class Impulse extends Thread {
    private byte[] ident;

    private long result = 0;

    public Impulse(byte[] ident) {
        setIdent(ident);
    }

    private void setIdent(byte[] i) {
        ident = i;
    }

    public byte[] getIdent() {
        return ident;
    }

    public Impulse() {

    }

    public static Impulse getInstance() {
        return new Impulse();
    }

    public Impulse withIdentity(byte[] id) {
        return new Impulse(id);
    }

    @Override
    public void run() {
        long i = 0;
        while(true) {
            out.print(this);
            out.print("\r");

            if(Primes.isPrime((int) i)) {

                /**
                 * @TODO figure outa way to bubble result up out of
                 * this thread instance into something accessible to
                 * the AwarenessEngine
                 */
            }

            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            i++;
        }
    }

    @Override
    public String toString() {
        return  "from impulse ".concat(Hex.encodeHexString(getIdent()))
                .concat("; result: ")
                .concat(String.valueOf(getResult()));
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }
}
