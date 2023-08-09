package org.white5moke.neural.lab;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.math3.primes.Primes;

import java.util.concurrent.TimeUnit;

public class Impulse extends Thread {
    /**
     * unique identifier
     */
    private byte[] ident;

    /**
     * end result
     */
    private long result = 0;

    /**
     * the engine that this impulse assigned to
     */
    private AwarenessEngine engine;

    /**
     * constructor for supplied identifier
     * @param ident unique identifier
     */
    public Impulse(byte[] ident) {
        setIdent(ident);
    }

    private void setIdent(byte[] i) {
        ident = i;
    }

    public byte[] getIdent() {
        return ident;
    }

    /**
     * main constructor
     */
    public Impulse() {}

    /**
     * instance assignment of engine
     * @param engine AwarenessEngine
     * @return Impulse
     */
    public Impulse withEngine(AwarenessEngine engine) {
        Impulse i = new Impulse();
        i.setEngine(engine);

        return i;
    }

    public AwarenessEngine getEngine() {
        return engine;
    }

    public void setEngine(AwarenessEngine engine) {
        this.engine = engine;
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
            if(Primes.isPrime((int) i)) {
                /**
                 * @TODO figure outa way to bubble result up out of
                 * this thread instance into something accessible to
                 * the AwarenessEngine
                 */
                setResult(i);
            }

            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
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
