package org.white5moke.neural.lab;

import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.lang.System.out;

public class AwarenessEngine {
    private long result = 0;

    public AwarenessEngine() {
        Arrays.stream(new int[] {4, 8, 2}).forEach((i) -> {
            Impulse.getInstance()
                    .withEngine(this)
                    .withIdentity(RandomStringUtils.randomAlphanumeric(i).getBytes(StandardCharsets.UTF_8))
                    .start();
        });
    }

    public long getResult() {
        return result;
    }

    public void setResult(long l) {
        result = l;
    }
}
